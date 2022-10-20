package com.smallchange.dao;

import java.util.List;

import com.smallchange.exception.InsufficientFundsException;
import com.smallchange.model.Client;
import com.smallchange.model.Holdings;
import com.smallchange.model.Instrument;
import com.smallchange.model.Orders;
import com.smallchange.uimodel.BuyInstrument;
import com.smallchange.uimodel.SellInstrument;

public interface OrderDao {
	 List<SellInstrument> getSellTrade();
	 List<BuyInstrument> getBuyTrade();
	 void putSellTrade();
	 void putBuyTrade(List<Orders> orderTable, List<Instrument> instrumentTable,Orders bo,List<Holdings> holdingsTable) throws InsufficientFundsException;

}

