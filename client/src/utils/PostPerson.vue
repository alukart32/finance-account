<template>
        <b-form-text @submit="postApiCall" @reset="onReset">
            <b-form-group class="col-md-6 mx-auto"
                    id="input-group-1"
                    label="UserName:"
                    label-for="input-1"
                    description="We'll never share your username with anyone else."
            >
                <b-form-input
                        id="input-1"
                        v-model="obj.userName"
                        type="text"
                        required
                        placeholder="Enter UserName"
                ></b-form-input>
            </b-form-group>
            <b-form-group class="col-md-6 mx-auto"
                    id="input-group-2"
                    label="FirstName:"
                    label-for="input-2"
            >
                <b-form-input
                        id="input-2"
                        v-model="obj.firstName"
                        type="text"
                        required
                        placeholder="Enter FirstName"
                ></b-form-input>

            </b-form-group>
            <b-form-group class="col-md-6 mx-auto"
                    id="input-group-3"
                    label="SecondName:"
                    label-for="input-3"
            >
                <b-form-input
                        id="input-3"
                        v-model="obj.secondName"
                        type="text"
                        required
                        placeholder="Enter SecondName"
                ></b-form-input>
            </b-form-group>
            <b-form-group>
                <b-button type="submit" variant="primary">Submit</b-button>
                <b-button type="reset" class="ml-1" variant="danger">Reset</b-button>
            </b-form-group>
        </b-form-text>
</template>

<script>
    import {AXIOS_PERSON} from './http-common.js'

    export default {
        name: "PostPerson",
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
            postApiCall(){
                AXIOS_PERSON.post('/create', this.obj)
                    .then(res => {return res})
                    .catch(e => this.errors.push(e))
            }
        }
    }
</script>

<style scoped>
    .a{
        style:"col-md-push-5"
    }
</style>