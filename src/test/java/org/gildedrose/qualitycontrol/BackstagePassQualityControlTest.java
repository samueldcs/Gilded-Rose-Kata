package org.gildedrose.qualitycontrol;

import static org.gildedrose.qualitycontrol.QualityControlFactory.BACKSTAGE_PASS_ITEM_NAME;
import org.gildedrose.Item;
import org.junit.Test;

public class BackstagePassQualityControlTest {

	private static final int TWENTY_DAYS = 20;
	private static final int TEN_DAYS = 10;
	private static final int FIVE_DAYS = 5;
	private static final int ZERO_DAYS = 0;
	
	private final QualityControl qualityControl = new BackstagePassQualityControl();
	private final QualityControlAssertionFactory assertionFactory = new QualityControlAssertionFactory(qualityControl);
	
	@Test public void
	shouldIncreaseQualityAsDaysGoBy() {
		assertionFactory
				.update(new Item(BACKSTAGE_PASS_ITEM_NAME, TWENTY_DAYS, 10))
				.andExpectQualityToBe(11);
	}
	
	@Test public void
	shouldIncreaseQualityByTwoWhenNeedsToBeSoldInTenDaysOrLess() {
		assertionFactory
				.update(new Item(BACKSTAGE_PASS_ITEM_NAME, TEN_DAYS, 10))
				.andExpectQualityToBe(12);
	}
	
	@Test public void
	shouldIncreaseQualityByThreeWhenNeedsToBeSoldInFiveDaysOrLess() {
		assertionFactory
				.update(new Item(BACKSTAGE_PASS_ITEM_NAME, FIVE_DAYS, 10))
				.andExpectQualityToBe(13);
	}
	
	@Test public void
	shouldSetQualityToZeroAfterConcert() {
		assertionFactory
				.update(new Item(BACKSTAGE_PASS_ITEM_NAME, ZERO_DAYS, 10))
				.andExpectQualityToBe(0);
	}
	
	@Test public void
	shouldNeverIncreaseQualityToMoreThanFifty() {
		assertionFactory
				.update(new Item(BACKSTAGE_PASS_ITEM_NAME, FIVE_DAYS, 50))
				.andExpectQualityToBe(50);
	}
}
