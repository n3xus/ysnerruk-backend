package com.ysnerruk.api.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.ysnerruk.api.exception.ApiException;
import com.ysnerruk.api.model.Offer;
import com.ysnerruk.api.model.Role;
import com.ysnerruk.api.model.User;
import com.ysnerruk.api.repository.MessageRepository;
import com.ysnerruk.api.repository.OfferRepository;

@RunWith(SpringRunner.class)
public class OfferServiceTest {
	
	@InjectMocks
	private OfferService service;
	
	@Mock
	private OfferRepository offerRepository;
	
	@Mock
	private MessageRepository messageRepository;
	
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	// Create a user
	protected User createUser(String username, Integer id) {
		User user = new User();
		user.setId(id);
		user.setUsername(username);
		user.setPassword("pass");
		user.setEmail(username + "@mail.com");
		List<Role> roles = new ArrayList<Role>();
		roles.add(Role.ROLE_CLIENT);
		user.setRoles(roles);
		return user;
	}
	
	@Test
	public void testGetOffer() {
		// Create Offer
		Offer offerA = new Offer();
		Integer offerIdA = 3;
		offerA.setId(offerIdA);
		String descA= "desc a";
		Integer offerAmountA = Integer.valueOf(10);
		Integer receiveAmountA = Integer.valueOf(12);
		String receiveCurrencyA = "AUD";
		String offerCurrencyA = "USD";
		offerA.setDescription(descA);
		offerA.setOfferAmount(offerAmountA);
		offerA.setReceiveAmount(receiveAmountA);
		offerA.setReceiveCurrency(receiveCurrencyA);
		offerA.setOfferCurrency(offerCurrencyA);
		
		when(offerRepository.findById(offerIdA)).thenReturn(Optional.of(offerA));
		Offer ret = service.getOffer(offerIdA);
		
		assertEquals(offerA, ret);
	}

	@Test
	public void testGetNonExistentOffer() {
		// Create Offer
		Offer offerA = new Offer();
		Integer offerIdA = 3;
		offerA.setId(offerIdA);
		String descA= "desc a";
		Integer offerAmountA = Integer.valueOf(10);
		Integer receiveAmountA = Integer.valueOf(12);
		String receiveCurrencyA = "AUD";
		String offerCurrencyA = "USD";
		offerA.setDescription(descA);
		offerA.setOfferAmount(offerAmountA);
		offerA.setReceiveAmount(receiveAmountA);
		offerA.setReceiveCurrency(receiveCurrencyA);
		offerA.setOfferCurrency(offerCurrencyA);
		
		when(offerRepository.findById(offerIdA + 1)).thenReturn(Optional.empty());
		try {
			Offer ret = service.getOffer(offerIdA);
		} catch (ApiException ae) {
			// Pass
		} catch (Exception e) { 
			fail("Wrong Exception");
		}
	}
	
	@Test
	public void testGetNullOffer() {
		// Create Offer
		Offer offerA = new Offer();
		Integer offerIdA = 3;
		offerA.setId(offerIdA);
		String descA= "desc a";
		Integer offerAmountA = Integer.valueOf(10);
		Integer receiveAmountA = Integer.valueOf(12);
		String receiveCurrencyA = "AUD";
		String offerCurrencyA = "USD";
		offerA.setDescription(descA);
		offerA.setOfferAmount(offerAmountA);
		offerA.setReceiveAmount(receiveAmountA);
		offerA.setReceiveCurrency(receiveCurrencyA);
		offerA.setOfferCurrency(offerCurrencyA);
		
		when(offerRepository.findById(offerIdA + 1)).thenReturn(Optional.empty());
		try {
			Offer ret = service.getOffer(offerIdA);
		} catch (ApiException ae) {
			// Pass
		} catch (Exception e) { 
			fail("Wrong Exception");
		}
	}
	
	@Test
	public void testGetOfferNullId() {
		// Create Offer
		Offer offerA = new Offer();
		Integer offerIdA = null;
		offerA.setId(offerIdA);
		String descA= "desc a";
		
		try {
			service.getOffer(offerIdA);
		} catch (ApiException ae) {
			// Pass
		} catch (Exception e) { 
			fail("Wrong Exception");
		}
	}
	
	
	@Test
	public void testCreateOffer() {
		User sender = createUser("userA", 1);
		
		// Create Offer
		Offer offerA= new Offer();
		Integer offerIdA = 3;
		offerA.setId(offerIdA);
		String descA= "desc a";
		Integer offerAmountA = Integer.valueOf(10);
		Integer receiveAmountA = Integer.valueOf(12);
		String receiveCurrencyA = "AUD";
		String offerCurrencyA = "USD";
		offerA.setDescription(descA);
		offerA.setOfferAmount(offerAmountA);
		offerA.setReceiveAmount(receiveAmountA);
		offerA.setReceiveCurrency(receiveCurrencyA);
		offerA.setOfferCurrency(offerCurrencyA);
		
		Integer id = service.createOffer(sender, offerA);
		
		assertEquals(offerIdA, id);
	}	
	
