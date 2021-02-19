<template>
	<div class="alert-warning alert">
		<span class="little-tittle">Notification</span>
		<div>{{ notifContent }}</div>
	</div>
</template>

<script>

export default {
	name: "Notification",
	props:{notifContent: {
		type: String,
		default: null
	}},
};
</script>

<style scoped>
    .notif-container{
        margin-top:5px;
        border-radius: 10px;
        border: 2px solid orange;
        padding: 10px;
    }
    .little-tittle{
        vertical-align: top;
        font-size: 0.7em;
        color: grey;
        margin-bottom: 10px;
    }
</style>