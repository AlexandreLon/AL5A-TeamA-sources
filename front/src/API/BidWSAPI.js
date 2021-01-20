import Bid from '../models/bid-management/Bid';
import API from './API';

class BidWSAPI extends API {
    
	getBids() {
		return new Promise((resolve, reject) => {
			this.axios.get('/bid').then(res => {
				resolve(res.data.map(bid => new Bid(bid.id, bid.name, bid.task)));
			}).catch(error => {
				reject(error);
			});
		});
	}

	
}

export default BidWSAPI;