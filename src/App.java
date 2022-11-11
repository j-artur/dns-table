import table.HashTable;

public class App {
    public static void main(String[] args) throws Exception {
        HashTable<Str, Str> table = new HashTable<>(10);

        table.set(new Str("Hello"), new Str("World"));
        table.set(new Str("Hi"), new Str("There"));
        table.set(new Str("My"), new Str("Name"));
        table.set(new Str("Is"), new Str("John"));
        table.set(new Str("I"), new Str("Am"));
        table.set(new Str("A"), new Str("Programmer"));
        table.set(new Str("I"), new Str("Like"));
        table.set(new Str("To"), new Str("Code"));
        table.set(new Str("In"), new Str("Java"));

        table.forEach((key, value) -> System.out.println(key + " " + value));
    }
}
