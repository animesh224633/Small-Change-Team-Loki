/**
 * 
 */
package com.smallchange.dao;


import java.util.List;

import com.smallchange.uimodel.Portfolio;

/**
 * @author a683691
 *
 */
public interface PortfolioDao {
	public List<Portfolio> getUserPortfolio(int clientId);

}
