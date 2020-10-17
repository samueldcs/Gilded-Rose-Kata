package org.gildedrose.qualitycontrol;

import org.gildedrose.Item;
import org.gildedrose.builder.ItemBuilder;

public class DefaultQualityControl implements QualityControl {
	
	public Item updateQuality(Item item) {
		return ItemBuilder.from(item)
				.withQuality(item.getQuality() - qualityDropFor(item))
				.build();
	}
	
	private int qualityDropFor(Item item) {
		int defaultQualityDrop = defaultQualityDropFor(item);
		
		return item.getQuality() - defaultQualityDrop >= 0
				? defaultQualityDrop
				: item.getQuality();
	}
	
	private int defaultQualityDropFor(Item item) {
		return item.getSellIn() < 0
				? DEFAULT_QUALITY_DROP * 2
				: DEFAULT_QUALITY_DROP;
	}
	
}
