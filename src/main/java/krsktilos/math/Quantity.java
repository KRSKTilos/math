package krsktilos.math;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Количественная величина.
 * Округление в большую сторону с точностью до 3х знаков.
 * @author krsktilos
 */
public final class Quantity implements Serializable {
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
    private static final int SCALE = 3;

    public static final Quantity ZERO = new Quantity(BigDecimal.ZERO);
    public static final Quantity ONE = new Quantity(BigDecimal.ONE);

    private final BigDecimal value;

    public Quantity(BigDecimal value) {
        this.value = value.setScale(SCALE, ROUNDING_MODE);
    }

    public Quantity(String value) {
        this(new BigDecimal(value));
    }

    public Quantity(float value) {
        this(BigDecimal.valueOf(value));
    }

    public Quantity(double value) {
        this(BigDecimal.valueOf(value));
    }

    public Quantity(int value) {
        this(new BigDecimal(value));
    }

    public Quantity(long value) {
        this(new BigDecimal(value));
    }

    /**
     * Возвращает результат логической операции РАВНО (=).
     * @param quantity сравниваемая величина
     * @return результат
     */
    public boolean eq(Quantity quantity) {
        return getValue().compareTo(quantity.getValue()) == 0;
    }

    /**
     * Возвращает результат логической операции БОЛЬШЕ (>).
     * @param quantity сравниваемая величина
     * @return результат
     */
    public boolean gt(Quantity quantity) {
        return getValue().compareTo(quantity.getValue()) > 0;
    }

    /**
     * Возвращает результат логической операции БОЛЬШЕ ЛИБО РАВНО (>=).
     * @param quantity сравниваемая величина
     * @return результат
     */
    public boolean ge(Quantity quantity) {
        return getValue().compareTo(quantity.getValue()) >= 0;
    }

    /**
     * Возвращает результат логической операции МЕНЬШЕ (<).
     * @param quantity сравниваемая величина
     * @return результат
     */
    public boolean lt(Quantity quantity) {
        return getValue().compareTo(quantity.getValue()) < 0;
    }

    /**
     * Возвращает результат логической операции МЕНЬШЕ ЛИБО РАВНО (<=).
     * @param quantity сравниваемая величина
     * @return результат
     */
    public boolean le(Quantity quantity) {
        return getValue().compareTo(quantity.getValue()) <= 0;
    }

    /**
     * Арифметическая операция сложения количества и денег {@link Money}.
     * @param money слагаемое
     * @return сумма
     */
    public Quantity add(Money money) {
        return valueOf(getValue().add(money.getValue()));
    }

    /**
     * Арифметическая операция сложения количества.
     * @param quantity слагаемое
     * @return сумма
     */
    public Quantity add(Quantity quantity) {
        return valueOf(getValue().add(quantity.getValue()));
    }

    /**
     * Арифметическая операция вычитания денег {@link Money} из количества.
     * @param value вычитаемое
     * @return разница
     */
    public Quantity subtract(Money value) {
        return valueOf(getValue().subtract(value.getValue()));
    }

    /**
     * Арифметическая операция вычитания количества.
     * @param quantity вычитаемое
     * @return разница
     */
    public Quantity subtract(Quantity quantity) {
        return valueOf(getValue().subtract(quantity.getValue()));
    }

    /**
     * Арифметическая операция умножения количества на деньги {@link Money}.
     * @param value умножаемое
     * @return сумма
     */
    public Quantity multiply(Money value) {
        return valueOf(getValue().multiply(value.getValue()));
    }

    /**
     * Арифметическая операция умножения количества.
     * @param quantity умножаемое
     * @return сумма
     */
    public Quantity multiply(Quantity quantity) {
        return valueOf(getValue().multiply(quantity.getValue()));
    }

    /**
     * Арифметическая операция деления количества на деньги {@link Money}.
     * @param value делитель
     * @return отношение
     */
    public Quantity divide(Money value) {
        return valueOf(getValue().divide(value.getValue(), SCALE, ROUNDING_MODE));
    }

    /**
     * Арифметическая операция деления количества.
     * @param quantity делитель
     * @return отношение
     */
    public Quantity divide(Quantity quantity) {
        return valueOf(getValue().divide(quantity.getValue(), SCALE, ROUNDING_MODE));
    }

    /**
     * Возвращает базовое значение величины.
     * @return базовое значение {@link BigDecimal}
     */
    public BigDecimal getValue() {
        return value;
    }

    private static Quantity valueOf(BigDecimal value) {
        return new Quantity(value);
    }

    /**
     * Возвращает результат проверки на эквивалентость нулю {@link Quantity#ZERO}.
     * @return результат
     */
    public boolean isZero() {
        return eq(ZERO);
    }

    /**
     * Возвращает результат проверки на несоответствие
     * нулевому {@link Quantity#ZERO} значению.
     * @return результат
     */
    public boolean isNotZero() {
        return !isZero();
    }

    /**
     * Возвращает целую часть величины.
     * @return целая часть величины
     */
    public Quantity getInt() {
        return new Quantity(getValue().intValue());
    }

    /**
     * Корректное сравнение возможно только между количеством {@link Quantity}.
     * @param object сравниваемое
     * @return результат
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Quantity) {
            Quantity quantity = (Quantity) object;
            return this.eq(quantity);
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 16;
        int result = 5;
        result = prime * result + getValue().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return getValue().toString();
    }
}
