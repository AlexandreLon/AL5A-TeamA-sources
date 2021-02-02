import API from './API';

class SupplierWSAPI extends API {
    
	outbid(offer, id) {
		console.log(offer, id);
		return new Promise((resolve, reject) => {
			this.axios.post(`/supplier/${id}/outbid`, offer).then(res => {
				resolve(res);
			}).catch(error => {
				reject(error);
			});
		});
	}

	getSuppliers() {
		return new Promise((resolve, reject) => {
			this.axios.get('/supplier').then(res => {
				resolve(res.data);
			}).catch(error => {
				reject(error);
			});
		});
	}

	
}

export default SupplierWSAPI;