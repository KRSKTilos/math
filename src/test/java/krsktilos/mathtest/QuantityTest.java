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
public class QuantityTest {

    @Test
    public void constructorsTest() {
        Quantity quantity;

        quantity = new Quantity(new BigDecimal(125.4799));
        System.out.println(quantity);
        assertEquals("125.480", quantity.toString());
        assertEquals(new Quantity(125.480D), quantity);

        quantity = new Quantity("125.4799");
        System.out.println(quantity);
        assertEquals("125.480", quantity.toString());
        assertEquals(new Quantity("125.480"), quantity);

        quantity = new Quantity("129.4");
        System.out.println(quantity);
        assertEquals("129.400", quantity.toString());
        assertEquals(new Quantity(129.4), quantity);

        quantity = new Quantity(129.4999f);
        System.out.println(quantity);
        assertEquals("129.500", quantity.toString());
        assertEquals(new Quantity(129.5000f), quantity);

        quantity = new Quantity(299);
        System.out.println(quantity);
        assertEquals("299.000", quantity.toString());
        assertEquals(new Quantity("299"), quantity);

        quantity = new Quantity(100056L);
        System.out.println(quantity);
        assertEquals("100056.000", quantity.toString());
        assertEquals(new Quantity(100056L), quantity);
    }

    @Test
    public void equalsTest() {
        Quantity quantity = new Quantity(15.5555);
        assertEquals(new Quantity(15.5555), quantity);
        assertEquals(new Quantity(15.556), quantity);
        assertEquals(new Quantity(15.5559), quantity);
    }

    @Test
    public void arithmeticTest() {
        Quantity quantity = new Quantity(10.555);

        Quantity result;
        result = quantity.add(new Quantity(1.001));
        System.out.println(result);
        assertEquals(new Quantity(10.555), quantity);
        assertEquals(new Quantity(11.556), result);

        result = quantity.add(new Money(1.01));
        System.out.println(result);
        assertEquals(new Quantity(10.555), quantity);
        assertEquals(new Quantity(11.565), result);

        result = quantity.subtract(new Quantity(1.111));
        System.out.println(result);
        assertEquals(new Quantity(10.555), quantity);
        assertEquals(new Quantity(9.444), result);

        result = quantity.subtract(new Money(1.11));
        System.out.println(result);
        assertEquals(new Quantity(10.555), quantity);
        assertEquals(new Quantity(9.445), result);

        result = quantity.multiply(new Quantity(0.5));
        System.out.println(result);
        assertEquals(new Quantity(10.555), quantity);
        assertEquals(new Quantity(5.278), result);

        result = quantity.multiply(new Money(0.5));
        System.out.println(result);
        assertEquals(new Quantity(10.555), quantity);
        assertEquals(new Quantity(5.278), result);

        result = quantity.divide(new Quantity(1.5));
        System.out.println(result);
        assertEquals(new Quantity(10.555), quantity);
        assertEquals(new Quantity(7.037), result);

        result = quantity.divide(new Money(1.5));
        System.out.println(result);
        assertEquals(new Quantity(10.555), quantity);
        assertEquals(new Quantity(7.037), result);
    }

    @Test
    public void logicalOperationsTest() {
        Quantity quantity = new Quantity(1.500);

        assertTrue(quantity.lt(new Quantity(1.501)));
        assertFalse(quantity.lt(new Quantity(1.500)));
        assertFalse(quantity.lt(new Quantity(1.499)));

        assertTrue(quantity.le(new Quantity(1.500)));
        assertTrue(quantity.le(new Quantity(1.4999)));
        assertFalse(quantity.le(new Quantity(1.499)));
        assertFalse(quantity.le(new Quantity(1)));

        assertTrue(quantity.eq(new Quantity(1.500)));
        assertTrue(quantity.eq(new Quantity(1.4999)));
        assertFalse(quantity.eq(new Quantity(1.5009)));
        assertFalse(quantity.eq(new Quantity(1.4994)));

        assertTrue(quantity.gt(new Quantity(1.499)));
        assertFalse(quantity.gt(new Quantity(1.4999)));
        assertFalse(quantity.gt(new Quantity(2)));

        assertTrue(quantity.ge(new Quantity(1.500)));
        assertTrue(quantity.ge(new Quantity(1.4999)));
        assertFalse(quantity.ge(new Quantity(1.501)));
    }

    @Test
    public void staticValuesTest() {
        assertEquals(Quantity.ONE, new Quantity(1));
        assertEquals(Quantity.ZERO, new Quantity(0));

        Quantity zero = new Quantity(0);
        assertTrue(zero.isZero());
        assertFalse(zero.isNotZero());

        Quantity quantity = new Quantity(0.001);
        assertTrue(quantity.isNotZero());
        assertFalse(quantity.isZero());
    }

    @Test
    public void transientMethodsTest() {
        Quantity quantity = new Quantity(100.499);
        assertEquals(new Quantity(100), quantity.getInt());

        quantity = new Quantity(0.999);
        assertEquals(new Quantity(0), quantity.getInt());
    }

    @Test
    public void serializationTest() throws Exception {
        Quantity quantity = new Quantity(0.997);
        System.out.println(quantity);
        System.out.println(quantity.hashCode());

        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(byteOutputStream);
        outputStream.writeObject(quantity);
        outputStream.flush();

        ObjectInputStream inputStream = new ObjectInputStream(
                new ByteArrayInputStream(byteOutputStream.toByteArray())
        );

        Quantity restoredQuantity = (Quantity) inputStream.readObject();

        outputStream.close();
        inputStream.close();

        System.out.println(restoredQuantity);
        System.out.println(restoredQuantity.hashCode());
        assertEquals(quantity, restoredQuantity);
    }
}
