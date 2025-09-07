// Product interface
public interface Product {
    void use();
}

// Concrete Products
public class ConcreteProductA implements Product {
    public void use() {
        System.out.println("Using Product A");
    }
}

public class ConcreteProductB implements Product {
    public void use() {
        System.out.println("Using Product B");
    }
}

// Creator abstract class
public abstract class Factory {
    public abstract Product createProduct();
}

// Concrete Creators
public class FactoryA extends Factory {
    public Product createProduct() {
        return new ConcreteProductA();
    }
}

public class FactoryB extends Factory {
    public Product createProduct() {
        return new ConcreteProductB();
    }
}