<template>
    <div>
        X : <input type="text" v-model.number="state.x" /> <br />
        Y : <input type="text" v-model.number="state.y" /> <br />
        <div>결과 : {{ result }}</div>
    </div>
</template>
<script>
import { reactive, computed } from "vue";

export default {
    name: "Calc4",
    setup() {
        const state = reactive({ x: 10, y: 20 }); //객체에 반응성을 가지도록
        const result = computed(() => {
            return state.x + state.y;
        });
        return { state, result };
    },
};
</script>
