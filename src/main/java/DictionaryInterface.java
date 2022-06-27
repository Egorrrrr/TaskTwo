import java.util.List;

public interface DictionaryInterface {
    boolean add(String key, String val);
    boolean del(String key);
    String get(String key);
    List<WordPair> getAll();

}
