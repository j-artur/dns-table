import table.HashTable;

public class Str implements HashTable.Key {
    private String str;

    public Str(String str) {
        this.str = str;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < str.length(); i++)
            hash += str.charAt(i);

        return hash;
    }

    @Override
    public boolean equals(HashTable.Key key) {
        if (key == null)
            return false;
        if (key == this)
            return true;
        if (!(key instanceof Str))
            return false;

        Str other = (Str) key;
        return str.equals(other.str);
    }

    @Override
    public String toString() {
        return str;
    }
}
