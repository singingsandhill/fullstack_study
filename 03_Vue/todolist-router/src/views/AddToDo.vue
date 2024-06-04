<script setup>
// Vue 3의 Composition API에서 필요한 함수들을 import합니다.
import { inject, reactive } from "vue";
// vue-router에서 프로그래밍 방식으로 라우팅을 할 수 있게 해주는 함수를 import합니다.
import { useRouter } from "vue-router";

// useRouter 훅을 사용하여 router 객체를 얻습니다. 이를 통해 프로그래밍 방식으로 페이지 이동이 가능합니다.
const router = useRouter();

// inject를 사용하여 상위 컴포넌트 또는 앱 레벨에서 제공된 'actions' 객체에서 addTodo 메서드를 가져옵니다.
// 이는 Vue의 의존성 주입 기능을 사용하는 것으로, 상위 컴포넌트에서 provide된 값을 하위 컴포넌트에서 사용할 수 있게 합니다.
// 변수를 선언할 때 {} 안에 이름을 넣는 것은 JavaScript의 "객체 구조 분해 할당(Object Destructuring Assignment)" 기능을 사용, { addTodo } 부분은 이 객체에서 addTodo 속성만을 추출하여 같은 이름의 변수에 할당
const { addTodo } = inject("actions");

// reactive를 사용하여 반응형 객체를 생성합니다. 이 객체의 속성이 변경되면 Vue가 자동으로 관련된 부분을 다시 렌더링합니다.
// todoItem은 사용자가 입력할 새로운 할 일의 제목(todo)과 설명(desc)을 저장합니다.
const todoItem = reactive({ todo: "", desc: "" });

// addTodoHandler 함수는 사용자가 새로운 할 일을 추가하려고 할 때 호출됩니다.
// 이 함수는 입력 유효성을 검사하고, 유효하면 할 일을 추가한 후 페이지를 전환합니다.
const addTodoHandler = () => {
    // todoItem 객체에서 todo 속성을 추출합니다. 이는 ES6의 구조 분해 할당 기능을 사용한 것입니다.
    let { todo } = todoItem;

    // todo가 비어있거나 공백만 있는 경우, 경고를 표시하고 함수를 종료합니다.
    // trim()은 문자열 앞뒤의 공백을 제거합니다.
    if (!todo || todo.trim() === "") {
        alert("할일은 반드시 입력해야 합니다");
        return;
    }

    // step.1 (주석 처리됨)
    // todoItem의 복사본을 만들어 addTodo 함수에 전달합니다. 이는 원본 객체를 변경하지 않기 위함입니다.
    // addTodo({ ...todoItem });
    // 할 일 추가 후 '/todos' 경로로 이동합니다. 이는 아마도 할 일 목록 페이지일 것입니다.
    // router.push("/todos");

    // step.2 (현재 사용 중)
    // step.1과 비슷하지만, addTodo 함수에 콜백 함수를 두 번째 인자로 전달합니다.
    // 이 콜백 함수는 할 일이 성공적으로 추가된 후에 실행될 것입니다.
    // 이 방식은 비동기 작업(예: 서버에 데이터 저장)이 완료된 후에 페이지를 전환하고 싶을 때 유용합니다.
    addTodo({ ...todoItem }, () => {
        router.push("/todos");
    });
};
</script>
