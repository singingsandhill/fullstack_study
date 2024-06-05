<script setup>
import { useRouter } from "vue-router";
import { useTodoListStore } from "@/stores/todoList.js";

defineProps({
    todoItem: { Type: Object, required: true },
});

const router = useRouter();
const todoListStore = useTodoListStore();
const { deleteTodo, toggleDone } = todoListStore;
</script>

<template>
    <!-- 할 일 항목을 리스트 아이템으로 표시합니다. 완료된 항목은 'list-group-item-success' 클래스를 가집니다. -->
    <li
        :class="
            todoItem.done
                ? 'list-group-item list-group-item-success'
                : 'list-group-item'
        "
    >
        <!-- 할 일 제목을 표시합니다. 클릭하면 완료 상태를 토글합니다. -->
        <span
            :class="todoItem.done ? 'todo-done pointer' : 'pointer'"
            @click="toggleDone(todoItem.id)"
        >
            {{ todoItem.todo }}
            {{ todoItem.done ? "(완료)" : "" }}
        </span>
        <!-- 편집 버튼: 클릭하면 해당 할 일을 편집하는 페이지로 이동합니다. -->
        <span
            class="float-end badge bg-secondary pointer m-1"
            @click="router.push(`/todos/edit/${todoItem.id}`)"
        >
            편집
        </span>
        <!-- 삭제 버튼: 클릭하면 해당 할 일을 삭제합니다. -->
        <span
            class="float-end badge bg-secondary pointer m-1"
            @click="deleteTodo(todoItem.id)"
        >
            삭제
        </span>
    </li>
</template>
