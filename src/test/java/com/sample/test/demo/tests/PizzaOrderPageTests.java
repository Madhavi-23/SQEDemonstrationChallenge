package com.sample.test.demo.tests;

import java.util.List;

import org.testng.annotations.Test;
import com.sample.test.demo.TestBase;
import com.sample.test.demo.constants.PizzaToppings;
import com.sample.test.demo.constants.PizzaTypes;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertEquals;

public class PizzaOrderPageTests extends TestBase {

   @Test
    public void validate_pizza_options() {
        List<String> pizzaOptions = this.pizzaOrderPage.getPizzaOptions();
        assertEquals(PizzaTypes.values().length + 1, pizzaOptions.size());
        
        assertTrue(pizzaOptions.contains("Choose Pizza"));
        for (PizzaTypes pizzaType : PizzaTypes.values()) { 
            assertTrue(pizzaOptions.contains(String.format("%s $%.2f", pizzaType.getDisplayName(), pizzaType.getCost())));
        }
    }

    @Test
    public void validate_pizza_firsttopping_options() {
        List<String> pizzaTopping1Options = this.pizzaOrderPage.getPizzaTopping1Options();
        assertEquals(PizzaToppings.values().length + 1, pizzaTopping1Options.size());
        
        assertTrue(pizzaTopping1Options.contains("Choose a Toppings 1"));
        for (PizzaToppings pizzaTopping : PizzaToppings.values()) { 
            assertTrue(pizzaTopping1Options.contains(pizzaTopping.getDisplayName()));
        }
    }

    @Test
    public void validate_pizza_secondtopping_options() {
        List<String> pizzaTopping1Options = this.pizzaOrderPage.getPizzaTopping2Options();
        assertEquals(PizzaToppings.values().length + 1, pizzaTopping1Options.size());
        
        assertTrue(pizzaTopping1Options.contains("Choose a Toppings 2"));
        for (PizzaToppings pizzaTopping : PizzaToppings.values()) { 
            assertTrue(pizzaTopping1Options.contains(pizzaTopping.getDisplayName()));
        }
    } 

    @Test
    public void order_successful_when_all_required_inputs_specified() {
    	
        int quantity = 2;
        this.pizzaOrderPage.selectPizza(PizzaTypes.MEDIUM_TWOTOPPINGS.getDisplayName());
        this.pizzaOrderPage.selectPizzaTopping1(PizzaToppings.MOZZARELLA.getDisplayName());
        this.pizzaOrderPage.selectPizzaTopping2(PizzaToppings.ITALIANHAM.getDisplayName());
	    this.pizzaOrderPage.setPizzaQuantity("2");
	    this.pizzaOrderPage.setName("Test User");
        this.pizzaOrderPage.setPhone("111-111-1111");
        this.pizzaOrderPage.setEmail("someone@gmail.com");
        this.pizzaOrderPage.chooseCreditCardPayment();    

        assertEquals("19.5", this.pizzaOrderPage.getCost());
        assertTrue(this.pizzaOrderPage.isCostReadOnly());
        
        this.pizzaOrderPage.placeOrder();
    	String alertDialogText = this.pizzaOrderPage.getDialogText();
        System.out.println((alertDialogText));
        System.out.println(String.format("Thank you for your order! TOTAL: %.2f %s", quantity * PizzaTypes.MEDIUM_TWOTOPPINGS.getCost(), PizzaTypes.MEDIUM_TWOTOPPINGS.getDisplayName()));
        assertTrue(alertDialogText.contains(
            String.format("Thank you for your order! TOTAL: %.1f %s", quantity * PizzaTypes.MEDIUM_TWOTOPPINGS.getCost(), PizzaTypes.MEDIUM_TWOTOPPINGS.getDisplayName())));        

        this.pizzaOrderPage.closeDialog();
    }

    @Test
    public void order_fails_when_mandatory_fields_missing() {
    	
        this.pizzaOrderPage.selectPizza(PizzaTypes.MEDIUM_TWOTOPPINGS.getDisplayName());
        this.pizzaOrderPage.selectPizzaTopping1(PizzaToppings.MOZZARELLA.getDisplayName());
        this.pizzaOrderPage.selectPizzaTopping2(PizzaToppings.ITALIANHAM.getDisplayName());
	    this.pizzaOrderPage.setPizzaQuantity("2");
        this.pizzaOrderPage.setEmail("someone@gmail.com");
        this.pizzaOrderPage.chooseCreditCardPayment();    

        assertEquals("19.5", this.pizzaOrderPage.getCost());
        assertTrue(this.pizzaOrderPage.isCostReadOnly());
        
        this.pizzaOrderPage.placeOrder();
        
        String alertDialogText = this.pizzaOrderPage.getDialogText();    	
        assertTrue(alertDialogText.contains("Missing name"));
        assertTrue(alertDialogText.contains("Missing phone number"));
        assertFalse(alertDialogText.contains("Thank you for your order"));

        this.pizzaOrderPage.closeDialog();
    }

    // Below test cases fail due to defects in code.

    @Test 
    public void validate_only_one_payment_instrument_can_be_selected() {
    	
        this.pizzaOrderPage.chooseCreditCardPayment();
        this.pizzaOrderPage.chooseCashPayment();
    	
        assertTrue(this.pizzaOrderPage.isCashPaymentSelected());
        // Below line fails due to defect in code.
    	assertFalse(this.pizzaOrderPage.isCreditCardPaymentSelected()); 
        
    	this.pizzaOrderPage.closeDialog();    	
    }

   
}
