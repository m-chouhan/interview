package locus.entities;

public class Resource {
    public final String name;
    public Resource(String name) { this.name = name; }

    @Override
    public String toString() {
        return name;
    }
}
