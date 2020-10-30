import Mishap from '../models/mishap-management/Mishap';
import API from './API';

class MishapWSAPI extends API {
    
	postMishap(mishap) {
		return new Promise((resolve, reject) => {
			this.axios.post('/mishap',mishap).then(res => {
				if(res.data.name === undefined || res.data.type === undefined || res.data.status === undefined)
				    reject(Error("Not received maintenance"));
				resolve(new Mishap(res.data.id, res.data.name, res.data.type, res.data.status, res.data.priority));
			}).catch(error => {
				reject(error);
			});
		});
	}


}

export default MishapWSAPI;