<template>
    <div class="google-map" :id="name"></div>
</template>

<script>
    export default {
        name: 'google-map',
        props: ['name'],
        data: function () {
            return {}
        },
        mounted: function () {
        },
        methods: {
        }
    }
</script>

<style scoped>
    .google-map {
        width: 640px;
        height: 480px;
        margin: 0 auto;
        background: gray;
    }
</style>