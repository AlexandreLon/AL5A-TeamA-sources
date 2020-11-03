<template>
	<tr v-if="mishap !== null">
		<th scope="row">
			{{ mishap.id }}
		</th>
		<td>{{ mishap.name }}</td>
		<td>{{ mishap.type }}</td>
		<td>{{ mishap.status }}</td>
		<td>{{ mishap.priority }}</td>
		<td>
			<UpdateModal
				:mishap-to-update="mishap"
				@mishap-updated="updateMishap"
			/>

			<div
				@click="deleteMishap"
				class="btn btn-danger"
			>
				<i class="fas fa-trash-alt" />
				Delete
			</div>
		</td>
	</tr>
</template>

<script>
import MishapWSAPI from "../../API/MishapWSAPI";
import Mishap from "../../models/mishap-management/Mishap";
import UpdateModal from "./mishap-modals/UpdateModal.vue";

const mishapWSAPI = new MishapWSAPI();

export default {
	components: { UpdateModal },
	props: {
		mishap: {
			type: Mishap,
			default: null
		}
	},
	emits: ["mishap-updated", "mishap-deleted"],
	setup(props, { emit }) {
		
		const updateMishap = (newMishap) => {
			emit("mishap-updated", newMishap);
		};

		const deleteMishap = () => {
			mishapWSAPI.deleteMishap(props.mishap.id).then(res => {
				emit("mishap-deleted", res);
			}).catch(error => {
				console.error(error);
			});
		};

		return { updateMishap, deleteMishap };
	}

};
</script>

<style>

</style>