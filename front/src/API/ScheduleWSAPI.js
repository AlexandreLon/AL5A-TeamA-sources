import Task from '../models/supplier/Task';
import API from './API';

class ScheduleWSAPI extends API {
    
	getPlanning() {
		return new Promise((resolve, reject) => {
			this.axios.get('/schedule').then(res => {
				 resolve(res.data.map(data => new Task(data.id, data.name, data.type, data.status,data.priority,data.creationDate)));
			}).catch(error => {
				reject(error);
			});
		});
	}

	
}

export default ScheduleWSAPI;