<template>
	<Notification
		v-for="notification in notifications"
		:key="notification"
		:notif-content="notification"
	/>
</template>
<script>
import {onMounted,ref} from "vue";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import Notification from "./Notification.vue";

export default {
	components: {Notification},
	props: {
		supplierId: {
			type: String,
			default: null
		}
	},
	setup(props){
		const stompClient = ref();
		// const username = ref();
		const notifications = ref([]);
		
		const onConnected = () =>{
			// Subscribe to the Public Topic
			stompClient.value.subscribe('/topic/messages', (messageOutput) => {// this one is working fine
				console.log(messageOutput.body);
				notifications.value.push(messageOutput.body);
			});
			// Tell your username to the server - not used, maybe later
			console.log(JSON.stringify({'from':'alexis', 'text':'coucou'}));
			stompClient.value.send("/app/register",
				 JSON.stringify({"from":props.supplierId, text:"coucou"}),{});
		};

		const onError = (error) => {
			console.error(error);
		};
        
		onMounted(()=>{
			const host = process.env.VUE_APP_HOST_BACK;
			const port = process.env.VUE_APP_PORT_BACK;
			const socket = new SockJS(`http://${host}:${port}/chat`);
			stompClient.value = Stomp.over(socket);

			stompClient.value.connect({}, onConnected, onError);
		});

		return { onConnected, onError, stompClient, notifications };

	}
};
</script>

<style scoped />