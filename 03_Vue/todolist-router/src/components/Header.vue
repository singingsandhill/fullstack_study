<template>
    <!-- 네비게이션 바를 정의합니다. Bootstrap 클래스들을 사용합니다. -->
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <!-- 브랜드 이름을 표시합니다. -->
        <span class="navbar-brand ps-2">TodoList App</span>
        <!-- 토글 버튼을 정의합니다. 모바일 뷰에서 네비게이션을 열고 닫는 데 사용됩니다. -->
        <button
            class="navbar-toggler"
            type="button"
            @click="isNavShow = !isNavShow"
        >
            <span class="navbar-toggler-icon"></span>
        </button>
        <!-- 네비게이션 메뉴를 정의합니다. 'collapse' 클래스를 사용하여 기본적으로 숨겨지도록 설정합니다. -->
        <div
            :class="
                isNavShow
                    ? 'collapse navbar-collapse show'
                    : 'collapse navbar-collapse'
            "
        >
            <!-- 네비게이션 메뉴 항목을 정의합니다. -->
            <ul class="navbar-nav">
                <li class="nav-item">
                    <!-- Vue Router를 사용하여 페이지 간의 네비게이션을 처리합니다. -->
                    <router-link class="nav-link" to="/">Home</router-link>
                </li>
                <li class="nav-item">
                    <router-link class="nav-link" to="/about"
                        >About</router-link
                    >
                </li>
                <li class="nav-item">
                    <router-link class="nav-link" to="/todos"
                        >TodoList</router-link
                    >
                </li>
            </ul>
        </div>
    </nav>
</template>

<script setup>
import { ref } from "vue";

// 네비게이션 바의 열림/닫힘 상태를 관리하는 반응형 변수입니다.
const isNavShow = ref(false);
</script>
