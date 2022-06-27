import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DictionaryFileSearch fs = new DictionaryFileSearch("dict.txt");
        ConsoleDictionary dict = new ConsoleDictionary(fs);
        dict.start();
    }
}
