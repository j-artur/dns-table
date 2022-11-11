package table;

import java.util.function.BiConsumer;

public class HashTable<K extends HashTable.Key, V> {
    public interface Key {
        public int hashCode();

        public boolean equals(Key other);
    }

    int size;
    Entry<K, V>[] entries;

    // Creates an array of entries with the given size
    public HashTable(int size) {
        this.size = size;
        entries = new Entry[size];
    }

    // Hashes the key to an index in the entries array
    private int hash(K key) {
        return key.hashCode() % size;
    }

    // Sets the value for the given key
    public V set(K key, V value) {
        int index = hash(key);
        Entry<K, V> entry = entries[index];

        // If the entry is null, create a new entry
        if (entry == null) {
            entries[index] = new Entry<K, V>(key, value);
            return null;
        }

        // If the entry has the same key, replace the value
        if (entry.key().equals(key))
            return entry.setValue(value);

        // Iterate through the linked list to find the entry with the same key
        // Or until it reaches the end
        while (entry.hasNext()) {
            entry = entry.next();
            if (entry.key().equals(key))
                return entry.setValue(value);

        }

        // If it reaches the end, add a new entry
        entry.setNext(new Entry<K, V>(key, value));
        return null;
    }

    // Gets the value associated with the given key
    public V get(K key) {
        int index = hash(key);
        Entry<K, V> entry = entries[index];

        // If the entry is null, return null
        if (entry == null)
            return null;

        // If the entry has the same key, return the value
        if (entry.key().equals(key))
            return entry.value();

        // Iterate through the linked list to find the entry with the same key
        // Or until it reaches the end
        while (entry.next() != null) {
            entry = entry.next();
            if (entry.key().equals(key))
                return entry.value();
        }

        // If it reaches the end, return null
        return null;
    }

    // Iterates through the entries array and executes the given function
    public void forEach(BiConsumer<K, V> consumer) {
        for (Entry<K, V> entry : entries) {
            if (entry == null)
                continue;

            consumer.accept(entry.key(), entry.value());

            while (entry.hasNext()) {
                entry = entry.next();
                consumer.accept(entry.key(), entry.value());
            }
        }
    }

}