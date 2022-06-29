import springdictionary.Starter;

import java.io.IOException;

public class WebDictionary implements DictionaryInterface{
    private Starter starter;
    private DictionaryAccessor da;
    public WebDictionary(DictionaryAccessor da){
        this.da = da;
        starter = new Starter();
    }
    @Override
    public void start() throws IOException {
        starter.start();
    }
}
