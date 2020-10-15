import axios from 'axios';

class API {

	constructor(host, port) {
		this.axios = axios.create({baseURL: `http://${host}:${port}`});
	}
}

export default API;