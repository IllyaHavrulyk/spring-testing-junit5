package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;

@Tag("model")
public abstract class ModelTest {
    @BeforeAll
    public void beforeAll(TestInfo testInfo){
        System.out.println("Before all " + testInfo);
    }
}
