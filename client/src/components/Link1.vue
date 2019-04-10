<template>
    <div>
        <p>
            I am a Link1 {{ $route.params.link1Id }}. Please, treat me well.
        </p>
    </div>
</template>

<script>
    export default {
        name: "Link1",

        watch:{
            '$route'(to, from){
                alert(to.params.link1Id)
            }
        },
        created (){
            alert(this.$route.params.link1Id);
        }
    }
</script>

<style scoped>

</style>