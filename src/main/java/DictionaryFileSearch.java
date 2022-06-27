import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DictionaryFileSearch implements DictionaryInterface {
    private String path;

    public DictionaryFileSearch(String path) throws IOException {
        this.path = path;
        Path path_object = Path.of(path);
        if(!Files.exists(path_object)){
            new File(path).createNewFile();
        }
    }

    @Override
    public boolean add(String key, String val) {
        try (FileWriter fr = new FileWriter(path,true);
             BufferedWriter bw = new BufferedWriter(fr)) {
            String toAdd = String.format("%s-%s", key,val);
            bw.write(toAdd);
            bw.newLine();
            return true;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean del(String key) {
        return true;
    }

    @Override
    public String get(String key) {
        try (FileReader fr = new FileReader(path);
             BufferedReader br = new BufferedReader(fr)) {
            String line = "";
            while(line != null){
                line = br.readLine();
                if(line != null){
                    String[] split = line.split("\\-", -1);
                    return split[1];
                }
            }
            return "Слово не найдено";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public List<WordPair> getAll() {
        return null;
    }
}
