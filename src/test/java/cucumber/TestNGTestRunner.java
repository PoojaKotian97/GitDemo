package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber", glue ="SeleniumJava.stepDefinitions" , 
monochrome=true , tags = "@Regression" ,  plugin= {"html:target/cucumber.html"}) // run all the feature file inside metioned package and glue the actual test files , monochrome provides output in readable manner and provides html report
// tags = "@Regression" --> Run only regression 
public class TestNGTestRunner extends AbstractTestNGCucumberTests{

}
