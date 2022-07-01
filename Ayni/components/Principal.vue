<!-- Please remove this file from your project -->
<template>
  <div class="bg-gray-600 w-full h-auto">
      <div class="flex flex-col">
            <nav class="sticky top-0 bg-white border-gray-200 px-2 sm:px-10 py-3 rounded dark:bg-gray-800">
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
                        <button @click="login" class="block text-gray-800 bg-transparent hover:bg-orange-300 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-orange-300 dark:hover:bg-transparent">Iniciar Sesion</button>
                        </li>
                        <li>
                        <button @click="register" class="block text-gray-800 bg-transparent hover:bg-orange-300 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-orange-300 dark:hover:bg-transparent">Registrar Voluntario</button>
                        </li>
                        <li>
                        <button @click="registerIns" class="block text-gray-800 bg-transparent hover:bg-orange-300 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-orange-300 dark:hover:bg-blue-700">Registrar Institucion</button>
                        </li>
                    </ul>
                    </div>
                </div>
            </nav>
        </div>

        <div class="bg-gradient-to-r from-pink-500 to-violet-500 h-screen w-full grid grid-cols-3 grid-row-2 gap-4 place-items-center content-center">
            <div></div>
            <Card v-if="card"></Card> 
            <Login v-if="log" @data="dataFromLogin"></Login>
            <Register v-if="registrar_voluntario" @data="dataFromRegister"></Register>
            <RegisterInstitucion v-if="registrar_institucion" @data="dataFromRegisterIns"></RegisterInstitucion>
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
            user: {},
            inicio_sesion: {name:null,password:null},
            registro: {},
            voluntarios: [],
            instituciones: [],
            registroIns: {},
            log: false,
            registrar_voluntario: false,
            registrar_institucion: false,
            card: null,
        };
    },
    methods: {
        login() {
            if (this.log == true) {
                this.log = false;
                this.card = true
            }else 
            {
                this.card = false
                this.log = true;
                this.registrar_institucion = false;
                this.registrar_voluntario = false;
            }
        },
        register() {
            if (this.registrar_voluntario == true) {
                this.registrar_voluntario = false;
                this.card = true
            }else 
            {
                this.card = false
                this.log = false
                this.registrar_institucion = false
                this.registrar_voluntario = true;
            }
        },
        registerIns() {
            if (this.registrar_institucion == true) {
                this.registrar_institucion = false;
                this.card = true
            }else 
            {
                this.card = false
                this.log = false
                this.registrar_voluntario = false
                this.registrar_institucion = true;
            }
        },
        async getVoluntarios() {
            try {

                const response = await this.$axios.get("http://localhost:8080/voluntarios");
                this.voluntarios = response.data
            }
            catch (error) {
                return error;
            }
        },
        async getInstituciones() {
            try {
                const response = await this.$axios.get("http://localhost:8080/instituciones");
                this.instituciones = response.data;
            }
            catch (error) {
                return error;
            }
        },
        InicioSesion() {
            //  Se debe revisar que los datos ingresados esten en la base de datos
            let tmp = 0;
            //  Se verifica si es un voluntario
            //  Traemos todos los voluntarios
            if (this.voluntarios.length !== 0) {
                this.voluntarios.forEach((voluntario) => {
                    //  Si los datos ingresados corresponden a los registrados en la base de datos se le envia a la vista de voluntarios
                    if ((this.inicio_sesion.name === voluntario.correo) && (this.inicio_sesion.password === voluntario.contraseña)) {
                        //  Enviar a vista de voluntarios
                        tmp = 1;
                        alert("Sesion iniciada como voluntario");
                        localStorage.setItem('voluntario', voluntario.id);
                        this.$router.push({ name: "voluntario", path: "/voluntario"});
                    }
                });
            }
            //  Si no se le reconoce como voluntario es porque quizas es un coordinador
            if (this.instituciones) {
                this.instituciones.forEach((institucion) => {
                    if (this.inicio_sesion.name === institucion.coordinador && this.inicio_sesion.password === institucion.claveCoordinador) {
                        tmp = 1;
                        alert("Sesion iniciada como coordinador");
                        localStorage.setItem('loged', this.inicio_sesion.name);
                        localStorage.setItem('institucion', institucion.nombre);
                        this.$router.push({ name: "coordinador", path: "/coordinador"});
                    }
                });
            }
            if (this.instituciones.length != 0) {
                if (tmp === 0) {
                    alert("Credenciales invalidas");
                }
            }
        },
        async RegistrarVoluntario() {
            //  Se debe verificar que el correo del usuario a registrar no se encuentre ya.
            let flag = 0;
            let num = 0;
            this.getVoluntarios();
            num = this.voluntarios.length;
            this.registro.id = num + 1;
            for(let i =0; i<this.voluntarios.length;i++) {
                if (this.voluntarios[i].correo === this.registro.correo) {
                    alert("El correo electronico ya se encuentra registrado");
                    flag = 1;
                }
            }
            if (flag == 0) {
                if (this.registro.nombre != undefined
                    && this.registro.apellido != undefined
                    && this.registro.correo != undefined
                    && this.registro.contraseña != this.registro
                    && this.registro.direccion != undefined
                    && this.registro.rut != undefined
                    && this.registro.estado != undefined) {
                    try {
                        await this.$axios
                            .post("http://localhost:8080/nuevo-voluntario", this.registro)
                            .then(res => res.data)
                            .catch(res => res);
                        alert("Voluntario registrado correctamente");
                    }
                    catch (error) {
                        alert("Error al conectar con el servidor");
                        return error;
                    }
                }
                else {
                    alert("Debe rellenar todos los campos para registrar un voluntario");
                }
            }
            this.getVoluntarios();
        },
        async RegistrarInstitucion() {
            // Se debe verificar que la institucion a agregar no se encuentre registrada.
            let flag = 0;
            let num = 0;
            this.getInstituciones();
            num = this.instituciones.length;
            this.registroIns.id = num + 1;
            this.instituciones.forEach((institucion) => {
                if (institucion.nombre === this.registroIns.nombre) {
                    flag = 1;
                    alert("El nombre de la institucion ya se encuentra registrado");
                }
            });
            if (flag === 0) {
                try {
                    await this.$axios
                        .post("http://localhost:8080/nueva-institucion", this.registroIns)
                        .then(res => res.data)
                        .catch(res => res);
                    alert("Institucion registrada correctamente");
                }
                catch (error) {
                    alert("Error al conectar con el servidor");
                    return error;
                }
            }
            this.getInstituciones();
        },
        dataFromLogin(value)
        {
            this.inicio_sesion = value
            this.InicioSesion()
        },
        dataFromRegister(value)
        {
            this.registro = value
            this.RegistrarVoluntario()
            
        },
        dataFromRegisterIns(value)
        {
            this.registroIns = value
            this.RegistrarInstitucion()
        }
    },
    created: function () {
        this.getVoluntarios();
        this.getInstituciones();
        this.card = true
    },
    mounted: function()
    {
        this.getVoluntarios();
        this.getInstituciones();
    },
    components: { Card, Login, Register, RegisterInstitucion }
}
</script>
