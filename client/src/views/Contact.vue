<template>
    <div class="row contact justify-content-center">
        <div class="col-md-3" style="padding-top: 1.2em" >
            <address>
                Университетская площадь 1<br />
                Воронеж, Россия 98052-6399<br />
                <abbr title="Phone">P:</abbr>
                XXX-XX-XX
            </address>

            <address>
                <strong>Поддержка:</strong><a href="http://www.amm.vsu.ru/" target="_blank">www.amm.vsu.ru</a><br />
            </address>
        </div>
        <div class="col-md-6 col-md-push-2">
            <Gmaps></Gmaps>
        </div>
    </div>
</template>

<script>
    import Gmaps from "../components/Gmaps";
    export default {
        name: "Contact",
        components: {Gmaps},
    }
</script>

<style scoped>
.contact{
    margin-top: 1.4em;
}
.redBox{
    border: 1px solid red;
}
</style>