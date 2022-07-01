<template>
  <div class="bg-green-700 box-border h-auto">
      <div class="flex flex-col">
            <nav class="sticky top-0 bg-white border-gray-200 px-2 sm:px-10 py-3 rounded dark:bg-gray-800">
                <div class="container flex flex-wrap justify-between items-center mx-auto ">
                    <a class="flex items-center">
                        <button @click="home" class="self-center text-xl font-serif font-semibold whitespace-nowrap dark:text-white">Plataforma Ayni</button>
                    </a>
                    <button data-collapse-toggle="mobile-menu" type="button" class="inline-flex items-center p-2 ml-3 text-sm text-gray-500 rounded-lg md:hidden hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200 dark:text-gray-400 dark:hover:bg-gray-700 dark:focus:ring-gray-600" aria-controls="mobile-menu" aria-expanded="false">
                    <span class="sr-only">Menu</span>
                    <svg class="w-6 h-6" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M3 5a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 10a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 15a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1z" clip-rule="evenodd"></path></svg>
                    <svg class="hidden w-6 h-6" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z" clip-rule="evenodd"></path></svg>
                    </button>
                    <div class="hidden w-full md:block md:w-auto" id="mobile-menu">
                    <ul class="flex flex-col mt-4 md:flex-row md:space-x-8 md:mt-0 md:text-sm md:font-medium">
                        <li>
                        <button @click="crearTarea" class="block text-gray-800 bg-transparent hover:bg-orange-300 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-orange-300 dark:hover:bg-transparent">Crear Tarea</button>
                        </li>
                        <li>
                        <button @click="volver" class="block text-gray-800 bg-transparent hover:bg-orange-300 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-orange-300 dark:hover:bg-transparent">Volver</button>
                        </li>
                    </ul>
                    </div>
                </div>
            </nav>
      </div>

      <div class="flex flex-row">
        <div class="focus:outline-none py-8 w-full">
          <div class="lg:flex items-center justify-center flex-col gap-4">

              <div class="w-auto focus:outline-none lg:mr-7 lg:mb-0 mb-7 shadow text-center">
                <h3 class="mb-2">{{msgVol}}</h3>
                <div class="rounded border" id="emergencymap"></div>  
              </div>
              
              <div v-for="(row, index) in emergencias" :key="index" aria-label="card 1" class="w-96 focus:outline-none lg:mr-7 lg:mb-0 mb-7 bg-white p-6 shadow rounded">
                  <div class="flex items-center border-b border-gray-200 pb-6">
                      <img src="../img/emergency-svgrepo-com.svg" alt="coin avatar" class="w-12 h-12 rounded-full" />
                      <div class="flex items-start justify-between w-full">
                          <div class="pl-3 w-full">
                              <button @click="verEmergencia(userLogeado,emergencias[index].id,emergencias[index].nombre)" class="focus:outline-none sm:text-sm text-xl font-medium leading-5 text-gray-800">
                                {{emergencias[index].nombre}}
                              </button>
                              <p class="focus:outline-none text-sm leading-normal pt-2 text-gray-500">Total tareas: </p>
                          </div>
                      </div>
                  </div>
                  <div class="px-2">
                      <p class="focus:outline-none text-sm leading-5 py-4 text-gray-600">{{emergencias[index].descripcion}}</p>
                  </div>
                  <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" @click="mostrarVoluntariosEmergencia(emergencias[index].id)">
                    Mostrar voluntarios de emergencia en el mapa
                  </button>
                  
              </div>
          </div>
        </div>
        
        <div v-if="show" class="focus:outline-none py-8 w-full">
          <div class="lg:flex items-center justify-center flex-col gap-4">
              <div class="md:w-max focus:outline-none w-96 lg:mr-7 lg:mb-0 mb-7 bg-white p-6 shadow rounded">
                Tareas asociadas a la emergencia: <br>- {{emergenciaSeleccionada}}
              </div>
              <div v-for="(row, index) in tareas" :key="index" aria-label="card 1" class="md:w-8/12 sm:w-11/12 focus:outline-none lg:mr-7 lg:mb-0 mb-7 bg-white p-6 shadow rounded">
                  <div class="flex items-center border-b border-gray-200 pb-6">
                      <img src="../img/homework-svgrepo-com.svg" alt="coin avatar" class="w-12 h-12 rounded-full" />
                      <div class="flex items-start justify-between w-full">
                          <div class="pl-3 w-full">
                              <button @click="verVoluntarios(index,em,tareas[index].nombre)" class="focus:outline-none sm:text-sm text-xl font-medium leading-5 text-gray-800">
                                {{tareas[index].nombre}}
                              </button>
                              <p class="focus:outline-none text-sm leading-normal pt-2 text-gray-500">Voluntarios inscritos: </p>
                              <p class="focus:outline-none text-sm leading-normal pt-2 text-gray-500">Voluntarios requeridos: </p>
                              <p class="focus:outline-none text-sm leading-normal pt-2 text-gray-500">Emergencia: {{emergenciaSeleccionada}}</p>
                          </div>
                      </div>
                  </div>
                  <div class="text-left px-2">
                      <p v-if="!edit || index!=i" class="w-64 focus:outline-none text-sm leading-5 py-4 text-gray-600">Fecha inicio: {{tareas[index].fechainicio}}</p>
                      <p v-if="edit && index==i" class="w-48 focus:outline-none text-sm leading-5 py-4 text-gray-600 mt-3">Fecha de inicio <input v-model="editar.fechainicio" class="border" v-if="edit && index==i" type="date"></p>
                      
                      
                      <p v-if="!edit || index!=i" class="w-64 focus:outline-none text-sm leading-5 py-4 text-gray-600">Descripcion: {{tareas[index].descripcion}}</p>
                      <p v-if="edit && index==i" class="w-48 focus:outline-none text-sm leading-5 py-4 text-gray-600 mt-3">Descripcion <input v-model="editar.descripcion" class="border" v-if="edit && index==i" type="text"></p>
                      

                      <p v-if="!edit || index!=i" class="w-64 focus:outline-none text-sm leading-5 py-4 text-gray-600">Estado tarea: {{tareas[index].estado}}</p>
                      <p v-if="edit && index==i" class="w-48 focus:outline-none text-sm leading-5 py-4 text-gray-600 mt-3">Estado <input v-model="editar.estado" class="border" v-if="edit && index==i" type="text"></p>

                      <button v-if="!edit || index!=i" @click="editarTarea(index)" class="mt-5 mr-5 text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600" type="button">Editar tarea</button>
                      <button v-if="edit && index==i" @click="guardarTarea" class="mt-5 mr-5 text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600" type="button">Guardar cambios</button>
                  </div>
              </div>
          </div>
        </div>

        <div v-if="showVolunteer" class="focus:outline-none py-8 w-full">
          <div class="lg:flex items-center justify-center flex-col gap-4">
              <div class="md:w-max focus:outline-none w-96 lg:mr-7 lg:mb-0 mb-7 bg-white p-6 shadow rounded">
                Voluntarios inscritos para realizar {{tareaSeleccionada}} de la emergencia: <br>- {{emergenciaSeleccionada}}
              </div>
              <div v-for="(row, index) in voluntarios" :key="index" aria-label="card 1" class="md:w-max focus:outline-none w-96 lg:mr-7 lg:mb-0 mb-7 bg-white p-6 shadow rounded">
                  <div class="flex items-center border-b border-gray-200 pb-6">
                      <img src="../img/eco-volunteer-svgrepo-com.svg" alt="coin avatar" class="w-12 h-12 rounded-full" />
                      <div class="flex items-start justify-between w-full">
                          <div class="pl-3 w-full">
                              <p class="focus:outline-none sm:text-sm text-xl font-medium leading-5 text-gray-800">
                                {{voluntarios[index].nombre}} {{voluntarios[index].apellido}}
                              </p>
                              <p class="focus:outline-none text-sm leading-normal pt-2 text-gray-500">Estado: {{voluntarios[index].estado}}</p>
                          </div>
                      </div>
                  </div>
                  <div class="px-2">
                      <p class="focus:outline-none text-sm leading-normal pt-2 text-gray-500">Nombre: {{voluntarios[index].nombre}} {{voluntarios[index].apellido}}</p>
                      <p class="focus:outline-none text-sm leading-normal pt-2 text-gray-500">Rut: {{voluntarios[index].rut}}</p>
                      <p class="focus:outline-none text-sm leading-normal pt-2 text-gray-500">Correo: {{voluntarios[index].correo}}</p>
                      <p class="focus:outline-none text-sm leading-normal pt-2 text-gray-500">Direccion: {{voluntarios[index].direccion}}</p>
                  </div>
              </div>
          </div>
        </div>
      </div> 
 
  </div>
  
