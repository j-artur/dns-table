import table.HashTable;

public class App {
    public static void main(String[] args) throws Exception {
        HashTable<String, String> table = new HashTable<>(10);

        table.set("Hello", "World");
        table.set("Hi", "There");
        table.set("My", "Name");
        table.set("Is", "John");
        table.set("I", "Am");
        table.set("A", "Programmer");
        table.set("I", "Like");
        table.set("To", "Code");
        table.set("In", "Java");

        for (var entry : table) {
            System.out.println(entry.key() + ": " + entry.value());
        }
    }
}
