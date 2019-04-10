<template>
    <div>
    <b-button v-b-modal.modal-1>Sign up</b-button>

    <b-modal id="modal-1" title="UserVue">
        <SignIn></SignIn>
    </b-modal>
    </div>
</template>

<script>
    import SignIn from "../registration/SignIn";
    export default {
        name: "ModalSignIn",
        components: {SignIn}
    }
</script>

<style scoped>

</style>