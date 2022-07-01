<template>
    <div class="bg-red-200 w-full h-screen"> 
      <nav class="sticky top-0 bg-white px-2 sm:px-10 py-3 rounded dark:bg-gray-800">
                <div class="container flex flex-wrap justify-between items-center mx-auto ">
                    <a class="flex items-center">
                        <button @click="card=true, log=false, registrar_voluntario=false, registrar_institucion=false" class="self-center text-xl font-serif font-semibold whitespace-nowrap dark:text-white">Plataforma Ayni</button>
                    </a>
                    <button data-collapse-toggle="mobile-menu" type="button" class="inline-flex items-center p-2 ml-3 text-sm text-gray-500 rounded-lg md:hidden hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200 dark:text-gray-400 dark:hover:bg-gray-700 dark:focus:ring-gray-600" aria-controls="mobile-menu" aria-expanded="false">
                    <span class="sr-only">Menu</span>
                    <svg class="w-6 h-6" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M3 5a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 10a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 15a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1z" clip-rule="evenodd"></path></svg>
                    <svg class="hidden w-6 h-6" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z" clip-rule="evenodd"></path></svg>
                    </button>
                    <div class="hidden w-full md:block md:w-auto" id="mobile-menu">
                    <ul class="flex flex-col mt-4 md:flex-row md:space-x-8 md:mt-0 md:text-sm md:font-medium">
                        <li>
                        <button @click="Cancelar" class="block text-gray-800 bg-transparent hover:bg-orange-300 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-orange-300 dark:hover:bg-transparent">Cancelar</button>
                        </li>
                    </ul>
                    </div>
                </div>
      </nav>
      <div class="mt-24 rounded rounded-b lg:rounded-b-none lg:rounded-r p-4 flex flex-col justify-between leading-normal"> 
            <div class="flex justify-center">
                <form class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
                    <h3 class="text-center not-italic text-2xl mb-5">Formulario de creacion de tarea</h3>
                    <label for="emergencia">Seleccione emergencia</label>
                    <select id="emergencia" class="select-success form-select block w-full px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding bg-no-repeat border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none" >
                      <option v-for="(item , index) in emergencias" v-bind:key="index">
                          {{item.id}} - {{item.nombre}}
                      </option>
                    </select>
                    
                    <div class="mb-4">
                        <label class="block text-gray-700 text-sm font-bold mb-2" for="username">Nombre tarea</label>
                        <input v-model="nuevaTarea.nombre" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" type="text" placeholder="Tarea">
                    </div>
                    <div class="mb-6">
                        <label class="block text-gray-700 text-sm font-bold mb-2" for="username">Descripcion de la tarea</label>
                        <input v-model="nuevaTarea.descripcion" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" type="text" placeholder="Descripcion">
                        
                        <label class="block text-gray-700 text-sm font-bold mb-2">Fecha de inicio</label>
                        <input v-model="nuevaTarea.fechainicio" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline"  type="date" placeholder="Inicio">
                        
                        <label class="block text-gray-700 text-sm font-bold mb-2">Ubicacion</label>
                        <input v-model="nuevaTarea.ubicacion" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline" type="text" placeholder="Ubicacion*">
                        
                        <div style="grid-column: 1/-1">
                          <p style="margin-bottom: 1rem;">Habilidades Requeridas</p>
                          <div class="group-habilidades">
                            <div class="rounded" v-for="item in habilidades" v-bind:key="item.id">
                              <label class="label-form">
                                <input class="form-checkbox" type="checkbox"  v-model="user.selecciones" :id="item.id" :value="item.name">
                                <p>{{ item.name }}</p>
                              </label>
                            </div>
                          </div>
                        </div>
                    </div>

                    <div class="flex items-center justify-between">
                        <button @click="CrearTarea" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" type="button">
                            Crear tarea
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>
<script>
export default {
  data () {
    return {
      temp: {},
      nuevaTarea: {},
      atributos: [],
      habilidades: [],
      user: {
        selecciones: []
      },
      emergencias: []
    }
  },
  methods: {
    async CrearTarea () {
      try {
        let response = await this.$axios.get("http://localhost:8080/tareas");
        this.temp = response.data;
        
        let id = 0
        for (let i=0;i<=this.temp.length;i++)
        {
          id += 1
        }
        this.nuevaTarea.id = id
        if(this.nuevaTarea.nombre == null ){
            alert("Debe ingresar un nombre para la tarea");
        }
        else if(this.nuevaTarea.descripcion == null){
            alert("Debe ingresar una descripcion para la tarea");
        }
        else if(this.nuevaTarea.fechainicio == null){
            alert("Debe ingresar un fecha de inicio");
        }
        else if(this.nuevaTarea.ubicacion == null){
            alert("Debe ingresar una ubicacion de la tarea")
        }
        else{
          let lista = ''
          
          for(let i = 0; i < this.user.selecciones.length; i++){
            lista = lista + ',' + this.user.selecciones[i]
          }
          this.nuevaTarea.listaHabilidades = lista
          try {
            
            var e = document.getElementById("emergencia");
            var em = e.options[e.selectedIndex].value
            this.nuevaTarea.idEmergencia = em[0]
            this.nuevaTarea.estado = 'Recien Creada - '+this.nuevaTarea.idEmergencia
            await this.$axios
              .post("http://localhost:8080/nueva-tarea", this.nuevaTarea)
              .then((res) => res.data)
              .catch((res) => res);
              
              alert("Se ha creado correctamente la tarea")
              this.$router.push({ name: 'verEmergencias', path: '/verEmergencias'})
          } catch (error) {
            console.log("error", error);
          }
        
        }
      } catch (error) {
        console.log("error", error);
      }
    },
    async getHabilidades () {
      try {
        const response = await this.$axios.get('http://localhost:8080/habilidades')
        this.atributos = response.data
      } catch (error) {
        return error
      }
      this.atributos.forEach((atributo) => {
        this.habilidades.push({ id: atributo.id, name: atributo.nombre })
      })
    },
    async getEmergencias(){
      try {
        let response = await this.$axios.get('http://localhost:8080/emergencias');
        this.emergencias = response.data;
        } catch (error) {
          console.log('error', error);
        } 
    },
    Cancelar()
    {
      this.$router.push({ name: 'coordinador', path: '/coordinador'})
    }
  },
  created: function () {
    this.getHabilidades()
    this.getEmergencias()
  }
}
</script>

<style>

  .group-habilidades {
    max-height: 70px;
    overflow-y: scroll;
    border: 1px solid black;
    border-radius: 2px;
    width: 95%;
    padding: 5px;
    display: grid;
    justify-content: center;
    grid-template-columns: repeat(3, 1fr);

  }
  .label-form{
    display: flex; 
    flex-direction: row;
    align-items: baseline;
    justify-content: center;
  }
  .label-form > input {
    margin-right: 5px;
  }
</style>
