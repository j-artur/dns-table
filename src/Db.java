import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Db implements Iterable<Db.Entry> {
    public static record Entry(String domain, String ipAddress) {
    }

    private List<Entry> entries;

    public Db(String path) throws IOException {
        // Read the file,
        // Split each line by multiple whitespaces,
        // Map the array to an Entry,
        // Collect the entries to a list
        entries = Files
                .lines(Paths.get(path))
                .filter(line -> !line.isBlank())
                .map(line -> line.split("\\s+"))
                .map(array -> new Entry(array[0], array[1]))
                .collect(Collectors.toList());
    }

    public List<Entry> entries() {
        return entries;
    }

    @Override
    public Iterator<Entry> iterator() {
        return entries.iterator();
    }
}
