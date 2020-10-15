import API from './API';

class DefaultAPI extends API {

	constructor() {
		super("localhost", "8000");
	}
    
	getHello() {
		return this.axios.get('/hello');
	}
}

export default DefaultAPI;