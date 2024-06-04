<script setup>
// import { RouterLink, RouterView } from "vue-router";
import { reactive, computed, provide } from "vue";
import Header from "@/components/Header.vue";
import axios from "axios";
import Loading from "@/components/Loading.vue";

const BASEURI = "/api/todos";
const states = reactive({ todoList: [] });

// step1. routing을 이용한 TDL구현
// reactive 함수를 사용하여 반응형 상태 객체를 생성합니다.
// 이 객체의 속성이 변경되면 Vue가 자동으로 관련된 부분을 다시 렌더링합니다.
// const states = reactive({
// todoList는 할 일 항목들의 배열입니다.
//     todoList: [
//         { id: 1, todo: "ES6학습", desc: "설명1", done: false },
//         { id: 2, todo: "React학습", desc: "설명2", done: false },
//         { id: 3, todo: "ContextAPI 학습", desc: "설명3", done: true },
//         { id: 4, todo: "야구경기 관람", desc: "설명4", done: false },
//     ],
// });
// 새로운 할 일을 추가하는 함수입니다.
// 객체 구조 분해를 사용하여 todo와 desc 속성을 매개변수로 받습니다.
// const addTodo = ({ todo, desc }) => {
// 새 할 일 항목을 todoList 배열에 추가합니다.
// id는 현재 시간의 타임스탬프를 사용하여 고유성을 보장합니다.
// done은 기본적으로 false(미완료)로 설정됩니다.
//     states.todoList.push({ id: new Date().getTime(), todo, desc, done: false });
// };
// 기존 할 일을 수정하는 함수입니다.
// 수정할 항목의 모든 속성을 매개변수로 받습니다.
// const updateTodo = ({ id, todo, desc, done }) => {
// id를 사용하여 수정할 항목의 인덱스를 찾습니다.
//     let index = states.todoList.findIndex((todo) => todo.id === id);
// 해당 인덱스의 항목을 새로운 정보로 업데이트합니다.
// 스프레드 연산자(...)를 사용하여 기존 속성을 복사한 후, 새 속성으로 덮어씁니다.
//     states.todoList[index] = { ...states.todoList[index], todo, desc, done };
// };
// 할 일을 삭제하는 함수입니다.
// const deleteTodo = (id) => {
//     let index = states.todoList.findIndex((todo) => todo.id === id);
//     states.todoList.splice(index, 1);
// };
// const toggleDone = (id) => {
//     let index = states.todoList.findIndex((todo) => todo.id === id);
//     states.todoList[index].done = !states.todoList[index].done;
// };

// step.2
const fetchTodoList = async () => {
    // 데이터를 불러오는 동안 로딩 상태를 true로 설정
    states.isLoading = true;
    try {
        // axios를 사용하여 BASEURI(서버의 기본 URL)에 GET 요청을 보냅니다.
        // await를 사용하여 비동기 작업이 완료될 때까지 기다립니다.
        const response = await axios.get(BASEURI);
        states.todoList = response.data;
    } catch (error) {
        alert("에러발생:" + error);
    }
    // 요청이 완료(성공 또는 실패)되면 로딩 상태를 false로 설정
    states.isLoading = false;
};

// 새로운 할 일을 서버에 추가하는 비동기 함수
// successCallback은 추가가 성공했을 때 실행될 함수입니다 (예: 페이지 이동).
const addTodo = async ({ todo, desc }, successCallback) => {
    states.isLoading = true;
    try {
        const payload = { todo, desc };
        // BASEURI에 POST 요청을 보내 새 할 일을 추가합니다.
        const response = await axios.post(BASEURI, payload);
        if (response.status === 201) {
            // 서버에서 반환한 데이터를 사용하여 로컬 상태를 업데이트합니다.
            // done: false는 새로 추가된 할 일은 아직 완료되지 않았다는 의미입니다.
            states.todoList.push({ ...response.data, done: false });
            successCallback();
        } else {
            alert("Todo 추가 실패");
        }
    } catch (error) {
        alert("에러발생 :" + error);
    }
    states.isLoading = false;
};
const updateTodo = async ({ id, todo, desc, done }, successCallback) => {
    states.isLoading = true;
    try {
        const payload = { id, todo, desc, done };
        const response = await axios.put(BASEURI + `/${id}`, payload);
        if (response.status === 200) {
            let index = states.todoList.findIndex((todo) => todo.id === id);
            states.todoList[index] = payload;
            successCallback();
        } else {
            alert("Todo 변경 실패");
        }
    } catch (error) {
        alert("에러발생 :" + error);
    }
    states.isLoading = false;
};
const deleteTodo = async (id) => {
    states.isLoading = true;
    try {
        console.log("Deleting todo with id:", id);
        const response = await axios.delete(BASEURI + `/${id}`);
        console.log(response.status, response.data);
        if (response.status === 200) {
            let index = states.todoList.findIndex((todo) => todo.id === id);
            states.todoList.splice(index, 1);
        } else {
            alert("Todo 삭제 실패");
        }
    } catch (error) {
        alert("에러발생 :" + error);
    }
    states.isLoading = false;
};
const toggleDone = async (id) => {
    states.isLoading = true;
    try {
        let todo = states.todoList.find((todo) => todo.id === id);
        let payload = { ...todo, done: !todo.done };
        const response = await axios.put(BASEURI + `/${id}`, payload);
        if (response.status === 200) {
            todo.done = payload.done;
        } else {
            alert("Todo 완료 변경 실패");
        }
    } catch (error) {
        alert("에러발생 :" + error);
    }
    states.isLoading = false;
};

provide(
    "todoList",
    computed(() => states.todoList)
);
provide("actions", {
    addTodo,
    deleteTodo,
    toggleDone,
    updateTodo,
    fetchTodoList,
});
fetchTodoList();
</script>

<template>
    <div class="container">
        <Header />
        <router-view />
        <Loading v-if="states.isLoading" />
    </div>
</template>

<style scoped></style>
