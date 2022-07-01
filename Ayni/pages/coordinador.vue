<!-- Please remove this file from your project -->
<template>
  <div class="bg-green-700 h-screen">
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
                        <button @click="nuevaEmergencia" class="block text-gray-800 bg-transparent hover:bg-orange-300 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-orange-300 dark:hover:bg-transparent">Crear Emergencia</button>    
                        </li>
                        <li>
                        <button @click="emergencias" class="block text-gray-800 bg-transparent hover:bg-orange-300 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-orange-300 dark:hover:bg-transparent">Ver Emergencias</button>
                        </li>
                        <li>
                        <button @click="nuevaTarea" class="block text-gray-800 bg-transparent hover:bg-orange-300 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-orange-300 dark:hover:bg-transparent">Crear Tarea</button>
                        </li>
                        <li>
                        <button @click="home" class="block text-gray-800 bg-transparent hover:bg-orange-300 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-orange-300 dark:hover:bg-transparent">Cerrar sesión</button>
                        </li>
                    </ul>
                    </div>
                </div>
            </nav>
        </div>

        <div class="grid grid-cols-3 grid-rows-3 gap-4 h-96 w-auto m-12 rounded">

            <div v-if="created" class="text-center w-96 ml-10 w-72 h-24 mt-10 text-left row-start-1 col-start-2 col-span-2 bg-white border-gray-200 px-2 sm:px-10 py-3 rounded dark:bg-gray-800">
                <h3 class="text-2xl font-normal leading-normal mt-0 mb-2 text-indigo-800">Bienvenido coordinador {{loged.name}}</h3>
            </div>
            
            <div v-if="created" class="ml-10 w-96 mt-10 h-80 text-left row-start-2 col-start-2 col-span-2 bg-red-50 border-gray-200 px-5 py-3 rounded dark:bg-gray-800">
                <h2 class="ml-1 mt-1 text-xl rounded">Datos Institución</h2>

                <div class="mt-6 w-54 bg-white rounded-lg shadow">
                    <ul class="divide-y-2 divide-gray-300">
                        <li class="p-3">Nombre institucion: {{datosIns}}</li>
                        <!-- Podriamos incorporar un mapa que muestre la ubicacion de la institucion-->
                        <li class="p-3">Ubicacion: </li>
                        <li class="p-3">Nombre Coordinador: {{loged.name}}</li>
                    </ul>
                </div>
            </div>
        </div>

  </div>
</template>

<script>

export default ({
    name: "MainPage",
    data(){
        return {
            loged: {},
            created: false,
            datosIns: {},
            registro: {},
        };
    },
    methods: {
        home()
        {
            this.$router.push({ name: "index", path: "/"});
        },
        nuevaEmergencia()
        {   
            this.$router.push({ name: "crearEmergencia", path: "/crearEmergencia"});
        },
        emergencias()
        {
            this.$router.push({ name: "verEmergencias", path: "/verEmergencias"});
        },
        nuevaTarea()
        {
            this.$router.push({ name: "crearTarea", path: "/crearTarea"});
        },
        
    },
    created: function()
    {
        this.created = true
        
        this.loged.name = localStorage.getItem('loged', this.loged);
        this.datosIns = localStorage.getItem('institucion', this.datosIns);
    },
    mounted: function()
    {
        
    },
    components: {}

    
})
</script>