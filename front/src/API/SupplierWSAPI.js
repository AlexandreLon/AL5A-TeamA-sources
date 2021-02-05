import API from './API';
import Bid from "../models/bid-management/Bid";

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

	getBids(id) {
		return new Promise((resolve, reject ) => {
			 this.axios.get(`/supplier/${id}/bid`).then(res => {
				resolve(res.data.map(bid => new Bid(bid.id, bid.name, bid.task, bid.status)));
			}).catch(error => {
				reject(error);
			});
		});
	}

	
}

export default SupplierWSAPI;