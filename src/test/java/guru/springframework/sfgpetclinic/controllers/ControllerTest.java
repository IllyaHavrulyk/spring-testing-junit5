package guru.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;

@Tag("controller")
public abstract class ControllerTest {
    @BeforeAll
    public void beforeAll(){
        System.out.println("Let's do something here");
    }
}
