import { createRouter, createWebHistory } from "vue-router";

import Home from "@/views/Home.vue";
import About from "@/views/About.vue";
import Members from "@/views/Members.vue";
import Videos from "@/views/Videos.vue";

const router = createRouter({
    history: createWebHistory(),
    routes: [
        { path: "/", name: "home", component: Home },
        { path: "/about", name: "about", component: About },
        { path: "/members", name: "members", component: Members },
        { path: "/videos", name: "videos", component: Videos },
    ],
});

export default router;
