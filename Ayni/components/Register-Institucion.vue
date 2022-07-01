<template>
    <div class="max-w-sm w-full lg:max-w-full">
        <div class="rounded border-r border-b border-l border-gray-400 lg:border-l-0 lg:border-t lg:border-gray-400 bg-red-200 rounded-b lg:rounded-b-none lg:rounded-r p-4 flex flex-col justify-between leading-normal"> 
            
            <div class="flex justify-center">
                
                <form class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
                    <h3 class="text-center not-italic text-xl mr-10 mb-1 bold">Registro de Institución</h3>
                    <div class="mb-4">
                        <label class="block text-gray-700 text-sm font-bold mb-2" for="username">Nombre Institución</label>
                        <input v-model="registroIns.nombre" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" type="text" placeholder="Nombre institucion">
                    </div>
                    <div class="mb-6">
                        <label class="block text-gray-700 text-sm font-bold mb-2" for="username">Nombre coordinador</label>
                        <input v-model="registroIns.coordinador" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" type="text" placeholder="Nombre Coordinador">
                        
                        <label class="block text-gray-700 text-sm font-bold mb-2" for="password">Clave coordinador</label>
                        <input v-model="registroIns.claveCoordinador" class="shadow appearance-none border border-red-500 rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline"  type="password" placeholder="******************">
                        
                        <label class="block text-gray-700 text-sm font-bold mb-2" for="password">Repita la contraseña</label>
                        <input v-model="registroIns.confirmar_contraseña" class="shadow appearance-none border border-red-500 rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline" type="password" placeholder="******************">
                        <p class="text-red-500 text-xs italic">Las contraseñas deben coincidir</p>
                    </div>

                    <div class="flex items-center justify-between">
                        <button @click="send" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" type="button">
                            Registrar Institución
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>


<script>
export default {
    name: "MainPage",
    data() {
        return {
            registroIns: {},
        };
    },
    methods: {
        send()
        {
            if (this.registroIns.claveCoordinador !== this.registroIns.confirmar_contraseña)
            {
                alert("Las contraseñas deben coincidir")
            }else 
            {
                this.$emit('data', {nombre: this.registroIns.nombre, coordinador: this.registroIns.coordinador, claveCoordinador: this.registroIns.claveCoordinador})
            }
        }
    }
}
</script>