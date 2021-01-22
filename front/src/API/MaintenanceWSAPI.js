import Maintenance from "../models/maintenance-management/Maintenance";
import API from "./API";

class MaintenanceWSAPI extends API {

	createMaintenance(maintenance) {
		return new Promise((resolve, reject) => {
			this.axios.post("/maintenance", maintenance).then(res => {
				if (res.data.name === undefined || res.data.type === undefined)
					reject(Error("An error occured. The server did not return the created maintenance."));
				resolve(new Maintenance(res.data.id, res.data.name, res.data.type, res.data.status, res.data.desiredDate));
			}).catch(error => {
				reject(error);
			});
		});
	}

	getMaintenances() {
		return new Promise((resolve, reject) => {
			this.axios.get("/maintenance").then(res => {
				resolve(res.data.map(maintenance => new Maintenance(maintenance.id, maintenance.name, maintenance.type, maintenance.status, maintenance.desiredDate)));
			}).catch(error => {
				reject(error);
			});
		});
	}

	getMaintenance(id) {
		return new Promise((resolve, reject) => {
			this.axios.get(`/maintenance/${id}`).then(res => {
				resolve(new Maintenance(res.data.id, res.data.name, res.data.type, res.data.status, res.data.desiredDate));
			}).catch(error => {
				reject(error);
			});
		});
	}

	updateMaintenance(id, maintenance) {
		return new Promise((resolve, reject) => {
			this.axios.put(`/maintenance/${id}`, maintenance).then(res => {
				if (res.data.id === undefined || res.data.name === undefined || res.data.type === undefined || res.data.status === undefined) {
					reject(Error("Server did not return the updated maintenance."));
				}
				resolve(new Maintenance(res.data.id, res.data.name, res.data.type, res.data.status, res.data.desiredDate));
			}).catch(error => {
				reject(error);
			});
		});
	}

	deleteMaintenance(id) {
		return new Promise((resolve, reject) => {
			this.axios.delete(`/maintenance/${id}`).then(() => {
				resolve(id);
			}).catch(error => {
				reject(error);
			});
		});
	}


}

export default MaintenanceWSAPI;