</template>

<script>

import "leaflet/dist/leaflet"; 
import "leaflet/dist/leaflet.css";
var icon = require("leaflet/dist/images/marker-icon.png"); 
import $ from 'jquery'
var LeafIcon = L.Icon.extend({
  options: { iconSize: [25, 41], iconAnchor: [12, 41], popupAnchor: [-3, -41] },
});
var myIcon = new LeafIcon({ iconUrl: icon });

export default ({
    data(){
        return {
            userLogeado: {},
            emergencias: [],
            tareas: [],
            show: false,
            showVolunteer: false,
            emergenciaSeleccionada: null,
            tareaSeleccionada: null,
            voluntarios: [],
            voluntarioTarea: [],
            em: null,
            edit: false,
            i: null,
            editar: {},
            editando: null,
            mymap: null,
            voluntariosEmergencia: [],
            msgVol: null
        }
    },
    methods: {
      async mostrarVoluntariosEmergencia(emergencyId){
        try{
          let response = await this.$axios.get('http://localhost:8080/voluntarioEmergencia/' + emergencyId);
          let dataPoints = response.data;
          if (dataPoints.length == 0)
          {
            this.msgVol = "No hay voluntarios inscritos para la emergencia seleccionada"
          }else 
          {
            this.msgVol = null
          }
          // Limpiar mapa de anterior llamada.

          this.voluntariosEmergencia.forEach( (marker) => {
            this.mymap.removeLayer(marker);
          });

          this.voluntariosEmergencia = [];
            
          // Añadir nuevos voluntarios de emergencia.

          dataPoints.forEach(point => {
  
          // Se crea un marcador por cada punto
            let p = [point.latitud, point.longitud];
            let marker = L.marker(p, {icon:myIcon}) //se define el ícono del marcador
                          .bindPopup("<b>Nombre: </b>"+point.nombre+" "+point.apellido + "<br>" + "<b>Correo: </b>"+point.correo);

            marker.addTo(this.mymap);
            this.voluntariosEmergencia.push(marker);

          });
          this.mymap.setView([-33.456, -70.648], 4);
        }catch (error) {
          console.log("error", error);
        }
      },
      async getEmergencias(){
           try {
            let response = await this.$axios.get('http://localhost:8080/emergencias');
            this.emergencias = response.data;
            } catch (error) {
                console.log('error', error);
            } 
      },
      crearTarea(){
        this.$router.push({ name: 'crearTarea', path: '/crearTarea'})
      },
      home(){
            this.$router.push({ name: "index", path: "/"});
      },
      async verEmergencia(userLogeado,emergencia,nombreEmergencia)
        {
          this.em = emergencia
          this.tareas = []
          this.show = true
          this.emergenciaSeleccionada = nombreEmergencia
          //Pedir tareas al backend
          try {
            let response = await this.$axios.get('http://localhost:8080/tareas');
            let responseData = response.data
            for (let i=0;i<responseData.length;i++)
            {
              if (responseData[i].idEmergencia == emergencia)
              {
                this.tareas.push(responseData[i])
              }
            }
          }catch (error) {
              console.log('error', error);
          }
          
      },
      async verVoluntarios(idTarea,e,tareaSe)
        {
          this.tareaSeleccionada = tareaSe
          this.showVolunteer = true
          //Pedir voluntarios al back
          let tarea = idTarea+1
          let tmp = []
          try{
            let response = await this.$axios.get('http://localhost:8080/voluntarios')
            let response2 = await this.$axios.get('http://localhost:8080/voluntario-tarea')
            this.voluntarios = response.data
            this.voluntarioTarea = response2.data

            this.voluntarioTarea.forEach(element => {
              if (tarea == element.idTarea && e == element.idEmergencia)
              {
                tmp.push(element.idVoluntario)
              }
            });
            let volTarea = []
            this.voluntarios.forEach(element => {
              for (let i = 0;i<tmp.length;i++)
              {
                if (element.id == tmp[i])
                {
                  volTarea.push(element)
                }
              }
            });
            this.voluntarios = []
            this.voluntarios = volTarea
          }catch(e)
          {
            console.log(e)
          }
      },
      volver(){
        this.$router.push({ name: "coordinador", path: "/coordinador"});
      },
      editarTarea(index){
        this.edit = true
        this.i = index
        this.editando = index
      },
      guardarTarea(){
          this.edit = false
          //Enviar tarea al backend
          if (this.editar.nombre != null){
          this.tareas[this.editando].nombre = this.editar.nombre
          }

          if (this.editar.descripcion != null)
          {
            this.tareas[this.editando].descripcion = this.editar.descripcion
          }

          if (this.editar.estado != null)
          {
            this.tareas[this.editando].estado = this.editar.estado
          }

          if (this.editar.fechainicio != null)
          {
            this.tareas[this.editando].fechainicio = this.editar.fechainicio
          }

          if (this.editar.ubicacion != null)
          {
            this.tareas[this.editando].ubicacion = this.editar.ubicacion
          }

          this.editar = {}

          //Realizar UPDATE en el backend
          this.ActualizarTarea()
      },
        async ActualizarTarea() {
          try {
            let response = await this.$axios.put("http://localhost:8080/tarea/actualizar",this.tareas[this.editando]);
          } catch (error) {
            console.log("error", error);
          }
          alert("Datos actualizados correctamente")
        },
        async getEmergencias(){
          try {
            let response = await this.$axios.get('http://localhost:8080/emergencias');
            this.emergencias = response.data;
          } catch (error) {
              console.log('error', error);
          } 
        }

    },
  //Función que se ejecuta al cargar el componente
  created: async function() {
      let username = this.$route.params.username;
      this.userLogeado = username;
  },
  mounted: function(){
    this.getEmergencias();
    this.mymap = L.map("emergencymap").setView([-33.456, -70.648], 7);
    // Se definen los mapas de bits de OSM
    L.tileLayer("http://{s}.tile.osm.org/{z}/{x}/{y}.png", {
            attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors',
            maxZoom: 15,
    }).addTo(this.mymap); 
  },
})
</script>

<style>
#emergencymap {
  width: 400px;
  aspect-ratio: 16/9;
}
</style>