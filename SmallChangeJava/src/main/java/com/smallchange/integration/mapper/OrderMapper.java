package com.smallchange.integration.mapper;

<<<<<<< HEAD
=======
import java.time.LocalDate;
>>>>>>> b9f00cf57cb0bdbb45213e2dce723eed850cf8ed
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

	@Select("select coalesce(client_smallchange_wallet,0) from client where client_id=#{clientId}\r\n")
	float getClientWallet(String clientId);

	@Insert("Insert into orders (buy_price,client_id,code,direction,order_id,quantity,timestamp)" + " Values ("
			+ "#{buyPrice},#{clientId},#{code},#{direction},#{orderId},#{quantity},#{timestamp})\r\n")
<<<<<<< HEAD

	
	
int putBuyTrade(BuyOrder bo, @Param("wallet") float wallet) throws InsufficientFundsException;
	@Update("Update client set client_smallchange_wallet =#{wallet}where client_id=#{clientId}")
		 void updateClientWallet(BuyOrder bo,@Param("wallet") float wallet);
=======
	int putBuyTrade(@Param("buyPrice") BigDecimal buyPrice,@Param("clientId") String clientId,@Param("code") String code,@Param("direction") String Direction,@Param("orderId") String orderId,@Param("quantity") int quantity,@Param("timestamp") LocalDate timestamp, @Param("wallet") float wallet) throws InsufficientFundsException;
	
	@Update("Update client set client_smallchange_wallet =#{wallet}where client_id=#{clientId}")
		 void updateClientWallet(@Param("clientId") String clientId,@Param("wallet") float wallet);
>>>>>>> b9f00cf57cb0bdbb45213e2dce723eed850cf8ed
}

