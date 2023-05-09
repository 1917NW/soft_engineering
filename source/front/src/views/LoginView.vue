
<template>
    <button @click="count">{{ name + "同学" }}</button>
</template>

<script>
import api from "../api/admin/user_management"
export default {
    data(){
        return {
            name : "lxy"
        }
    },
    methods : {
        count(){
            api.getAllUser().then(res => {
                this.name = res.data;
            })
        }
    }

}
</script>
    
<style>

</style>