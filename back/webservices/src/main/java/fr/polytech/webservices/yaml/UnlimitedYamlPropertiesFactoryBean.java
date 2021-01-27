package fr.polytech.webservices.yaml;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class UnlimitedYamlPropertiesFactoryBean extends YamlPropertiesFactoryBean {

    private static void log(Object o) {
        System.out.println("############################################");
        for(int i=0; i<10; i++) {
            System.out.println("#                                          #");
        } 
        System.out.println(o);
        for(int i=0; i<10; i++) {
            System.out.println("#                                          #");
        } 
        System.out.println("############################################");
    }

    @Override
    protected Yaml createYaml() {
        LoaderOptions loaderOptions = new LoaderOptions();
        loaderOptions.setAllowDuplicateKeys(false);
        log("Before1");
        log(loaderOptions.getMaxAliasesForCollections());
        log("Before2");
        loaderOptions.setMaxAliasesForCollections(Integer.MAX_VALUE);
        log("After");
        log(loaderOptions.getMaxAliasesForCollections());
        loaderOptions.setAllowRecursiveKeys(true);

        return new Yaml(loaderOptions);
    }
}
