package org.gildedrose.sellincontrol;

import org.gildedrose.Item;
import org.gildedrose.builder.ItemBuilder;

import static org.gildedrose.qualitycontrol.QualityControlFactory.SULFURAS_ITEM_NAME;

public class SellInControl {
	
	private static final int DEFAULT_DECREASE = 1;
	private static final int NO_DECREASE = 0;
	
	public Item updateSellInFor(Item item) {
		return ItemBuilder.from(item)
				.withSellIn(item.getSellIn() - sellInDecreaseFor(item))
				.build();
	}
	
	private int sellInDecreaseFor(Item item) {
		return SULFURAS_ITEM_NAME.equals(item.getName()) ? NO_DECREASE : DEFAULT_DECREASE;
	}
}
