package org.gildedrose.qualitycontrol;

import org.gildedrose.Item;
import org.gildedrose.builder.ItemBuilder;

public class ConjuredQualityControl implements QualityControl {
	
	private static final int CONJURED_QUALITY_DROP = DEFAULT_QUALITY_DROP * 2;
	
	public Item updateQuality(Item item) {
		return ItemBuilder.from(item)
				.withQuality(item.getQuality() - qualityDropFor(item))
				.build();
	}
	
	private int qualityDropFor(Item item) {
		return item.getQuality() - CONJURED_QUALITY_DROP > 0
				? CONJURED_QUALITY_DROP
				: item.getQuality();
		
	}
	
}
