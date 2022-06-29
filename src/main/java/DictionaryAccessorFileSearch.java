import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DictionaryAccessorFileSearch implements DictionaryAccessor {
    private HashMap<String, String> dictionaries;

    public DictionaryAccessorFileSearch(String path) throws IOException {
        Path path_object = Path.of(path);
        if (!Files.exists(path_object)) {
            new File(path).createNewFile();
        }
    }
    private boolean isStringDigits(String iStringToCheck)
    {
        return iStringToCheck.matches("^[0-9]+$");
    }
    private boolean isStringLatin(String iStringToCheck)
    {
        return iStringToCheck.matches("^[a-zA-Z]+$");
    }
    public DictionaryAccessorFileSearch(List<Path> paths) throws IOException {
        dictionaries = new HashMap<>();
        for (Path path : paths
        ) {
            if (!Files.exists(Path.of(path.toString()))) {
                new File(String.valueOf(path)).createNewFile();
            }
            dictionaries.put(path.getFileName().toString(), path.toString());
        }
    }

    @Override
    public boolean add(String dict, String key, String val) {
        if (dictionaries.containsKey(dict)) {
            String currentDict = dictionaries.get(dict);
            try (FileWriter fr = new FileWriter(currentDict, true);
                 BufferedWriter bw = new BufferedWriter(fr)) {
                if(key.length() != 4 || val.length() !=5){
                    return false;
                }
                if(!isStringLatin(key) || !isStringDigits(val)){
                    return false;
                }
                String toAdd = String.format("%s-%s", key, val);
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
        return false;
    }

    @Override
    public boolean del(String dict, String key) throws IOException {
        if(dictionaries.containsKey(dict)) {
            String currentDict = dictionaries.get(dict);
            File file = new File(currentDict);
            StringBuilder sb = new StringBuilder();
            try (Scanner sc = new Scanner(file)) {
                String currentLine;
                while (sc.hasNext()) {
                    currentLine = sc.nextLine();
                    String[] split = currentLine.split("\\-", -1);
                    if (split[0].equals(key)) {
                        continue; //skips lineToRemove
                    }
                    sb.append(currentLine).append("\n");
                }
            }
            //Delete File Content
            PrintWriter pw = new PrintWriter(file);
            pw.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.append(sb.toString());
            writer.close();
            pw.close();
            return true;
        }
        return false;
    }


    @Override
    public String get(String dict, String key) {
        if(dictionaries.containsKey(dict)) {
            String currentDict = dictionaries.get(dict);
            try (FileReader fr = new FileReader(currentDict);
                 BufferedReader br = new BufferedReader(fr)) {
                String line = "";
                while (line != null) {
                    line = br.readLine();
                    if (line != null) {
                        String[] split = line.split("\\-", -1);
                        if (split[0].equals(key)) {
                            return split[1];
                        }
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
        return "";
    }

    @Override
    public List<WordPair> getAll(String dict) {
        if(dictionaries.containsKey(dict)) {
            String currentDict = dictionaries.get(dict);
            List<WordPair> words = new ArrayList<>();
            try (FileReader fr = new FileReader(currentDict);
                 BufferedReader br = new BufferedReader(fr)) {
                String line = "";
                while (line != null) {
                    line = br.readLine();
                    if (line != null) {
                        String[] split = line.split("\\-", -1);
                        WordPair wp = new WordPair(split[0], split[1]);
                        words.add(wp);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return words;
        }
        return null;
    }

    @Override
    public List<String> getAllDictionaries() {
        return (List<String>) dictionaries.values()
                .stream()
                .collect(
                        Collectors.toCollection(ArrayList::new)
                );
    }

    public HashMap<String, String> getDictionaries() {
        return dictionaries;
    }

    public void setDictionaries(HashMap<String, String> dictionaries) {
        this.dictionaries = dictionaries;
    }
}
