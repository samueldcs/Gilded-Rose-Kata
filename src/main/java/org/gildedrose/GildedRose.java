package org.gildedrose;

import org.gildedrose.qualitycontrol.QualityControlFactory;
import org.gildedrose.sellincontrol.SellInControl;

import java.util.List;
import java.util.stream.Collectors;

public class GildedRose {
	
	private final QualityControlFactory qualityControlFactory;
	private final SellInControl sellInControl;
	
	public GildedRose(QualityControlFactory qualityControl, SellInControl sellInControl) {
		this.qualityControlFactory = qualityControl;
		this.sellInControl = sellInControl;
	}
	
	public List<Item> updateQuality(final List<Item> itens) {
		return itens
				.stream()
				.map(this::updateQuality)
				.map(this::updateSellIn)
				.collect(Collectors.toUnmodifiableList());
	}
	
	private Item updateQuality(Item item) {
		return qualityControlFactory
				.qualityControlFor(item)
				.updateQuality(item);
	}
	
	private Item updateSellIn(Item item) {
		return sellInControl.updateSellInFor(item);
	}
	
}
