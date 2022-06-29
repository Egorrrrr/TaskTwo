import java.io.IOException;
import java.util.List;

public interface DictionaryAccessor {
    boolean add(String dictName,String key, String val);
    boolean del(String dictName,String key) throws IOException;
    String get(String dictName,String key);
    List<WordPair> getAll(String dictName);
    List<String> getAllDictionaries();

}
