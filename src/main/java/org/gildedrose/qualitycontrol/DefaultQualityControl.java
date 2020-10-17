package org.gildedrose.qualitycontrol;

import org.gildedrose.Item;
import org.gildedrose.builder.ItemBuilder;

public class DefaultQualityControl implements QualityControl {
	
	private static final int DOUBLE_QUALITY_DROP = DEFAULT_QUALITY_DROP * 2;
	
	public Item updateQuality(Item item) {
		return ItemBuilder.from(item)
				.withQuality(item.getQuality() - qualityDropFor(item))
				.build();
	}
	
	private int qualityDropFor(Item item) {
		return shouldDropQuality(item)
				? defaultQualityDropFor(item)
				: item.getQuality();
	}
	
	private boolean shouldDropQuality(final Item item) {
		return item.getQuality() - defaultQualityDropFor(item) >= 0;
	}
	
	private int defaultQualityDropFor(Item item) {
		return item.getSellIn() < 0
				? DOUBLE_QUALITY_DROP
				: DEFAULT_QUALITY_DROP;
	}
	
}
