package com.smallchange.integration.mapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.smallchange.exception.InsufficientFundsException;
import com.smallchange.model.Holdings;
import com.smallchange.model.Instrument;
import com.smallchange.model.Orders;
import com.smallchange.uimodel.BuyInstrument;
import com.smallchange.uimodel.BuyOrder;
import com.smallchange.uimodel.Order;
import com.smallchange.uimodel.SellInstrument;
@Mapper
public interface OrderMapper {
	@Select("SELECT h.holding_id,\r\n" + "     h.client_id,\r\n" + "     h.code,\r\n" + "     i.name,\r\n"
			+ "     i.category,\r\n" + "     h.quantity,\r\n"

			+ "    cast(round(i.current_price,2) as numeric(16,2))\r\n"

			+ "     FROM holdings h join instrument i on h.code=i.code\r\n")

	@ResultMap("com.smallchange.integration.mapper.OrderMapper.SellInstrument")

	List<SellInstrument> getSellInstrument();

	@Select("		\r\n" + "       select \r\n" + "code," + " name, category,"
			+ " cast(round(current_price,2) as numeric(16,2))\r\n" + "        from \r\n" + "      instrument\r\n")
	@ResultMap("com.smallchange.integration.mapper.OrderMapper.BuyInstrument")
	List<BuyInstrument> getBuyInstrument();

	@Select("select COALESCE(CLIENT_SMALLCHANGE_WALLET,0.0) from client where client_id=#{bo.clientId}\r\n")
	float getClientWallet(String clientId);

	@Insert("Insert into orders (buy_price,client_id,code,direction,order_id,quantity,timestamp)" + " Values ("
			+ "#{buyPrice},#{clientId},#{code},#{direction},#{orderId},#{quantity},#{timestamp})\r\n")

	
	int putBuyTradeOrder(BuyOrder bo) throws InsufficientFundsException;
	@Update("Update client set client_smallchange_wallet =#{wallet} where client_id=#{bo.clientId}")
		 void updateClientWallet(BuyOrder bo,@Param("wallet") float wallet);
	
     @Insert("Insert into holdings (holding_id,buyprice,client_id,code,quantity)" + " Values ("
 			+ "#{holding_id},#{buyPrice},#{clientId},#{code},#{quantity})\r\n")
int putBuyTradeHoldingsInsert(BuyOrder bo);
     @Update("Update holdings set holding_id=#{holding_id},buyprice=#{buyPrice},client_id=#{clientId},code=#{code},quantity=#{quantity}")
 int putBuyTradeHoldingsUpdate(BuyOrder bo);

}

