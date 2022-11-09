export class BuyOrder {
    constructor(public name: string,
        public code: string,
        public direction: string,
        public  quantity: number,
        public  buyPrice: number,
        public clientId: string,
        public  orderId: string,
        public  timestamp: Date,
        public  holding_id: string){}
    
}