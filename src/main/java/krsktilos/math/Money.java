package krsktilos.math;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Денежная величина.
 * Округление в большую сторону с точностью до 2х знаков.
 * @author krsktilos
 */
public final class Money implements Serializable {
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
    private static final int SCALE = 2;

    public static final Money HUNDRED = new Money(100);
    public static final Money ZERO = new Money(BigDecimal.ZERO);
    public static final Money MAX = new Money(1000000);

    private final BigDecimal value;

    public Money(BigDecimal value) {
        this.value = value.setScale(SCALE, ROUNDING_MODE);
    }

    public Money(Money value) {
        this(value.getValue());
    }

    public Money(String value) {
        this(new BigDecimal(value));
    }

    public Money(float value) {
        this(BigDecimal.valueOf(value));
    }

    public Money(double value) {
        this(BigDecimal.valueOf(value));
    }

    public Money(int value) {
        this(new BigDecimal(value));
    }

    public Money(long value) {
        this(new BigDecimal(value));
    }

    /**
     * Возвращает результат логической операции РАВНО (=).
     * @param money сравниваемая величина
     * @return результат
     */
    public boolean eq(Money money) {
        return getValue().compareTo(money.getValue()) == 0;
    }

    /**
     * Возвращает результат логической операции БОЛЬШЕ (>).
     * @param money сравниваемая величина
     * @return результат
     */
    public boolean gt(Money money) {
        return getValue().compareTo(money.getValue()) > 0;
    }

    /**
     * Возвращает результат логической операции БОЛЬШЕ ЛИБО РАВНО (>=).
     * @param money сравниваемая величина
     * @return результат
     */
    public boolean ge(Money money) {
        return getValue().compareTo(money.getValue()) >= 0;
    }

    /**
     * Возвращает результат логической операции МЕНЬШЕ (<).
     * @param money сравниваемая величина
     * @return результат
     */
    public boolean lt(Money money) {
        return getValue().compareTo(money.getValue()) < 0;
    }

    /**
     * Возвращает результат логической операции МЕНЬШЕ ЛИБО РАВНО (<=).
     * @param money сравниваемая величина
     * @return результат
     */
    public boolean le(Money money) {
        return getValue().compareTo(money.getValue()) <= 0;
    }

    /**
     * Арифметическая операция сложения денег.
     * @param money слагаемое
     * @return сумма
     */
    public Money add(Money money) {
        return valueOf(getValue().add(money.getValue()));
    }

    /**
     * Арифметическая операция сложения денег и количества {@link Quantity}.
     * @param quantity слагаемое
     * @return сумма
     */
    public Money add(Quantity quantity) {
        return valueOf(getValue().add(quantity.getValue()));
    }

    /**
     * Арифметическая операция вычитания денег.
     * @param value вычитаемое
     * @return разница
     */
    public Money subtract(Money value) {
        return valueOf(getValue().subtract(value.getValue()));
    }

    /**
     * Арифметическая операция вычитания количества {@link Quantity} из денег.
     * @param quantity вычитаемое
     * @return разница
     */
    public Money subtract(Quantity quantity) {
        return valueOf(getValue().subtract(quantity.getValue()));
    }

    /**
     * Арифметическая операция умножения денег.
     * @param value умножаемое
     * @return сумма
     */
    public Money multiply(Money value) {
        return valueOf(getValue().multiply(value.getValue()));
    }

    /**
     * Арифметическая операция умножения денег на количество {@link Quantity}.
     * @param quantity умножаемое
     * @return сумма
     */
    public Money multiply(Quantity quantity) {
        return valueOf(getValue().multiply(quantity.getValue()));
    }

    /**
     * Арифметическая операция деления денег.
     * @param value делитель
     * @return отношение
     */
    public Money divide(Money value) {
        return valueOf(getValue().divide(value.getValue(), SCALE, ROUNDING_MODE));
    }

    /**
     * Арифметическая операция деления денег на количество {@link Quantity}.
     * @param quantity делитель
     * @return отношение
     */
    public Money divide(Quantity quantity) {
        return valueOf(getValue().divide(quantity.getValue(), SCALE, ROUNDING_MODE));
    }

    /**
     * Возвращает базовое значение величины.
     * @return базовое значение {@link BigDecimal}
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * Упрощенное создание объекта.
     * @param value базовое значение
     * @return величина
     */
    private static Money valueOf(BigDecimal value) {
        return new Money(value);
    }

    /**
     * Возвращает результат проверки на эквивалентость нулю {@link Money#ZERO}.
     * @return результат
     */
    public boolean isZero() {
        return eq(ZERO);
    }

    /**
     * Возвращает результат проверки на несоответствие
     * нулевому {@link Money#ZERO} значению.
     * @return результат
     */
    public boolean isNotZero() {
        return !isZero();
    }

    /**
     * Возвращает целую часть величины.
     * @return целая часть величины
     */
    public Money getInt() {
        return new Money(getValue().intValue());
    }

    /**
     * Корректное сравнение возможно только между деньгами {@link Money}.
     * @param object сравниваемое
     * @return результат
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Money) {
            Money money = (Money) object;
            return this.eq(money);
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 12;
        int result = 4;
        result = prime * result + getValue().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return getValue().toString();
    }
}