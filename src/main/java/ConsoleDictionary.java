import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ConsoleDictionary implements DictionaryInterface {

    private DictionaryAccessor dict;
    private String langSelected;
    BufferedReader reader;
    public ConsoleDictionary(DictionaryAccessor da) {
        this.dict = da;

    }
    @Override
    public void start() throws IOException {
        this.dict = dict;
        reader = new BufferedReader(
                new InputStreamReader(System.in));
        selectDict(reader);
        displayMenu();
        readInput();
    }
    public void readInput() throws IOException {
        while(true) {
            String input = reader.readLine();
            switch (input) {
                case "1": {
                    System.out.println("Введите слово, которое хотите перевести");
                    String word = reader.readLine();
                    System.out.println(dict.get(langSelected,word));
                    break;
                }
                case "2": {
                    System.out.println("Введите первое слово(ключ)");
                    String firstWord = reader.readLine();
                    System.out.println("Введите второе слово(значение)");
                    String secondWord = reader.readLine();
                    boolean success = dict.add(langSelected,firstWord, secondWord);
                    if(success){
                        System.out.println("Слово добавлено");
                    }
                    else {
                        System.out.println("Ошибка при добавлении");
                    }
                    break;
                }
                case "3": {
                    System.out.println("Введите слово, которое хотите удалить");
                    String word = reader.readLine();
                    boolean success = dict.del(langSelected,word);
                    if(success){
                        System.out.println("Слово удалено");
                    }
                    else {
                        System.out.println("Ошибка при удалении");
                    }
                    break;
                }
                case "4": {
                    System.out.println("-----");
                    for (WordPair wp: dict.getAll(langSelected)
                         ) {
                        System.out.println(wp.getWordOne() +" - " + wp.getWordTwo());
                    }
                    System.out.println("-----");
                    break;
                }
                case "5": {
                    selectDict(reader);

                }
            }
        }
    }
    public void selectDict(BufferedReader reader){
        ArrayList<String> dicts = (ArrayList<String>) dict.getAllDictionaries();
        for (int i =0; i<dicts.size();i++){
            System.out.println(i+1 +"."+dicts.get(i));
        }
        System.out.println("Введите номер словаря, который хотите выбрать");
        Integer index = null;
        while(true) {
            try {
                index = Integer.parseInt(reader.readLine());
                if (index-1 > dicts.size() || index <= 0){
                    throw new Exception("Out of bounds");
                }
                break;
            } catch (Exception e) {
                System.out.println("Введите корректное значение");
                continue;
            }

        }
        langSelected = dicts.get(index-1);
        System.out.println("Словарь выбран");
    }
    public void displayMenu(){
        System.out.println("Доступные словари:");


        System.out.println("1.Найти слово\n" +
                "2.Добавить слово\n" +
                "3.Удалить слово\n" +
                "4.Вывести все слова\n" +
                "5.Выбрать словарь");


    }
    public DictionaryAccessor getDict() {
        return dict;
    }

    public void setDict(DictionaryAccessor dict) {
        this.dict = dict;
    }
}
