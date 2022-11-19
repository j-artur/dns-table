import debug.Debug;
import debug.Debug.Color;
import table.HashTable;

public class Server {
    private boolean debugMode;
    private HashTable<Str, Str> dnsTable = new HashTable<>(30);

    public Server(boolean debugMode) {
        this.debugMode = debugMode;
    }

    public void insert(Iterable<Db.Entry> entries) {
        for (var entry : entries) {
            if (debugMode)
                Debug.log(Color.BLUE, "Inserting " + entry.domain() + " -> " + entry.ipAddress());
            dnsTable.set(new Str(entry.domain()), new Str(entry.ipAddress()));
        }
        Debug.log(Color.GREEN_BRIGHT, "Finished inserting entries, table size is " + dnsTable.stream().count());
    }

    public void request(String domain) {
        var ipAddress = dnsTable.get(new Str(domain));

        if (ipAddress == null) {
            Debug.log(Color.RED, "No IP address found for " + domain);
        } else {
            Debug.log(Color.GREEN, "IP address for " + domain + " is " + ipAddress);
        }
    }
}
