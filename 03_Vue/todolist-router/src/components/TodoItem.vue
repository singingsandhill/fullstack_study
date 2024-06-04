<script setup>
import { useRouter } from "vue-router";
import { inject } from "vue";

// 컴포넌트의 props를 정의합니다. 이 컴포넌트는 부모로부터 'todoItem'을 객체 타입으로 받아야 하며, 필수 항목입니다.
defineProps({ todoItem: { type: Object, required: true } });

// Vue Router의 useRouter 훅을 사용하여 라우터 객체를 가져옵니다.
// // useRouter : 라우터 인스턴스로 javascript에서 다른 페이지로 이동할 수 있다. // useRoute : 현재 활성 라우트 정보에 접근할 수 있다. (읽기 전용)
const router = useRouter();

// 'actions'로 제공된 deleteTodo 및 toggleDone 함수를 주입(inject)받습니다.
const { deleteTodo, toggleDone } = inject("actions");
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
