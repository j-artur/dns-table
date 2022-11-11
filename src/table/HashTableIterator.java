package table;

import java.util.Iterator;

public class HashTableIterator<K, V> implements Iterator<Entry<K, V>> {
    private HashTable<K, V> table;
    private int index;
    private Entry<K, V> current;

    HashTableIterator(HashTable<K, V> table) {
        this.table = table;
    }

    @Override
    public boolean hasNext() {
        if (current != null && current.hasNext())
            return true;

        for (int i = index; i < table.size; i++) {
            if (table.entries[i] != null)
                return true;
        }

        return false;
    }

    @Override
    public Entry<K, V> next() {
        if (current != null && current.hasNext()) {
            current = current.next();
        } else {
            while (table.entries[index] == null)
                index++;
            current = table.entries[index++];
        }

        return current;
    }
}
