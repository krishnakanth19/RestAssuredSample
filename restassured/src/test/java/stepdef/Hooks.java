package stepdef;

import cucumber.api.java.Before;
import utils.RestAssuredExtension;

public class Hooks {

    @Before
    public void init(){
        RestAssuredExtension restAssuredExtension = new RestAssuredExtension();
        System.out.println("Object ref: "+restAssuredExtension);
    }
}
