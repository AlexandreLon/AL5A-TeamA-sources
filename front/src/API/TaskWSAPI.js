import Task from '../models/supplier/Task';
import API from './API';

class TaskWSAPI extends API {
    

	putTask(id) {
		return new Promise((resolve, reject) => {
			this.axios.put(`/task/${id}`).then(res => {
				if(res.data.id === undefined || res.data.name === undefined || res.data.type === undefined || res.data.status === undefined)
				    reject(Error("Not received task"));
				resolve(new Task(res.data.id, res.data.name, res.data.type, res.data.status,res.data.priority,res.data.creationDate));
			}).catch(error => {
				reject(error);
			});
		});
	}
	
	getTasks() {
		return new Promise((resolve, reject) => {
			this.axios.get('/task').then(res => {
				resolve(res.data.map(data => new Task(data.id, data.name, data.type, data.status, data.priority, data.creationDate)));
			}).catch(error => {
				reject(error);
			});
		});
	}

}

export default TaskWSAPI;