import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleDictionary {

    private DictionaryInterface dict;

    public ConsoleDictionary(DictionaryInterface dict) {
        this.dict = dict;
    }
    public void start() throws IOException {
        displayMenu();
        readInput();
    }
    public void readInput() throws IOException {
        while(true) {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            String input = reader.readLine();
            switch (input) {
                case "1": {
                    System.out.println("Введите слово, которое хотите перевести");
                    String word = reader.readLine();
                    System.out.println(dict.get(word));
                    break;
                }
                case "2": {
                    System.out.println("Введите первое слово(ключ)");
                    String firstWord = reader.readLine();
                    System.out.println("Введите второе слово(значение)");
                    String secondWord = reader.readLine();
                    boolean success = dict.add(firstWord, secondWord);
                    if(success){
                        System.out.println("Слово добавлено");
                    }
                    else {
                        System.out.println("Ошибка при добавлении");
                    }
                    break;
                }
                case "3": {
                    break;
                }
                case "4": {
                    break;
                }
            }
        }
    }
    public void displayMenu(){

        System.out.println("1.Найти слово\n" +
                "2.Добавить слово\n" +
                "3.Удлаить слово\n" +
                "4.Вывести все слова");


    }
    public DictionaryInterface getDict() {
        return dict;
    }

    public void setDict(DictionaryInterface dict) {
        this.dict = dict;
    }
}
