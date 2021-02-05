<template>
	<center class="container mt-5">
		<h1 class="mb-3">
			Train Management
		</h1>
		<img
			class="m-4"
			src="https://www.pkpcargointernational.com//static/2018/08/railway-1.jpg"
			alt="Train"
		>
		<ul>
			<li>
				<router-link to="/bid-supplier">
					<div class="btn btn-danger">
						Bid (Supplier)
					</div>
				</router-link>
			</li>
			<li>
				<router-link to="/supplier">
					<div class="btn btn-primary">
						Supplier
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
				<router-link to="/mishap">
					<div class="btn btn-primary">
						Mishap
					</div>
				</router-link>
			</li>
			<li>
				<router-link to="/bid">
					<div class="btn btn-primary">
						Bid
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