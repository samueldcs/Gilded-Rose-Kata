package org.gildedrose;
import static org.gildedrose.builder.ItemBuilder.anItem;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;

import org.gildedrose.qualitycontrol.DefaultQualityControl;
import org.gildedrose.qualitycontrol.QualityControlFactory;
import org.gildedrose.sellincontrol.SellInControl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GildedRoseTest {
	
	@Mock
	private QualityControlFactory qualityControlFactory;
	
	@Mock
	private DefaultQualityControl qualityControl;
	
	@Mock
	private SellInControl sellInControl;
	
	private GildedRose gildedRose;
	
	private final Item item2 = anItem()
			.withName("Test Item 2")
			.withSellIn(0)
			.withSellIn(0)
			.build();
	
	private final Item item1 = anItem()
			.withName("Test Item")
			.withSellIn(0)
			.withSellIn(0)
			.build();
	
	@Before 
	public void initialise() {
		this.gildedRose = new GildedRose(qualityControlFactory, sellInControl);
		when(qualityControlFactory.qualityControlFor(Mockito.any(Item.class)))
				.thenReturn(qualityControl);
		
		when(qualityControl.updateQuality(any())).thenAnswer(i -> i.getArguments()[0]);
		when(sellInControl.updateSellInFor(any())).thenAnswer(i -> i.getArguments()[0]);
	}
	
	@Test public void
	shouldUpdateItemQuality() {
		gildedRose.updateQuality(List.of(item1, item2));
		
		verify(this.qualityControl, times(2)).updateQuality(any());
		verifyNoMoreInteractions(qualityControl);
	}

	@Test public void
	shouldUpdateItemSellIn() {
		gildedRose.updateQuality(List.of(item1, item2));
		
		verify(this.sellInControl, times(2)).updateSellInFor(any());
		verifyNoMoreInteractions(sellInControl);
	}
	
	
}
