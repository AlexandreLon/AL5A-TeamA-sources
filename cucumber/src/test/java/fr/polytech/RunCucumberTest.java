package fr.polytech;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;


@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, features = "src/test/resources/features")
@ContextConfiguration(classes= AppConfiguration.class)
public class RunCucumberTest {}
