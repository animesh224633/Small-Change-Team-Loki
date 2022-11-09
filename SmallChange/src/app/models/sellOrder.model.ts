export class SellOrder {
    constructor(public name: string,
        public code: string,
        public direction: string,
        public  quantity: number,
        public  sellPrice: number,
        public clientId: string,
        public  orderId: string,
        public  timestamp: Date,
        public  holding_id: string){}
    
}