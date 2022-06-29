import database.DictDatabase;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Dictionary;
import java.util.List;

public class DictionaryAccessorDB implements DictionaryAccessor {
    private DictDatabase db;
    public DictionaryAccessorDB(List<Path> paths){
        db = new DictDatabase(paths.get(0).getFileName().toString());
    }
    @Override
    public boolean add(String dictName, String key, String val) {
        return false;
    }

    @Override
    public boolean del(String dictName, String key) {
        return false;
    }

    @Override
    public String get(String dictName, String key) {
        return null;
    }

    @Override
    public List<WordPair> getAll(String dictName) {
        return null;
    }

    @Override
    public List<String> getAllDictionaries() {
        return null;
    }
}
