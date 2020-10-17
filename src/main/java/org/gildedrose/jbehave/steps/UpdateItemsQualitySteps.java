package org.gildedrose.jbehave.steps;

import org.gildedrose.GildedRose;
import org.gildedrose.Item;
import org.gildedrose.qualitycontrol.QualityControlFactory;
import org.gildedrose.sellincontrol.SellInControl;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.gildedrose.builder.ItemBuilder.anItem;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UpdateItemsQualitySteps {
	
	private Item item;
	
	private List<Item> modifiedItens;
	
	private GildedRose gildedRose;
	
	@BeforeStory
	public void beforeStoryDo() {
		gildedRose = new GildedRose(new QualityControlFactory(), new SellInControl());
	}
	
	@Given("an item named <name>, with sell in <sellIn> days and quality <quality>")
	public void givenAnItemWithNameSellInAndQualitySetTo(@Named("name") String name,
														 @Named("sellIn") int sellIn, @Named("quality") int quality) {
		this.item = anItem()
				.withName(name)
				.withSellIn(sellIn)
				.withQuality(quality).build();
	}
	
	@When("the quality of the item is updated after one day")
	public void whenTheQualityOfTheItemIsUpdatedAfterOneDay() {
		this.modifiedItens = gildedRose.updateQuality(List.of(item));
	}
	
	@Then("sell in should be <newSellIn>")
	public void thenSellInShouldBe(@Named("newSellIn") int newSellIn) {
		assertThat(modifiedItens.get(0).getSellIn(), is(newSellIn));
	}
	
	@Then("collection size should not change")
	public void thenCollectionSizeShouldNotChange() {
		assertThat(modifiedItens.size(), is(1));
	}
	
	@Then("quality should be <newQuality>")
	public void thenQualityShouldBe(@Named("newQuality") int newQuality) {
		assertThat(modifiedItens.get(0).getQuality(), is(newQuality));
	}
	
}
