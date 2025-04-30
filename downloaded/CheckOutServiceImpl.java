package org.supermarket.checkout.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.supermarket.checkout.modal.Item;
import org.supermarket.checkout.modal.ItemDetail;
import org.supermarket.checkout.modal.Price;
import org.supermarket.checkout.service.CheckOutService;
import org.supermarket.checkout.service.PriceService;
import org.supermarket.checkout.util.SuperMarketUtil;

/**
 * 
 * Service implementation for CheckOut
 * 
 * @author pankajmishra
 *
 */
@Service
public class CheckOutServiceImpl implements CheckOutService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CheckOutServiceImpl.class);
	private static Map<Long, ItemDetail> stock = SuperMarketUtil.loadStockOfItems();

	@Autowired
	private PriceService priceService;

	@Override
	public Item checkOut(Long itemId, Integer itemQuantity) {
		Item item = null;
		if (stock.containsKey(itemId)) {
			LOGGER.debug("Item found in stock: " + stock.get(itemId));
			// Check If item in stock
			ItemDetail itemDetail = stock.get(itemId);
			if (itemQuantity < itemDetail.getItemStock()) {
				// Calculate normal and discounted price for the item
				Price priceDetails = priceService.calculatePrice(itemDetail, itemQuantity);
				item = new Item(itemDetail.getItemName(), itemId, itemQuantity, priceDetails,
						itemDetail.getItemDescription());
				// decrease the quantity from stock
				Integer currentStock = stock.get(itemId).getItemStock();
				stock.get(itemId).setItemStock(currentStock - itemQuantity);
			}
		}
		else{
			LOGGER.debug("Item not found in stock: " + itemId);
		}
		return item;
	}

}
