package table;

import java.util.Iterator;

public class FcList<K, V> implements Iterable<Entry<K, V>> {
    private Entry<K, V> head;

    public FcList() {
        this.head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public Entry<K, V> head() {
        return head;
    }

    // Sets the value for the given key, and returns the old value
    public V set(K key, V value) {
        // if the list is empty, add the new node to the front
        if (isEmpty()) {
            head = new Entry<>(key, value);
            return null;
        }

        Entry<K, V> current = head;

        // if the key is in the head, update the value and return the old value
        if (current.key().equals(key))
            return current.setValue(value);

        // iterate through the list until we find the key or reach the end
        while (current.next() != null) {
            current = current.next();

            // if the key is found, update the value and return the old value
            if (current.key().equals(key))
                return current.setValue(value);
        }

        // if the key is not found, add the new node to the end
        current.setNext(new Entry<>(key, value));
        return null;
    }

    // Searches the value associated with the given key,
    // moves it forward based on its frequency and returns the value
    private Entry<K, V> searchMF(K key) {
        // iterate through the array
        Entry<K, V> current = head;
        Entry<K, V> previous = null;

        while (current != null) {
            // if the key is found, increment the frequency
            if (current.key().equals(key)) {
                current.incrementFrequency();

                // if the node is not at the front of the array, move the node forward
                // until it all the nodes in front of it have a higher frequency
                if (previous != null) {
                    while (previous.frequency() < current.frequency()) {
                        // swap the positions of the nodes
                        Entry<K, V> temp = current;
                        current = previous;
                        previous = temp;
                        previous.setNext(current.next());

                        // if the node is at the front of the array, update the head
                        if (previous == head)
                            head = current;
                    }
                }

                // return the node from the updated position
                return current;
            }

            // move to the next node
            previous = current;
            current = current.next();
        }

        // if the key is not found, return null
        return null;
    }

    // Public interface for search
    public V get(K key) {
        Entry<K, V> node = searchMF(key);
        return node == null ? null : node.value();
    }

    // Removes the node associated with the given key and returns the value
    public V remove(K key) {
        // if the list is empty, return null
        if (isEmpty())
            return null;

        // if the key is in the head, remove the head and return the value
        if (head.key().equals(key)) {
            if (head.next() == null) {
                V value = head.value();
                head = null;
                return value;
            } else {
                V value = head.value();
                head = head.next();
                return value;
            }
        }

        // iterate through the list until we find the key or reach the end
        Entry<K, V> current = head;
        Entry<K, V> previous = null;

        while (current.next() != null) {
            previous = current;
            current = current.next();

            // if the key is found, remove the node and return the value
            if (current.key().equals(key)) {
                V value = current.value();
                previous.setNext(current.next());
                return value;
            }
        }

        // if the key is not found, return null
        return null;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<>() {
            private Entry<K, V> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Entry<K, V> next() {
                Entry<K, V> temp = current;
                current = current.next();
                return temp;
            }
        };
    }
}
