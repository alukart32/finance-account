<template>
    <div class="container">
        <div class="row">
        <b-navbar toggleable="lg" type="dark" style="background-color: dimgrey" fixed="top">
            <b-navbar-brand href="/">NavBar</b-navbar-brand>

            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

            <b-collapse id="nav-collapse" is-nav>
                <b-navbar-nav>
                    <b-nav-item>
                       <router-link to="/link1/1">Link1 1</router-link>
                    </b-nav-item>
                    <b-nav-item>
                        <router-link to="/link1/2">Link1 2</router-link>
                    </b-nav-item>
                </b-navbar-nav>

                <!-- Right aligned nav items -->
                <b-navbar-nav class="ml-auto">
                    <Lang></Lang>
                    <ModalSignIn></ModalSignIn>
                </b-navbar-nav>

            </b-collapse>
        </b-navbar>
        </div>
    </div>
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