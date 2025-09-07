// Product interface
public interface Product {
    String getNombre();
    int getCantidad();
    void restock(int cantidad);
    boolean necesitaRefrigeracion();
    String getTipo(); // vegetales, frutas, carne, etc.
    void use();
}

// Concrete Products
public class ConcreteProductA implements Product {
    private String nombre;
    private int cantidad;
    private boolean refrigeracion;
    private String tipo;

    public ConcreteProductA() {
        this.nombre = "Lechuga";
        this.cantidad = 10;
        this.refrigeracion = true;
        this.tipo = "vegetal";
    }

    public String getNombre() { return nombre; }
    public int getCantidad() { return cantidad; }
    public void restock(int cantidad) { this.cantidad += cantidad; }
    public boolean necesitaRefrigeracion() { return refrigeracion; }
    public String getTipo() { return tipo; }
    public void use() {
        System.out.println("Usando " + nombre);
        if (cantidad > 0) cantidad--;
    }
}

public class ConcreteProductB implements Product {
    private String nombre;
    private int cantidad;
    private boolean refrigeracion;
    private String tipo;

    public ConcreteProductB() {
        this.nombre = "Manzana";
        this.cantidad = 20;
        this.refrigeracion = false;
        this.tipo = "fruta";
    }

    public String getNombre() { return nombre; }
    public int getCantidad() { return cantidad; }
    public void restock(int cantidad) { this.cantidad += cantidad; }
    public boolean necesitaRefrigeracion() { return refrigeracion; }
    public String getTipo() { return tipo; }
    public void use() {
        System.out.println("Usando " + nombre);
        if (cantidad > 0) cantidad--;
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