package pl.com.bottega.ecommerce.sharedkernel;

import org.junit.Before;
import org.junit.Test;

import java.util.Currency;

import static org.junit.Assert.*;

public class MoneyTest {

    private Money money;
    private Money otherMoney;

    @Before public void setUp() throws Exception {
        money = new Money(100, "EUR");
        otherMoney = new Money(1000, "PL");
    }

    @Test(expected = IllegalArgumentException.class) public void addIncompatibleCurrency() {
        money.add(otherMoney);
    }
}
