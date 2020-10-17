package org.gildedrose.qualitycontrol;

import static org.gildedrose.builder.ItemBuilder.anItem;
import static org.gildedrose.qualitycontrol.QualityControlFactory.AGED_BRIE_ITEM_NAME;
import static org.gildedrose.qualitycontrol.QualityControlFactory.BACKSTAGE_PASS_ITEM_NAME;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.gildedrose.Item;
import org.junit.Test;

public class AgedBrieQualityControlTest {
	
	private static final String AGED_BRIE = AGED_BRIE_ITEM_NAME;
	
	private final QualityControl qualityControl = new AgedBrieQualityControl();
	private final QualityControlAssertionFactory assertionFactory = new QualityControlAssertionFactory(qualityControl);
	
	@Test public void
	shouldIncreaseAgedBrieWheneverItGetsOlder() {
		assertionFactory
				.update(anItem().withName(AGED_BRIE).withQuality(10).build())
				.andExpectQualityToBe(11);
	}
	
	@Test public void
	shouldNeverIncreaseTheQualityToMoreThanFifty() {
		assertionFactory
				.update(anItem().withName(AGED_BRIE).withQuality(50).build())
				.andExpectQualityToBe(50);
	}
	
}
