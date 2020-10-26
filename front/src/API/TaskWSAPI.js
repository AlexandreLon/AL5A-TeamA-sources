import Task from '../models/department/Task';
import API from './API';

class TaskWSAPI extends API {
    

	putTask() {
		return new Promise((resolve, reject) => {
			this.axios.put('/task').then(res => {
				if(res.data.id === undefined || res.data.name === undefined || res.data.type === undefined || res.data.status === undefined)
				    reject(Error("Not received task"));
				resolve(new Task(res.data.id, res.data.name, res.data.type, res.data.status,res.data.creationDate));
			}).catch(error => {
				reject(error);
			});
		});
	}
    
	// getTask() {
	// 	return this.axios.get('/task');
	// }
}

export default TaskWSAPI;