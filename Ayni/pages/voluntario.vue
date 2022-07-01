<!-- Please remove this file from your project -->
<template>
<div class="bg-green-700 box-border" v-bind:style="show==true ? 'height: auto;' : 'height: 100vh;' ">
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
                        <button @click="home" class="block text-gray-800 bg-transparent hover:bg-orange-300 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-orange-300 dark:hover:bg-transparent">Cerrar Sesión</button>
                        </li>
                    </ul>
                    </div>
                </div>
            </nav>
      </div>

      <div class="flex flex-row">
        <div class="focus:outline-none py-8 w-full">
          <div class="lg:flex items-center justify-center flex-col gap-4">
              <div v-for="(row, index) in emergencias" :key="index" aria-label="card 1" class="w-96 focus:outline-none lg:mr-7 lg:mb-0 mb-7 bg-white p-6 shadow rounded">
                  <div class="flex items-center border-b border-gray-200 pb-6">
                      <img src="../img/emergency-svgrepo-com.svg" alt="coin avatar" class="w-12 h-12 rounded-full" />
                      <div class="flex items-start justify-between w-full">
                          <div class="pl-3 w-full">
                              <button @click="verEmergencia(emergencias[index].id,emergencias[index].nombre)" class="focus:outline-none sm:text-sm text-xl font-medium leading-5 text-gray-800">
                                {{emergencias[index].nombre}}
                              </button>
                              <p class="focus:outline-none text-sm leading-normal pt-2 text-gray-500">Total tareas: </p>
                          </div>
                      </div>
                  </div>
                  <div class="px-2">
                      <p class="focus:outline-none text-sm leading-5 py-4 text-gray-600">{{emergencias[index].descripcion}}</p>
                  </div>
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
                              <button class="focus:outline-none sm:text-sm text-xl font-medium leading-5 text-gray-800">
                                {{tareas[index].nombre}}
                              </button>
                              <p class="focus:outline-none text-sm leading-normal pt-2 text-gray-500">Emergencia: {{emergenciaSeleccionada}}</p>
                              <button @click="inscribir(index,tareas[index].idEmergencia)" class="mt-5 mr-5 text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600" type="button">Inscribir tarea</button>
                          </div>
                      </div>
                  </div>
                  <div class="px-2">
                    <p class="focus:outline-none text-sm leading-5 py-4 text-gray-600">Fecha inicio: {{tareas[index].fechainicio}}</p>
                      <p class="focus:outline-none text-sm leading-5 py-4 text-gray-600">{{tareas[index].descripcion}}</p>
                      <p class="focus:outline-none text-sm leading-5 py-4 text-gray-600">Estado tarea: {{tareas[index].estado}}</p>
                      <p class="focus:outline-none text-sm leading-5 py-4 text-gray-600">{{tareas[index].ubicacion}}: Mostrar en un mapa</p>
                  </div>
              </div>
          </div>
        </div>    
      </div>
  </div>
</template>

<script>

export default ({
    data(){
        return {
            loged: {},
            created: false,
            datosVoluntario: {},
            emergencias: [],
            tareas: [],
            show: false
        };
    },
    methods: {
        home()
        {
            this.$router.push({ name: "index", path: "/"});
        },
        async inscribir(tarea, emergencia)
        {
            let idTarea = tarea+1
            let volTarea = {
                idTarea: idTarea,
                idVoluntario: this.datosVoluntario.id,
                id:null,
                idEmergencia:null
            }
            let flag = 0
            try {
                let response = await this.$axios.get('http://localhost:8080/voluntario-tarea');
                let responseData = response.data
                volTarea.idEmergencia = emergencia
                for(let i=0;i<responseData.length;i++)
                {
                    if (responseData[i].idTarea == volTarea.idTarea){
                        if (responseData[i].idVoluntario == volTarea.idVoluntario){
                            if (responseData[i].idEmergencia == volTarea.idEmergencia){
                                alert("Usted ya registró esta tarea")
                                return
                            }
                        }
                    }
                }
                volTarea.id = responseData.length
            }catch (error) {
                console.log('error', error);
            }

            //Inscribir la tarea al voluntario
            try {
                let response = await this.$axios.post('http://localhost:8080/new-voluntario-tarea',volTarea);
                alert("Tarea registrada correctamente")
            }catch (error) {
                console.log('error', error);
            }

            //Añadir campo de id de emergencia a la tarea
        },
        nuevaTarea()
        {
            this.$router.push({ name: "verEmergencias", path: "/verEmergencias"});
        },
        async verEmergencia(emergencia,nombreEmergencia)
        {
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
        }
    },
    mounted: async function()
    {
        try {
            let response = await this.$axios.get('http://localhost:8080/emergencias');
            this.emergencias = response.data;
        } catch (error) {
            console.log('error', error);
        }
    },
    created: function()
    {
        this.created = true
        this.datosVoluntario.id = localStorage.getItem('voluntario', this.datosVoluntario);
    }
})
</script>