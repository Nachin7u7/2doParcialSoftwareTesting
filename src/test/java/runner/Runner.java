package runner;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class Runner {

    @Before
    public void setup(){
        System.out.println("SETUP");
    }

    @After
    public void cleanup(){
        System.out.println("CLEAN UP");
    }


}