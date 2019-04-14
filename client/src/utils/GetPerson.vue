<template>
    <div>
        <button class=”Search__button” @click="callRestService()">CALL Spring Boot REST backend service</button>
        <h3>{{ obj.secondName}}</h3>
    </div>
</template>

<script>
    import {AXIOS_API} from './http-common.js'

    export default {
        name: "GetPerson",
        data () {
            return {
                obj: {
                    userName:'',
                    firstName:'',
                    secondName:''
                },
                errors: []
            }
        },
        methods:{
            callRestService () {
                AXIOS_API.get(`/hello`)
                    .then(response => {
                        // JSON responses are automatically parsed.
                        this.obj = response.data
                        //console.log(obj.data)
                    })
                    .catch(e => {
                        this.errors.push(e)
                    })
            }
        }
    }
</script>

<style scoped>

</style>