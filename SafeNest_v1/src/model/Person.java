package model;

/**
 * Abstract base class for all persons in the system.
 * Demonstrates: abstract class, inheritance, encapsulation, this keyword.
 */
public abstract class Person {

    private final String id;
    private String name;

    protected Person(String id, String name) {
        this.id = id;           // 'this' keyword
        this.name = name;
    }

    public String getId()   { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    /** Each subclass declares its own role (polymorphism via abstract method). */
    public abstract String role();

    @Override
    public String toString() {
        return role() + " { id='" + id + "', name='" + name + "' }";
    }
}
