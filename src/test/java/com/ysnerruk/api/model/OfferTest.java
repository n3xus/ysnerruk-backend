package com.ysnerruk.api.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class OfferTest {
	@Test
	public void testSetAndGetId() {
		Offer offer = new Offer();
		assertNull(offer.getId());
		Integer id = Integer.valueOf(10);
		offer.setId(id);
		assertEquals(id, offer.getId());
	}

	@Test
	public void testSetAndGetMaker() {
		Offer offer = new Offer();
		assertNull(offer.getMaker());
		User maker = new User();
		offer.setMaker(maker);
		assertEquals(maker, offer.getMaker());
	}
	
	@Test
	public void testSetAndGetTaker() {
		Offer offer = new Offer();
		assertNull(offer.getTaker());
		User taker = new User();
		offer.setTaker(taker);
		assertEquals(taker, offer.getTaker());
	}
	
	@Test
    public void testSetAndGetDescription() {
        Offer offer = new Offer();
		String testDescription = "aDescription";
        assertNull(offer.getDescription());
        offer.setDescription(testDescription);
        assertEquals(testDescription, offer.getDescription());
    }
	
	@Test
	public void testSetAndGetOfferAmount() {
		Offer offer = new Offer();
		assertNull(offer.getOfferAmount());
		Integer amount = Integer.valueOf(10);
		offer.setOfferAmount(amount);
		assertEquals(amount, offer.getOfferAmount());
	}
	
	@Test
	public void testSetAndGetReceiveAmount() {
		Offer offer = new Offer();
		assertNull(offer.getReceiveAmount());
		Integer amount = Integer.valueOf(10);
		offer.setReceiveAmount(amount);
		assertEquals(amount, offer.getReceiveAmount());
	}

	@Test
	public void testSetAndGetOfferCurrency() {
		Offer offer = new Offer();
		assertNull(offer.getOfferCurrency());
		String currency = new String("AUD");
		offer.setOfferCurrency(currency);
		assertEquals(currency, offer.getOfferCurrency());
	}
	
	@Test
	public void testSetAndGetReceiveCurrency() {
		Offer offer = new Offer();
		assertNull(offer.getReceiveCurrency());
		String currency = new String("AUD");
		offer.setReceiveCurrency(currency);
		assertEquals(currency, offer.getReceiveCurrency());
	}
}
