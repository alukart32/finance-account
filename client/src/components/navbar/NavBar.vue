<template>
        <b-navbar toggleable="lg" type="dark" style="background-color: dimgrey" fixed="top">
            <b-navbar-brand href="/">FinaceApp</b-navbar-brand>

            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

            <b-collapse id="nav-collapse" is-nav>
                <b-navbar-nav>
                    <b-nav-item href="/about">About</b-nav-item>
                    <b-nav-item href="/support">Support</b-nav-item>
                    <b-nav-item href="/contact">Contact</b-nav-item>
                </b-navbar-nav>

                <!-- Right aligned nav items -->
                <b-navbar-nav class="ml-auto">
                    <ModalSignIn></ModalSignIn>
                </b-navbar-nav>

            </b-collapse>
        </b-navbar>
</template>

<script>
    import Lang from "../Lang";
    import ModalSignIn from "../modal/ModalSignIn";
    export default {
        name: "NavBar",
        components: {ModalSignIn, Lang},
    }
</script>

<style scoped>
</style>