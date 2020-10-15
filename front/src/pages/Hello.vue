<template>
	<h1
		class="m-5"
		style="text-align: center;"
	>
		{{ hello === undefined ? "Loading..." : hello }}
	</h1>
</template>

<script>
import { onMounted, ref } from 'vue';
import DefaultAPI from '../API/DefaultAPI';

export default {

	setup() {
        
		const hello = ref(undefined);

		onMounted(() => {
			const api = new DefaultAPI();
			api.getHello().then(res => {
				hello.value = res.data;
			});
		});
        
		return {hello};
	}

};
</script>

<style>

</style>