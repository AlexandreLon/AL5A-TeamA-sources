import Bid from '../models/bid-management/Bid';
import API from './API';

class BidWSAPI extends API {
    
	getBids() {
		return new Promise((resolve, reject) => {
			this.axios.get('/bid').then(res => {
				resolve(res.data.map(bid => new Bid(bid.id, bid.name, bid.task, bid.status)));
			}).catch(error => {
				reject(error);
			});
		});
	}

	getOffers(id) {
		return new Promise((resolve, reject) => {
			this.axios.get(`/bid/${id}/offers`).then(res => {
				resolve(res.data);
			}).catch(error => {
				reject(error);
			});
		});
	}
	
}

export default BidWSAPI;