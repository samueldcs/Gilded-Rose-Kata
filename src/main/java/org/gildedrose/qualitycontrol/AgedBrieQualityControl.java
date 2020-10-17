package org.gildedrose.qualitycontrol;

import org.gildedrose.Item;
import org.gildedrose.builder.ItemBuilder;

import static java.lang.Math.min;

public class AgedBrieQualityControl implements QualityControl {
	
	public Item updateQuality(Item item) {
		return ItemBuilder.from(item)
				.withQuality(newQualityFor(item))
				.build();
	}
	
	private int newQualityFor(Item item) {
		return min(item.getQuality() + DEFAULT_QUALITY_HIKE, MAX_QUALITY_ALLOWED);
	}
	
}
