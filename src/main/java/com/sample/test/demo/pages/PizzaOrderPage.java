package com.sample.test.demo.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/*
 * Pizza Order Page Factory.
 */
public class PizzaOrderPage {
    
    WebDriver driver;

    @FindBy(id = "pizza1Pizza")
    WebElement pizzaSelector;

    @FindBy(xpath = "//div[@id='pizza1']//select[@class='toppings1']")
    WebElement pizzaTopping1Selector;

    @FindBy(xpath = "//div[@id='pizza1']//select[@class='toppings2']")
    WebElement pizzaTopping2Selector;
    
    @FindBy(id = "pizza1Qty")
    WebElement pizzaQuantityTextBox;
    
    @FindBy(id = "pizza1Cost")
    WebElement pizzaCostTextBox;

    @FindBy(id = "email")
    WebElement emailTextBox;

    @FindBy(id = "name")
    WebElement nameTextBox;

    @FindBy(id = "phone")
    WebElement phoneTextBox;

    @FindBy(id = "ccpayment")
    WebElement creditCardPaymentRadioBtn;

    @FindBy(id = "cashpayment")
    WebElement cashPaymentRadioBtn;
    
    @FindBy(id = "placeOrder")
    WebElement placeorderBtn;

    @FindBy(id = "reset")
    WebElement resetBtn;

    @FindBy(xpath = "//div[@id='dialog']/p")
    WebElement dialogTextBlock;

    @FindBy(xpath = ".//*[@class='ui-button-icon ui-icon ui-icon-closethick']")
	private WebElement closeDialogBtn;

    public PizzaOrderPage(WebDriver driver) 
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectPizza(String pizzaDisplayName)
    {
       Select select = new Select(pizzaSelector);
       select.selectByValue(pizzaDisplayName);       
    }

    public void selectPizzaTopping1(String toppingDisplayName)
    {
       Select select = new Select(pizzaTopping1Selector);
       select.selectByValue(toppingDisplayName);       
    }

    public void selectPizzaTopping2(String toppingDisplayName)
    {
       Select select = new Select(pizzaTopping2Selector);
       select.selectByValue(toppingDisplayName);       
    }

    public void setName(String name)
    {
       nameTextBox.sendKeys(name);
    }

    public void setPhone(String phone)
    {
       phoneTextBox.sendKeys(phone);
    }

    public void setEmail(String email)
    {
       emailTextBox.sendKeys(email);
    }

    public void setPizzaQuantity(String quantity)
    {
       pizzaQuantityTextBox.sendKeys(quantity);
    }

    public void chooseCreditCardPayment()
    {
       creditCardPaymentRadioBtn.click();
    }

    public boolean isCreditCardPaymentSelected() 
    {
      return creditCardPaymentRadioBtn.isSelected();
    }

    public void chooseCashPayment()
    {
       cashPaymentRadioBtn.click();
    }

    public boolean isCashPaymentSelected() 
    {
      return cashPaymentRadioBtn.isSelected();
    }

    public String getDialogText()
    {
        return dialogTextBlock.getText();
    }

    public void placeOrder()
    {
       placeorderBtn.click();
    }

    public void resetOrder()
    {
       resetBtn.click();
    }

    public void closeDialog()
    {
       closeDialogBtn.click();
    }

    public List<String> getPizzaOptions()
    {
        return getOptions(new Select(pizzaSelector));
    }

    public List<String> getPizzaTopping1Options()
    {
        return getOptions(new Select(pizzaTopping1Selector));
    }

    public List<String> getPizzaTopping2Options()
    {
        return getOptions(new Select(pizzaTopping2Selector));
    }

    public String getCost()
    {
        return pizzaCostTextBox.getAttribute("value");
    }

    public boolean isCostReadOnly()
    {
        return Boolean.parseBoolean(pizzaCostTextBox.getAttribute("readonly"));
    }

    private List<String> getOptions(Select select)
    {
        List<WebElement> options = select.getOptions();
        List<String> optionsDisplayNames = new ArrayList<String>();
 
        for (WebElement option : options) {
            optionsDisplayNames.add(option.getText());
        }
        return optionsDisplayNames;
    }
    
    
}
