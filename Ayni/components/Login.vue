<template>
    <div class="max-w-sm w-80 lg:max-w-full">
        <div class="mt-5 p-10 border-r border-b border-l border-gray-400 lg:border-l-0 lg:border-t lg:border-gray-400 bg-red-200 rounded-b lg:rounded-b-none lg:rounded-r flex flex-col justify-between leading-normal"> 
            <h3 class="text-2xl text-center mb-3">Iniciar Sesión</h3>
            <div class="flex justify-center">
                <form class="bg-white shadow-md rounded px-4 pt-6 pb-8">
                    <div class="mb-4">
                    <label class="block text-gray-700 text-sm font-bold mb-2" for="username">
                        Nombre de Usuario
                    </label>
                    <input v-model="inicio_sesion.name" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="username" type="text" placeholder="Correo electronico">
                    </div>
                    <div class="mb-6">
                    <label class="block text-gray-700 text-sm font-bold mb-2" for="password">Contraseña</label>
                    <input v-model="inicio_sesion.password" class="shadow appearance-none border border-red-500 rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline" id="password" type="password" placeholder="******************">
                    <p class="text-red-500 text-xs italic">Ingrese una contraseña.</p>
                    </div>
                    <div class="grid grid-cols-3">
                        <div class="col-start-2">
                            <button @click="send" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" type="button">
                                Login
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
import Card from "./Card.vue";
import Login from "./Login.vue";
import Register from "./Register.vue";
import RegisterInstitucion from "./Register-Institucion.vue";
export default {
    name: "MainPage",
    data() {
        return {
            inicio_sesion: {},
        };
    },
    methods: {
        send()
        {
            this.$emit('data', {name:this.inicio_sesion.name, password: this.inicio_sesion.password})
        }
    }
}
</script>
