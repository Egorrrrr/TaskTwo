import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        List<Path> paths = new ArrayList<>();
        paths.add(Path.of("dict.txt"));
        paths.add(Path.of("dict_two.txt"));
        ConsoleDictionary dict = (ConsoleDictionary) applicationContext.getBean("consoleDictionary", paths);
        dict.start();
    }
}
