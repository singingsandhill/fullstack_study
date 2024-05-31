<template>
    <div class="container">
        <Header></Header>
        <router-view></router-view>
        <br />
        <button @click="changeModal">HOME</button>
    </div>
    <br />

    <teleport to="#modal">
        <Modal v-if="isModal" />
    </teleport>
</template>

<script>
import { RouterLink, RouterView } from "vue-router";
import Header from "@/components/Header.vue";
import Modal from "./components/Modal.vue";

export default {
    name: "App",
    components: { Header, Modal },
    data() {
        return {
            isModal: false,
            nextRoute: "", // 이동할 다음 페이지 경로
        };
    },
    methods: {
        changeModal() {
            this.isModal = true;
            setTimeout(() => {
                this.isModal = false;
                this.$router.push({ name: "home" });
            }, 2000);
        },
    },
};
</script>

<style>
.container {
    text-align: center;
    margin-top: 10px;
}
</style>
