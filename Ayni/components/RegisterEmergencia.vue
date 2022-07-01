<template>
    <div class="bg-gradient-to-r from-pink-500 to-violet-500 w-full h-screen"> 
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
                            <button @click="Volver" class="block text-gray-800 bg-transparent hover:bg-orange-300 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-orange-300 dark:hover:bg-transparent">Volver</button>
                            </li>
                        </ul>
                        </div>
                    </div>
        </nav>
    
        <div class="max-w-sm lg:max-w-full ">
            <div class="mt-5 mb-20 rounded rounded-b lg:rounded-b-none lg:rounded-r p-4 flex flex-col justify-between leading-normal"> 
                
                <div class="flex justify-center">
                    
                    <form class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
                        <h3 class="text-center not-italic text-xl mr-10 mb-3 bold">Añadir nueva emergencia</h3>
                        <div class="grid grid-cols-2 gap-4">
                            <div class="start-col-1">
                            <label class="block text-gray-700 text-sm font-bold mb-2" for="nombreEmergencia">Nombre</label>
                            <input v-model="registro.nombre" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="username" type="text" placeholder="Nombre">
                            </div>

                            <div class="start-col-2">
                            <label class="block text-gray-700 text-sm font-bold mb-2" for="descripcionEmergencia">Descripcion</label>
                            <input v-model="registro.descripcion" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline"  type="text" placeholder="Descripcion">
                            </div>
                        </div>
                            
                            <label class="block text-gray-700 text-sm font-bold mb-2" for="ubicacion">Ubicación</label>
                            <input v-model="registro.ubicacion" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline" placeholder="Ubicacion">
                            
                            <label class="block text-gray-700 text-sm font-bold mb-2" for="fecha">Fecha</label>
                            <input v-model="registro.fecha" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline" type="date" placeholder="Fecha">
                        

                            <div id="map"></div>

                        <div class="grid grid-cols-3 mt-5 mb-1">
                            <div></div>
                            <div>
                                <button @click="RegistrarEmergencia" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" type="button">
                                    Registrar emergencia
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</template>



<script>
// Importaciones para usar leaflet
import "leaflet/dist/leaflet"; 
import "leaflet/dist/leaflet.css";
var icon = require("leaflet/dist/images/marker-icon.png"); 
var LeafIcon = L.Icon.extend({
  options: { iconSize: [25, 41], iconAnchor: [12, 41], popupAnchor: [-3, -41] },
});
var myIcon = new LeafIcon({ iconUrl: icon });
export default {
    name: "MainPage",
    data() {
        return {
            registro: {},
            latitude: null,
            longitude: null,
            marcador: [],
            emergencias: [],
        };
    },
    methods: {
        
        async getEmergencias() {
            try {
                const response = await this.$axios.get("http://localhost:8080/emergencias");
                this.emergencias = response.data
            }
            catch (error) {
                return error;
            }
        },
        async RegistrarEmergencia(){
            // Se debe verificar que no exista una emergencia con el mismo nombre
            let flag = 0;
            let num = 0;
            num = this.emergencias.length;
            this.registro.id = num + 1;
            for(let i = 0; i < this.emergencias.length; i++) {
                
                if (this.emergencias[i].nombre === this.registro.nombre) {
                    alert("Ya existe una emergencia con el nombre ingresado");
                    flag = 1;
                }
            }
            if (flag == 0) {
                if (this.registro.nombre != undefined
                    && this.registro.descripcion != undefined
                    && this.registro.ubicacion != undefined 
                    && this.registro.fecha != undefined
                    && this.registro.ubicacion != undefined) {
                    
                    try {
                        this.registro.latitud = this.latitude;
                        this.registro.longitud = this.longitude;
                        await this.$axios 
                            .post("http://localhost:8080/nuevaEmergencia", this.registro)
                            .then(res => res.data)
                            .catch(res => res);
                        alert("Emergencia registrada correctamente");
                    }
                    catch (error) {
                        alert("Error al conectar con el servidor");
                        return error;
                    }
                }
                else {
                    alert("Debe rellenar todos los campos para registrar una emergencia");
                }   
            }
            this.getEmergencias();
            this.Volver();
        },
        Volver()
        {
            this.$router.push({ name: 'coordinador', path: '/coordinador'})
        }
    },
    mounted: async function(){
        this.getEmergencias();

        let _this = this
        this.mymap = L.map("map").setView([-33.456, -70.648], 7);
        //Se definen los mapas de bits de OSM
        L.tileLayer("http://{s}.tile.osm.org/{z}/{x}/{y}.png", {
        attribution:
            '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors',
        maxZoom: 15,
        }).addTo(this.mymap);
        
        this.mymap.on("click", function (e) {
            if (_this.flag == 0){
                _this.latitude = e.latlng.lat;
                _this.longitude = e.latlng.lng;
                let p =[ _this.latitude, _this.longitude]
                let marker = L.marker(p, {icon:myIcon}).bindPopup(_this.registro.ubicacion)
                marker.addTo(_this.mymap)
                _this.flag = 1
                _this.marcador.push(marker)
            }else 
            {

                _this.flag = 0
                _this.mymap.removeLayer(_this.marcador[0])
                _this.marcador = []
            }
        });

    }
}
</script>

<style>
    #map {
        width: 100%;
        aspect-ratio: 16/9;
    }
</style>