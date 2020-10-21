<template>
	<center class="container mt-5">
		<h1 class="mb-3">
			Front AL
		</h1>
		<img
			class="m-4"
			src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTmrgxnfcba5EoAABGbbhfHhOFKB9TQVCvIZw&usqp=CAU"
			alt="Train"
		>
		<ul>
			<li>
				<router-link to="/hello">
					<div class="btn btn-primary">
						Hello
					</div>
				</router-link>
			</li>
			<li>
				<router-link to="/department">
					<div class="btn btn-primary">
						Department
					</div>
				</router-link>
			</li>
			<li>
				<router-link to="/maintenance">
					<div class="btn btn-primary">
						Maintenance
					</div>
				</router-link>
			</li>
			<li>
				<router-link to="/">
					<div class="btn btn-primary">
						...
					</div>
				</router-link>
			</li>
		</ul>
	</center>
</template>

<script> 
// import { ref } from "vue";
export default {
	name: "Home"
};
</script>

<style>
	li
	{
		display: inline;
		list-style-type: none;
		padding-right: 20px;
	}
</style>