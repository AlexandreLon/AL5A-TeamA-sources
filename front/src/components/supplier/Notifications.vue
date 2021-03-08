<template>
	<Notification
		v-for="notification in notifications"
		:key="notification"
		:notif-content="notification"
	/>
</template>
<script>
import {onMounted,ref, onUnmounted, watch} from "vue";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import Notification from "./Notification.vue";

export default {
	components: {Notification},
	props: {
		supplierId: {
			type: Number,
			default: null
		}
	},
	setup(props){
		const stompClient = ref();
		const notifications = ref([]);

		const unsubscribe = (supplierId) =>{
			console.log(`unsubscribe : ${supplierId}`);
			stompClient.value.send("/app/unsubscribe",
				JSON.stringify({"from":supplierId, text:"unsub"}),{});

			// Unsubscribe to topic
			stompClient.value.unsubscribe(`/topic/supplier/${supplierId}`, (messageOutput) => {// this one is working fine
				console.log(messageOutput.body);
				notifications.value = [];
			});

		};
		const subscribe = (supplierId) =>{
			console.log(`subscribe : ${supplierId}`);
			// Send register to back
			stompClient.value.send("/app/register",
				JSON.stringify({"from":supplierId, text:"sub"}),{});
			// Subscribe to topic
			stompClient.value.subscribe(`/topic/supplier/${supplierId}`, (messageOutput) => {// this one is working fine
				console.log(messageOutput.body);
				notifications.value.push(messageOutput.body);
			});
		};

		watch( () =>props.supplierId, (newSupplierId, oldSupplierId) => {
			unsubscribe(oldSupplierId);
			subscribe(newSupplierId);
			notifications.value = [];
		});


		
		const onConnected = () =>{
			subscribe(props.supplierId);
		};

		const onError = (error) => {
			console.error(error);
		};
        
		onMounted(()=>{
			console.log("lancé");
			const host = process.env.VUE_APP_HOST_BACK;
			const port = process.env.VUE_APP_PORT_BACK;
			const socket = new SockJS(`http://${host}:${port}/chat`);
			stompClient.value = Stomp.over(socket);

			stompClient.value.connect({}, onConnected, onError);
		});

		onUnmounted(()=> {
			unsubscribe(props.supplierId);
		});

		

		return { onConnected, onError, stompClient, notifications };

	}
};
</script>

<style scoped />