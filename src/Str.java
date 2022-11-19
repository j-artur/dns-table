import table.HashTable.Key;

public class Str implements Key {
    private String str;

    public Str(String str) {
        this.str = str;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (int i = str.length() - 1; i >= 0; i--)
            hash += str.charAt(i) * i;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (obj.getClass() != getClass()) {
            return false;
        }

        Str other = (Str) obj;
        return str.equals(other.str);
    }

    @Override
    public String toString() {
        return str;
    }
}