	@Test
	public void testCreateOfferBadReceiveCurrency() {
		User sender = createUser("userA", 1);
		
		// Create Offer
		Offer offerA= new Offer();
		Integer offerIdA = 3;
		offerA.setId(offerIdA);
		String descA= "desc a";
		Integer offerAmountA = Integer.valueOf(10);
		Integer receiveAmountA = Integer.valueOf(12);
		String receiveCurrencyA = "";
		String offerCurrencyA = "USD";
		offerA.setDescription(descA);
		offerA.setOfferAmount(offerAmountA);
		offerA.setReceiveAmount(receiveAmountA);
		offerA.setReceiveCurrency(receiveCurrencyA);
		offerA.setOfferCurrency(offerCurrencyA);
		
		try {
			service.createOffer(sender, offerA);
		} catch (ApiException ae) {
			// Pass
		} catch (Exception e) {
			fail("Exception should be ApiException");
		}
	}
	
	@Test
	public void testCreateOfferBadOfferCurrency() {
		User sender = createUser("userA", 1);
		
		// Create Offer
		Offer offerA= new Offer();
		Integer offerIdA = 3;
		offerA.setId(offerIdA);
		String descA= "desc a";
		Integer offerAmountA = Integer.valueOf(10);
		Integer receiveAmountA = Integer.valueOf(12);
		String offerCurrencyA = "";
		String receiveCurrencyA = "USD";
		offerA.setDescription(descA);
		offerA.setOfferAmount(offerAmountA);
		offerA.setReceiveAmount(receiveAmountA);
		offerA.setReceiveCurrency(receiveCurrencyA);
		offerA.setOfferCurrency(offerCurrencyA);
		
		try {
			service.createOffer(sender, offerA);
		} catch (ApiException ae) {
			// Pass
		} catch (Exception e) {
			fail("Exception should be ApiException");
		}
	}
	
	@Test
	public void testCreateOfferBadReceiveAmount() {
		User sender = createUser("userA", 1);
		
		// Create Offer
		Offer offerA= new Offer();
		Integer offerIdA = 3;
		offerA.setId(offerIdA);
		String descA= "desc a";
		Integer offerAmountA = Integer.valueOf(10);
		Integer receiveAmountA = Integer.valueOf(0);
		String receiveCurrencyA = "AUD";
		String offerCurrencyA = "USD";
		offerA.setDescription(descA);
		offerA.setOfferAmount(offerAmountA);
		offerA.setReceiveAmount(receiveAmountA);
		offerA.setReceiveCurrency(receiveCurrencyA);
		offerA.setOfferCurrency(offerCurrencyA);
		
		try {
			service.createOffer(sender, offerA);
		} catch (ApiException ae) {
			// Pass
		} catch (Exception e) {
			fail("Exception should be ApiException");
		}
	}
	
	@Test
	public void testCreateOfferBadOfferAmount() {
		User sender = createUser("userA", 1);
		
		// Create Offer
		Offer offerA= new Offer();
		Integer offerIdA = 3;
		offerA.setId(offerIdA);
		String descA= "desc a";
		Integer offerAmountA = Integer.valueOf(0);
		Integer receiveAmountA = Integer.valueOf(10);
		String receiveCurrencyA = "AUD";
		String offerCurrencyA = "USD";
		offerA.setDescription(descA);
		offerA.setOfferAmount(offerAmountA);
		offerA.setReceiveAmount(receiveAmountA);
		offerA.setReceiveCurrency(receiveCurrencyA);
		offerA.setOfferCurrency(offerCurrencyA);
		
		try {
			service.createOffer(sender, offerA);
		} catch (ApiException ae) {
			// Pass
		} catch (Exception e) {
			fail("Exception should be ApiException");
		}
	}
	
	
	@Test
	public void testCreateOfferShortDescription() {
		User sender = createUser("userA", 1);
		
		// Create Offer
		Offer offerA= new Offer();
		Integer offerIdA = 3;
		offerA.setId(offerIdA);
		String descA= "";
		Integer offerAmountA = Integer.valueOf(10);
		Integer receiveAmountA = Integer.valueOf(10);
		String receiveCurrencyA = "AUD";
		String offerCurrencyA = "USD";
		offerA.setDescription(descA);
		offerA.setOfferAmount(offerAmountA);
		offerA.setReceiveAmount(receiveAmountA);
		offerA.setReceiveCurrency(receiveCurrencyA);
		offerA.setOfferCurrency(offerCurrencyA);
		
		try {
			service.createOffer(sender, offerA);
		} catch (ApiException ae) {
			// Pass
		} catch (Exception e) {
			fail("Exception should be ApiException");
		}
	}
	
