import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Path> paths = new ArrayList<>();
        paths.add(Path.of("dict.txt"));
        paths.add(Path.of("dict_two.txt"));
        DictionaryFileSearch fs = new DictionaryFileSearch(paths);
        ConsoleDictionary dict = new ConsoleDictionary(fs);
        dict.start();
    }
}
