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
			<div
				@click="updateMishap"
				class="btn btn-warning"
			>
				Update
				<i class="fas fa-pencil-alt" />
			</div>
			<div
				@click="deleteMishap"
				class="btn btn-danger"
			>
				Delete
			</div>
		</td>
	</tr>
</template>

<script>

import MishapWSAPI from "../../../API/MishapWSAPI";
import Mishap from "../../../models/mishap-management/Mishap";

const mishapWSAPI = new MishapWSAPI();

export default {
	props: {
		mishap: {
			type: Mishap,
			default: null
		}
	},
	emits: ["mishap-updated", "mishap-deleted"],
	setup(props, { emit }) {

		const updateMishap = () => {
			mishapWSAPI.updateMishap(props.mishap.id).then(res => {
				emit("mishap-updated", res);
			}).catch(error => {
				console.error(error);
			});
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