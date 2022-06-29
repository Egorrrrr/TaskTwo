import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class AppConfig {
    @Bean("fileDictionarySearcher")
    @Scope("prototype")
    public DictionaryAccessor createFileDictionary(List<Path> paths) throws IOException {
        return new DictionaryAccessorFileSearch(paths);
    }
    @Bean("consoleDictionary")
    @Scope("prototype")
    public ConsoleDictionary createConsoleDict(List<Path> paths) throws IOException {
        return new ConsoleDictionary(createFileDictionary(paths));
    }
}
