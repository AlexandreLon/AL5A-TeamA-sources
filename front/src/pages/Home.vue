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
		<div>
			<div class="button-panel">
				<h2>Supplier</h2>
				<ul>
					<li>
						<router-link to="/bid-supplier">
							<div class="btn btn-danger">
								Ongoing bids
							</div>
						</router-link>
					</li>
					<li>
						<router-link to="/supplier">
							<div class="btn btn-danger">
								My tasks
							</div>
						</router-link>
					</li>
				</ul>
			</div>

			<div class="button-panel">
				<h2>Manager</h2>
				<ul>
					<li>
						<router-link to="/maintenance">
							<div class="btn btn-primary">
								Maintenances
							</div>
						</router-link>
					</li>
					<li>
						<router-link to="/mishap">
							<div class="btn btn-primary">
								Mishaps
							</div>
						</router-link>
					</li>
					<li>
						<router-link to="/bid">
							<div class="btn btn-primary">
								Bids
							</div>
						</router-link>
					</li>
				</ul>
			</div>

		</div>

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

	.button-panel{
		display: inline-block;
		padding: 20px;
		margin: 20px;
		border: 2px solid #ddd;
		border-radius: 10px;
	}
</style>