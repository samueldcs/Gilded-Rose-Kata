package org.gildedrose.qualitycontrol;

import static org.gildedrose.builder.ItemBuilder.anItem;
import static org.gildedrose.qualitycontrol.QualityControlFactory.CONJURED_ITEM_NAME;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.gildedrose.Item;
import org.junit.Test;

public class ConjuredQualityControlTest {
	
	private final QualityControl qualityControl = new ConjuredQualityControl();
	private final QualityControlAssertionFactory assertionFactory = new QualityControlAssertionFactory(qualityControl);
	
	@Test public void
	shouldDecreaseQualityByTwoForConjuredItems() {
		var conjured = anItem()
				.withName(CONJURED_ITEM_NAME)
				.withQuality(20)
				.withSellIn(10).build();
		
		assertionFactory
				.update(conjured)
				.andExpectQualityToBe(18);
	}

	@Test public void
	shouldNeverSetQualityForLessThanZero() {
		var conjured = anItem()
				.withName(CONJURED_ITEM_NAME)
				.withQuality(0)
				.withSellIn(10).build();
		
		assertionFactory
				.update(conjured)
				.andExpectQualityToBe(0);
	}
	

}
