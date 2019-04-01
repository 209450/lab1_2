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

    @Test public void multiplyByDouble3() {
        assertThat("300.00 €", is(money.multiplyBy(3d).toString()));
    }

    @Test public void multiplyByNegativeDouble3() {
        money.multiplyBy(-3d);
        assertThat("100.00 €", is(money.toString()));
    }

    @Test public void greaterThanForBiggerBasicValueWithCompatibleCurrency() {
        assertThat(true,is(money.greaterThan(moneySecound)));
    }

    @Test public void greaterThanForLowerBasicValueWithCompatibleCurrency() {
        assertThat(false,is(moneySecound.greaterThan(money)));
    }

    @Test public void lessThanForBiggerBasicValueWithCompatibleCurrency() {
        assertThat(false,is(money.lessThan(moneySecound)));
    }

    @Test public void lessThanForLowerBasicValueWithCompatibleCurrency() {
        assertThat(true,is(moneySecound.lessThan(money)));
    }

    @Test public void lessOrEqualWithCompatibleCurrency() {
        assertThat(true,is(moneySecound.lessOrEquals(money)));
        moneySecound = new Money(100,"EUR");
        assertThat(true,is(moneySecound.lessOrEquals(money)));
    }

    @Test public void lessOrEqualWithNotCompatibleCurrency() {
        assertThat(true,is(money.lessOrEquals(otherMoney)));
    }
}
