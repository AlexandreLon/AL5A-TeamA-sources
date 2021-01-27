package fr.polytech.webservices.yaml;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class UnlimitedYamlPropertiesFactoryBean extends YamlPropertiesFactoryBean {

    @Override
    protected Yaml createYaml() {
        LoaderOptions loaderOptions = new LoaderOptions();
        loaderOptions.setAllowDuplicateKeys(false);
        loaderOptions.setMaxAliasesForCollections(Integer.MAX_VALUE);
        loaderOptions.setAllowRecursiveKeys(true);

        return new Yaml(loaderOptions);
    }
}
