<template>
  <div>
    <img alt="Vue logo" src="../assets/logo.png">
    <post-person></post-person>
  </div>
</template>

<script>
import PostPerson from "../utils/PostPerson";
export default {
  name:'home',
    components: {PostPerson},
}

</script>
