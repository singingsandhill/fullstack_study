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
                    <!--
                    v-model을
                    사용하여
                    입력값과
                    todoItem.todo를
                    양방향
                    바인딩
                    --
                />
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
// 필요한 Vue 및 vue-router 기능을 import
import { inject, reactive } from "vue";
import { useRouter, useRoute } from "vue-router";

// 의존성 주입을 통해 todoList와 updateTodo 함수를 가져옵니다.
const todoList = inject("todoList");
const { updateTodo } = inject("actions");

// 라우팅을 위한 객체들을 초기화합니다.
const router = useRouter();
const currentRoute = useRoute();

// 현재 URL의 파라미터(id)와 일치하는 할 일 항목을 찾습니다.
const matchedTodoItem =
    // 주석 처리된 부분: id를 정수로 변환하여 비교 (타입이 다를 경우를 대비)
    // todoList.value.find((item) => item.id === parseInt(currentRoute.params.id));
    // 현재 사용 중: 문자열 비교 (id가 이미 문자열이라고 가정)
    todoList.value.find((item) => item.id === currentRoute.params.id);

// 일치하는 항목이 없으면 할 일 목록 페이지로 리다이렉트
if (!matchedTodoItem) {
    router.push("/todos");
}

// 일치하는 항목의 복사본을 만들어 반응형 객체로 변환
// 이는 원본을 직접 수정하지 않고, 사용자의 입력에 따라 변경할 수 있게 하기 위함입니다.
const todoItem = reactive({ ...matchedTodoItem });

// 수정 버튼 클릭 시 호출되는 함수
const updateTodoHandler = () => {
    // todoItem에서 todo 속성을 추출
    let { todo } = todoItem;

    // todo가 비어있거나 공백만 있는 경우 경고를 표시하고 함수를 종료
    if (!todo || todo.trim() === "") {
        alert("할일은 반드시 입력해야 합니다");
        return;
    }

    // step.1 (주석 처리됨)
    // 간단한 방식: 할 일을 업데이트하고 바로 목록 페이지로 이동
    // updateTodo({ ...todoItem });
    // router.push("/todos");

    // 현재 사용 중인 방식:
    // 콜백 함수를 제공하여 업데이트가 완료된 후 목록 페이지로 이동
    // 이는 업데이트가 비동기 작업(예: API 호출)일 때 유용합니다.
    updateTodo({ ...todoItem }, () => {
        router.push("/todos");
    });
};
</script>
