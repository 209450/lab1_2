package pl.com.bottega.ecommerce.sharedkernel;

import org.junit.Before;
import org.junit.Test;

import java.util.Currency;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MoneyTest {

    private Money money;
    private Money otherMoney;
    private Money moneySecound;

    @Before public void setUp() throws Exception {
        money = new Money(100, "EUR");
        moneySecound = new Money(10,"EUR");
        otherMoney = new Money(1000, "PL");
    }

    @Test(expected = IllegalArgumentException.class) public void addIncompatibleCurrency() {
        money.add(otherMoney);
    }

    @Test public void addMoneyWithCompatibleCurrency() {
        Money result = money.add(moneySecound);
        assertThat("110.00 €", is(result.toString()));
    }

    @Test(expected = IllegalArgumentException.class) public void subtractIncompatibleCurrency() {
        money.subtract(otherMoney);
    }

    @Test public void subtractMoneyWithCompatibleCurrency() {
        Money result = money.subtract(moneySecound);
        assertThat("90.00 €", is(result.toString()));
    }

    @Test(expected = IllegalArgumentException.class) public void subtractTooBigValue() {
        moneySecound = new Money(1000,"UER");
        Money result = money.subtract(moneySecound);
    }


}
