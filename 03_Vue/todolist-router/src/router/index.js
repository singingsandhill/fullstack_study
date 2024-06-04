import { createRouter, createWebHistory } from "vue-router";

import Home from "@/views/Home.vue";
import About from "@/views/About.vue";
import TodoList from "@/views/TodoList.vue";
import AddTodo from "@/views/AddToDo.vue";
import EditTodo from "@/views/EditToDo.vue";
import NotFound from "@/views/NotFound.vue";

const router = createRouter({
    history: createWebHistory(),
    routes: [
        { path: "/", name: "home", component: Home },
        { path: "/about", name: "about", component: About },
        { path: "/todos", name: "TodoList", component: TodoList },
        { path: "/todos/add", name: "AddTodo", component: AddTodo },
        { path: "/todos/edit/:id", name: "EditTodo", component: EditTodo },
        { path: "/:paths(.*)*", name: "NotFound", component: NotFound },
    ],
});

export default router;
