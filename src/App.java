import debug.Debug;
import debug.Debug.Color;

public class App {
    private static boolean debugMode = false;
    private static long timestamp = System.currentTimeMillis();
    private static long waitingTime = 10000;

    static void makeRequest(Server server) {
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
        Server server = new Server(debugMode);

        Db db = new Db("assets/db.txt");

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

        Debug.log(Color.YELLOW, "Done inserting entries, feel free to make more requests.");

        while (true)
            makeRequest(server);
    }
}
