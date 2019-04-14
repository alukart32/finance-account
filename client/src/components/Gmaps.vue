<template>
    <gmap-map
            :center="center"
            :zoom="12"
            style="width:100%;  height: 400px;"
    ></gmap-map>
</template>

<script>
    export default {
        name: "Gmaps",
        data() {
            return {
                // default to Montreal to keep it simple
                // change this to whatever makes sense
                center: { lat: 45.508, lng: -73.587 },
                markers: [],
                places: [],
                currentPlace: null
            };
        },

    }
</script>

<style scoped>

</style>