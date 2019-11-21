package krsktilos.mathtest;

import krsktilos.math.Money;
import krsktilos.math.Quantity;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author krsktilos
 */
public class MoneyTest {

    @Test
    public void constructorsTest() {
        Money money;

        money = new Money(new BigDecimal(125.479));
        System.out.println(money);
        assertEquals("125.48", money.toString());
        assertEquals(new Money(125.48D), money);

        money = new Money("125.479");
        System.out.println(money);
        assertEquals("125.48", money.toString());
        assertEquals(new Money("125.48"), money);

        money = new Money("129.4");
        System.out.println(money);
        assertEquals("129.40", money.toString());
        assertEquals(new Money(129.4), money);

        money = new Money(129.499f);
        System.out.println(money);
        assertEquals("129.50", money.toString());
        assertEquals(new Money(129.5000f), money);

        money = new Money(299);
        System.out.println(money);
        assertEquals("299.00", money.toString());
        assertEquals(new Money("299"), money);

        money = new Money(100056L);
        System.out.println(money);
        assertEquals("100056.00", money.toString());
        assertEquals(new Money(100056L), money);
    }

    @Test
    public void equalsTest() {
        Money money = new Money(15.55);
        assertEquals(new Money(15.55), money);
        assertEquals(new Money(15.554), money);
        assertEquals(new Money(15.549), money);
    }

    @Test
    public void arithmeticTest() {
        Money money = new Money(10500.45);

        Money result;
        result = money.add(new Money(24.043));
        System.out.println(result);
        assertEquals(new Money(10500.45), money);
        assertEquals(new Money(10524.49), result);

        result = money.add(new Quantity(24.043));
        System.out.println(result);
        assertEquals(new Money(10500.45), money);
        assertEquals(new Money(10524.49), result);

        result = money.subtract(new Money(499.451));
        System.out.println(result);
        assertEquals(new Money(10500.45), money);
        assertEquals(new Money(10001), result);

        result = money.subtract(new Quantity(499.451));
        System.out.println(result);
        assertEquals(new Money(10500.45), money);
        assertEquals(new Money(10001), result);

        result = money.multiply(new Money(0.5));
        System.out.println(result);
        assertEquals(new Money(10500.45), money);
        assertEquals(new Money(5250.23), result);

        result = money.multiply(new Quantity(0.5));
        System.out.println(result);
        assertEquals(new Money(10500.45), money);
        assertEquals(new Money(5250.23), result);

        result = money.divide(new Money(1.5));
        System.out.println(result);
        assertEquals(new Money(10500.45), money);
        assertEquals(new Money(7000.30), result);

        result = money.divide(new Quantity(1.5));
        System.out.println(result);
        assertEquals(new Money(10500.45), money);
        assertEquals(new Money(7000.30), result);
    }

    @Test
    public void logicalOperationsTest() {
        Money money = new Money(501.55);

        assertTrue(money.lt(new Money(501.56)));
        assertFalse(money.lt(new Money(501.55)));
        assertFalse(money.lt(new Money(500)));

        assertTrue(money.le(new Money(501.55)));
        assertTrue(money.le(new Money(501.56)));
        assertFalse(money.le(new Money(501.54)));
        assertFalse(money.le(new Money(500)));

        assertTrue(money.eq(new Money(501.55)));
        assertTrue(money.eq(new Money(501.5499)));
        assertFalse(money.eq(new Money(501.54)));
        assertFalse(money.eq(new Money(501.56)));

        assertTrue(money.gt(new Money(501.54)));
        assertFalse(money.gt(new Money(501.55)));
        assertFalse(money.gt(new Money(501.56)));

        assertTrue(money.ge(new Money(501.55)));
        assertTrue(money.ge(new Money(501.54)));
        assertFalse(money.ge(new Money(501.56)));
    }

    @Test
    public void staticValuesTest() {
        assertEquals(Money.ZERO, new Money(0));

        Money zero = Money.ZERO;
        assertTrue(zero.isZero());
        assertFalse(zero.isNotZero());

        Money money = new Money(0.01);
        assertTrue(money.isNotZero());
        assertFalse(money.isZero());

        assertEquals(Money.HUNDRED, new Money(100));

        Money hundred = Money.HUNDRED;
        assertTrue(hundred.isNotZero());
        assertFalse(hundred.isZero());

        assertTrue(hundred.gt(new Money(99.99)));
        assertTrue(hundred.lt(new Money(100.99)));
        assertTrue(hundred.ge(Money.HUNDRED));
        assertTrue(hundred.le(Money.HUNDRED));
        assertTrue(hundred.ge(new Money(100)));
        assertTrue(hundred.le(new Money(100)));
    }

    @Test
    public void transientMethodsTest() {
        Money money = new Money(100.49);
        assertEquals(new Money(100), money.getInt());

        money = new Money(0.99);
        assertEquals(new Money(0), money.getInt());
    }

    @Test
    public void serializationTest() throws Exception {
        Money money = new Money(666.99);
        System.out.println(money);
        System.out.println(money.hashCode());

        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(byteOutputStream);
        outputStream.writeObject(money);
        outputStream.flush();

        ObjectInputStream inputStream = new ObjectInputStream(
                new ByteArrayInputStream(byteOutputStream.toByteArray())
        );

        Money restoredMoney = (Money) inputStream.readObject();

        outputStream.close();
        inputStream.close();

        System.out.println(restoredMoney);
        System.out.println(restoredMoney.hashCode());
        assertEquals(money, restoredMoney);
    }
}