	@Test
	public void testCreateOfferLongDescription() {
		User sender = createUser("userA", 1);
		
		// Create Offer
		Offer offerA = new Offer();
		Integer offerIdA = 3;
		offerA.setId(offerIdA);
		String descA = "1";
		for (int i = 0; i < 100; i++) {
			descA = descA + "12345";
		}
		
		Integer offerAmountA = Integer.valueOf(10);
		Integer receiveAmountA = Integer.valueOf(10);
		String receiveCurrencyA = "AUD";
		String offerCurrencyA = "USD";
		offerA.setDescription(descA);
		offerA.setOfferAmount(offerAmountA);
		offerA.setReceiveAmount(receiveAmountA);
		offerA.setReceiveCurrency(receiveCurrencyA);
		offerA.setOfferCurrency(offerCurrencyA);
		
		try {
			service.createOffer(sender, offerA);
		} catch (ApiException ae) {
			// Pass
		} catch (Exception e) {
			fail("Exception should be ApiException");
		}
	}
	
	@Test
	public void testCreateOfferBadSender() {
		User sender = null;
		
		Offer offerA= new Offer();
		Integer offerIdA = 3;
		offerA.setId(offerIdA);
		String descA= "desc a";
		Integer offerAmountA = Integer.valueOf(10);
		Integer receiveAmountA = Integer.valueOf(12);
		String receiveCurrencyA = "AUD";
		String offerCurrencyA = "USD";
		offerA.setDescription(descA);
		offerA.setOfferAmount(offerAmountA);
		offerA.setReceiveAmount(receiveAmountA);
		offerA.setReceiveCurrency(receiveCurrencyA);
		offerA.setOfferCurrency(offerCurrencyA);
		
		try {
			service.createOffer(sender, offerA);
		} catch (ApiException ae) {
			verify(offerRepository, times(0)).save(offerA);
		} catch (Exception e) {
			fail("Null sender should result in failure");
		}
	}
	
	@Test
	public void testCreateBadOffer() {
		User sender = createUser("userA", 1);
		Offer offerA = null;
		
		try {
			service.createOffer(sender, offerA);
		} catch (ApiException ae) {
			verify(offerRepository, times(0)).save(offerA);
		} catch (Exception e) {
			fail("Null sender should result in failure");
		}
	}
	
	@Test
	public void testGetOffers() {
		Offer offerA = new Offer();
		Offer offerB = new Offer();
		List<Offer> offers = new ArrayList<Offer>();
		offers.add(offerA);
		offers.add(offerB);
		
		when(offerRepository.findAll()).thenReturn(offers);
		
		List<Offer> ret = service.listOffers();
		
		assertEquals(offers, ret);
	}
	
	@Test
	public void testAcceptOffer() {
		User sender = createUser("userA", 1);
		User maker = createUser("userB", 2);
		Offer offerA = new Offer();
		Integer offerIdA = 5;
		offerA.setId(offerIdA);
		offerA.setMaker(maker);
		
		when(offerRepository.findById(offerIdA)).thenReturn(Optional.of(offerA));
		
		try {
			service.acceptOffer(sender, offerIdA);
		} catch (Exception e) {
			fail("Offer should accept successfully");
		}
		verify(offerRepository, times(1)).save(offerA);
	}
	
	@Test
	public void testAcceptOfferNullSender() {
		User sender = null;
		Offer offerA = new Offer();
		Integer offerIdA = 5;
		offerA.setId(offerIdA);
		offerA.setMaker(sender);
		
		when(offerRepository.findById(offerIdA)).thenReturn(Optional.of(offerA));
		
		try {
			service.acceptOffer(sender, offerIdA);
		} catch (ApiException ae) {
			verify(offerRepository, times(0)).save(offerA);
		} catch (Exception e) {
			fail("Wrong type of exception");
		}
	}
	
	@Test
	public void testDeleteOffer() {
		User sender = createUser("userA", 1);
		Offer offerA = new Offer();
		Integer offerIdA = 5;
		offerA.setId(offerIdA);
		offerA.setMaker(sender);
		
		when(offerRepository.findById(offerIdA)).thenReturn(Optional.of(offerA));
		service.deleteOffer(sender, offerIdA);
		// Verify delete call called
		verify(offerRepository, times(1)).delete(offerA);
	}
}
