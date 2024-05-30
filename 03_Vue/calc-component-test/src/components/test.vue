<template>
    <div>
        <p>{{ state.message }}</p>
        <p>Count: {{ state.count }}</p>
        <p>Double Count: {{ doubleCount }}</p>
        <button @click="increment">Increment</button>
    </div>
</template>

<script>
import {
    ref,
    reactive,
    computed,
    watch,
    watchEffect,
    onMounted,
    onBeforeUnmount,
} from "vue";

export default {
    setup() {
        const state = reactive({
            count: 0,
            message: "Hello, Vue!",
        });

        const doubleCount = computed(() => state.count * 2);

        const increment = () => {
            state.count++;
        };

        watch(
            () => state.count,
            (newValue, oldValue) => {
                console.log(`Count changed from ${oldValue} to ${newValue}`);
            }
        );

        watchEffect(() => {
            console.log(`Message is: ${state.message}`);
        });

        onMounted(() => {
            console.log("Component is mounted");
        });

        onBeforeUnmount(() => {
            console.log("Component is about to be unmounted");
        });

        return {
            state,
            doubleCount,
            increment,
        };
    },
};
</script>
