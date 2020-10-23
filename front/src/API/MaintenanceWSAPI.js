import Maintenance from '../models/maintenance-management/Maintenance';
import API from './API';

class MaintenanceWSAPI extends API {
    
	postMaintenance(maintenance) {
		return new Promise((resolve, reject) => {
			this.axios.post('/maintenance',maintenance).then(res => {
				if(res.data.name === undefined || res.data.type === undefined || res.data.status === undefined)
				    reject(Error("Not received maintenance"));
				resolve(new Maintenance(res.data.id, res.data.name, res.data.type, res.data.status));
			}).catch(error => {
				reject(error);
			});
		});
	}


}

export default MaintenanceWSAPI;