package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.*;

@Tag("model")
public abstract class ModelRepeatedTest {

    @BeforeEach
    public void beforeEach(TestInfo testInfo, RepetitionInfo repetitionInfo){
        System.out.println(testInfo + "\n" + repetitionInfo);
    }
}
