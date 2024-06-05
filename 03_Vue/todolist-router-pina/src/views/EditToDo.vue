<template>
    <!-- 페이지 제목을 표시하는 행 -->
    <div class="row">
        <div class="col p-3">
            <h2>할일 수정</h2>
        </div>
    </div>

    <!-- 할 일 수정 폼을 포함하는 행 -->
    <div class="row">
        <div class="col">
            <!-- 할 일 제목 입력 필드 -->
            <div class="form-group">
                <label htmlFor="todo">할일:</label>
                <input
                    type="text"
                    class="form-control"
                    id="todo"
                    v-model="todoItem.todo"
                />
            </div>

            <!-- 할 일 설명 입력 필드 (여러 줄 입력 가능) -->
            <div class="form-group">
                <label htmlFor="desc">설명:</label>
                <!--v-model을 사용하여 입력값과 todoItem.desc를 양방향 바인딩 -->
                <textarea
                    class="form-control"
                    rows="3"
                    id="desc"
                    v-model="todoItem.desc"
                ></textarea>
            </div>

            <!-- 완료 여부를 체크하는 체크박스 -->
            <div class="form-group">
                <label htmlFor="done">완료여부 : </label>&nbsp;
                <input type="checkbox" v-model="todoItem.done" />
                <!-- v-model을 사용하여 체크 상태와 todoItem.done을 양방향 바인딩 -->
            </div>

            <!-- 수정 및 취소 버튼 -->
            <div class="form-group">
                <!-- 수정 버튼: 클릭 시 updateTodoHandler 메서드 호출 -->
                <button
                    type="button"
                    class="btn btn-secondary m-1"
                    @click="updateTodoHandler"
                >
                    수정
                </button>
                <!-- 취소 버튼: 클릭 시 할 일 목록 페이지로 이동 -->
                <button
                    type="button"
                    class="btn btn-secondary m-1"
                    @click="router.push('/todos')"
                >
                    취소
                </button>
            </div>
        </div>
    </div>
</template>
<script setup>
import { reactive } from "vue";
import { useRouter, useRoute } from "vue-router";
import { useTodoListStore } from "@/stores/todoList.js";
const router = useRouter();
const currentRoute = useRoute();
const { todoList, updateTodo } = useTodoListStore();
const matchedTodoItem = todoList.find(
    (item) => item.id === currentRoute.params.id
);
if (!matchedTodoItem) {
    router.push("/todos");
}
const todoItem = reactive({ ...matchedTodoItem });
const updateTodoHandler = () => {
    let { todo } = todoItem;
    if (!todo || todo.trim() === "") {
        alert("할일은반드시입력해야합니다");
        return;
    }
    updateTodo({ ...todoItem }, () => {
        router.push("/todos");
    });
};
</script>
