package org.gildedrose.qualitycontrol;

import org.gildedrose.Item;

import java.util.Map;
import java.util.Optional;

import static java.util.Optional.ofNullable;

public class QualityControlFactory {
	
	private static final String EMPTY_STRING = "";
	
	public static final String BACKSTAGE_PASS_ITEM_NAME = "Backstage passes to a TAFKAL80ETC concert";
	public static final String SULFURAS_ITEM_NAME = "Sulfuras, Hand of Ragnaros";
	public static final String AGED_BRIE_ITEM_NAME = "Aged Brie";
	public static final String CONJURED_ITEM_NAME = "Conjured Mana Cake";
	
	private final Map<String, QualityControl> qualityControls =
			Map.of(AGED_BRIE_ITEM_NAME, new AgedBrieQualityControl(),
					BACKSTAGE_PASS_ITEM_NAME, new BackstagePassQualityControl(),
					CONJURED_ITEM_NAME, new ConjuredQualityControl(),
					SULFURAS_ITEM_NAME, new SulfurasQualityControl());
	
	public QualityControl qualityControlFor(Item item) {
		return qualityControls
				.getOrDefault(
						getItemName(item),
						new DefaultQualityControl()
				);
	}
	
	private String getItemName(final Item item) {
		return ofNullable(item.name)
				.orElse(EMPTY_STRING);
	}
	
}
