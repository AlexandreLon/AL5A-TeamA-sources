<template>
	<div>
		<CreationModal
			@created="createMishap($event)"
		/>

		<table class="table mt-4">
			<thead>
				<tr>
					<th scope="col">
						#
					</th>
					<th scope="col">
						Name
					</th>
					<th scope="col">
						Type
					</th>
					<th scope="col">
						Status
					</th>
					<th scope="col">
						Priority
					</th>
					<th scope="col">
						Actions
					</th>
				</tr>
			</thead>
			<tbody v-if="mishaps && mishaps.length > 0">
				<Mishap
					v-for="mishap of mishaps"
					:key="mishap.id"
					:mishap="mishap"
					@updated="update($event)"
					@aborted="remove"
				/>
			</tbody>
			<tr
				v-else
				style="colspan:all; text-align: center;"
			>
				There is no mishap to display.
			</tr>
		</table>
	</div>
</template>

<script>
import { ref, onMounted } from "vue";
import MishapWSAPI from "../../API/MishapWSAPI";
import Mishap from "./Mishap.vue";
import CreationModal from "./mishap-modals/CreationModal.vue";

const mishapWSAPI = new MishapWSAPI();

export default {
	components: { Mishap, CreationModal },
	setup() {
		const mishaps = ref(null);

		onMounted(() => {
			mishapWSAPI
				.getMishaps()
				.then(res => {
					mishaps.value = res;
				})
				.catch(error => {
					console.error(error);
				});
		});

		function createMishap(newMishap) {
			mishaps.value.push(newMishap);
		}

		function update(newMishap) {
			mishaps.value = mishaps.value.map(current =>
				current.id === newMishap.id ? newMishap : current
			);
		}

		function remove(abortMishapId) {
			mishaps.value = mishaps.value.filter(current =>
				current.id !== abortMishapId
			);
		}

		return { mishaps, createMishap, update, remove };
	}
};
</script>

<style></style>
