import axios from 'axios';

class API {

	constructor() {
		const host = process.env.VUE_APP_HOST_BACK;
		const port = process.env.VUE_APP_PORT_BACK;
		if(host === undefined || port === undefined ||
			Number.isNaN(host) || Number.isNaN(port)) {
			throw new Error("No host or port specified or incorrect. Please edit your .env file");
		}
		this.axios = axios.create({baseURL: `http://${host}:${port}`});
	}
}

export default API;