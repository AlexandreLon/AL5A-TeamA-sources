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
				@updated="updateMishap"
			/>

			<div
				@click="abortMishap"
				class="btn btn-danger"
			>
				<i class="fas fa-trash-alt" />
				Abort
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
	emits: ["updated", "aborted"],
	setup(props, { emit }) {
		
		const updateMishap = (newMishap) => {
			emit("updated", newMishap);
		};

		const abortMishap = () => {
			mishapWSAPI.abortMishap(props.mishap.id).then(res => {
				emit("aborted", res);
			}).catch(error => {
				console.error(error);
			});
		};

		return { updateMishap, abortMishap };
	}

};
</script>

<style>

</style>