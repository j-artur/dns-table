import debug.Debug;
import debug.Debug.Color;

public class App {
    private static boolean debugMode = false;
    private static long timestamp = System.currentTimeMillis();
    private static long waitingTime = 5000;

    static void makeRequest(DNSServer server) {
        String domain = Debug.input(Color.RESET, "Enter a domain to request: ", Color.YELLOW_BRIGHT);
        server.request(domain);
    }

    static void sleep() throws InterruptedException {
        long sleepTime = waitingTime - (System.currentTimeMillis() - timestamp);
        if (sleepTime > 0) {
            Debug.log(Color.YELLOW, "Waiting for more entries to be inserted...");
            Thread.sleep(sleepTime);
        }
        timestamp = System.currentTimeMillis();
    }

    public static void main(String[] args) throws Exception {
        Db db = new Db("assets/db.txt");

        DNSServer server = new DNSServer(15, debugMode);

        server.insert(db.entries().subList(0, 25));
        makeRequest(server);

        sleep();

        server.insert(db.entries().subList(25, 50));
        makeRequest(server);

        sleep();

        server.insert(db.entries().subList(50, 75));
        makeRequest(server);

        sleep();

        server.insert(db.entries().subList(75, 100));
        makeRequest(server);

        Debug.log(Color.BLUE, "Done inserting entries, feel free to make more requests.");

        while (true) {
            makeRequest(server);
        }
    }
}
