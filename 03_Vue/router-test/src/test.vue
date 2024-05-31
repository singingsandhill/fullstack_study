<template>
    <div class="container">
        <Header></Header>
        <router-view></router-view>
    </div>
    <br /><br />
    <button @click="changeModal">다음 페이지</button>
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
            nextRoute: "www.naver.com", // 이동할 다음 페이지 경로
        };
    },
    methods: {
        changeModal() {
            this.isModal = true;
            setTimeout(() => {
                this.isModal = false;
                this.$router.push(this.nextRoute);
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
