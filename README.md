# Math
Library for operations with money and quantity.

[![Build Status](https://travis-ci.org/KRSKTilos/math.svg?branch=master)](https://travis-ci.org/KRSKTilos/math)
[![codecov](https://codecov.io/gh/KRSKTilos/math/branch/master/graph/badge.svg)](https://codecov.io/gh/KRSKTilos/math)

# Money
Money uses rounding to 2.

Math operations
```java
Money value = new Money(1000.99);
/* sum */
value.add(new Money(24.01));
/* subtract */
value.subtract(new Money(0.99));
/* multiply */
value.multiply(new Money(5.34));
/* divide */
value.divide(new Money(300));
```

Logical operations
```java
Money value = new Money(1000);
/* great-equal (>=) */
assert value.ge(new Money(1000));
/* great-than (>) */
assert value.gt(new Money(999.99));
/* low-equal (<=) */
assert value.le(new Money(1000));
/* low-than (<) */
assert value.lt(new Money(1000.01));
/* equal (=) */
assert value.eq(new Money(1000));
```

# Quantity
Money uses rounding to 3.

Math operations
```java
Quantity value = new Quantity(0.579);
/* sum */
value.add(new Quantity(1.421));
/* subtract */
value.subtract(new Quantity(0.500));
/* multiply */
value.multiply(new Quantity(10));
/* divide */
value.divide(new Quantity(2.5));
```

Logical operations
```java
Quantity value = new Quantity(1.500);
/* great-equal (>=) */
assert value.ge(new Quantity(1.500));
/* great-than (>) */
assert value.gt(new Quantity(0.990));
/* low-equal (<=) */
assert value.le(new Quantity(1.500));
/* low-than (<) */
assert value.lt(new Quantity(1.501));
/* equal (=) */
assert value.eq(new Quantity(1.500));
```

## Cross-unit operations
```java
assert new Money(1000).divide(new Quantity(5)).eq(new Money(200));
```