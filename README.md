# CSE123

## Creative Projects

1. C0: [Connect Four](./creative_project_0/)
2. C1: [Surivor Challenge](./creative_project_1/)

## Cheat Sheet

### 1. Implementing Data Structures

- Instantiating an Object
- References vs Objects

#### Data Structures

- ArrayList
- Stack
- Queue
- Set
- Map

### 2. Pre/Post Conditions & Exceptions

- Pre-Condition: What can you guarantee about the input to the function.
- Post-Condition: A state which is true after the function executes.

#### Exceptions

- `NullPointerException`
- `IllegalArgumentException`
- `ArrayIndexOutOfBoundsException`
- `ClassCastException`

### 3. Inheritance & Polymorphism

- Inheritance using `extends`: Creates a child class from a given parent class, which inherit all public methods.
- Polymorphism: The idea of creating a common interface/API that can be accessed the same way across different objects.

#### Declared Type vs Actual Type

- If a method in the `ActualType` is not found in the `DeclaredType`, and is called, a compiler error will be thrown.
  - **Declared Type**: Determines the methods you can use.
  - **Actual Type**: Determines the implementation.

```java
DeclaredType object = new ActualType<>();
```

#### Casting

- To fix this issue, we can use casting.

```java
Car myCar = new Toyota<>();
((Toyota) myCar).vroom();
```

- However, a runtime error will be thrown if incorrect casting is used.
- Additionally, if the casted type doesn't contain the method that we are calling, a compiler error will be thrown.

### Comparable & Abstract Classes

#### Comparable

- Classes can `implements Comparable<Type>` and override the `compareTo` method to make themselves comparable.

```java
public class Car implements Comparable<Car> {
    private int horsePower;

    public Car(int horsePower) {
        this.horsePower = horsePower;
    }

    public int getHorsePower() {
        return horsePower;
    }

    @Override
    public int compareTo(Car other) {
        return horsePower - other.getHorsePower();
    }
}
```

- `compareTo`
  - Positive (+) return means that the current class is "bigger".
  - Negative (-) return means that the current class is "smaller".
  - Zero (0) return means the classes are the same.

#### Abstract Classes

```java
public abstract class Car {
    private int horsePower;

    public Car(int horsePower) {
        this.horsePower = horsePower;
    }

    public int getHorsePower() {
        return horsePower;
    }

     public abstract String vroomVroom();
}
```
