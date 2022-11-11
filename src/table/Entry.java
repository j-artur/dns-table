package table;

public class Entry<K, V> {
    private K key;
    private V value;
    private Entry<K, V> next;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K key() {
        return key;
    }

    public V value() {
        return value;
    }

    V setValue(V value) {
        V oldValue = this.value;
        this.value = value;
        return oldValue;
    }

    Entry<K, V> next() {
        return next;
    }

    boolean hasNext() {
        return next != null;
    }

    void setNext(Entry<K, V> next) {
        this.next = next;
    }
}
