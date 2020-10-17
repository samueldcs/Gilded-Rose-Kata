package org.gildedrose.qualitycontrol;

import org.gildedrose.Item;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class QualityControlAssertionFactory {
	private final QualityControl qualityControl;
	
	public QualityControlAssertionFactory(final QualityControl control) {
		this.qualityControl = control;
	}
	
	public QualityControlAssertion update(Item item) {
		return new QualityControlAssertion(qualityControl, item);
	}
	
	static class QualityControlAssertion {
		
		private final QualityControl qualityControl;
		private final Item item;
		
		public QualityControlAssertion(final QualityControl qualityControl, final Item item) {
			this.qualityControl = qualityControl;
			this.item = item;
		}
		
		public void andExpectQualityToBe(Integer number) {
			var itemWithUpdatedQuality = qualityControl.updateQuality(this.item);
			
			assertThat(itemWithUpdatedQuality.getQuality(), is(number));
		}
	}
}
