package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PersonRepeatedTests extends ModelRepeatedTest{

    @RepeatedTest(value = 10, name = "{displayName} : {currentRepetition} of {totalRepetitions}")
    @DisplayName("My repeated test")
    void myRepeatedTest(){

    }

    @RepeatedTest(value = 10, name = "{displayName} : {currentRepetition} of {totalRepetitions}")
    @DisplayName("My repeated test assignment")
    void myRepeatedTestAssignment(){

    }

    @RepeatedTest(5)
    void myRepeatedTestWithDI(TestInfo testInfo, RepetitionInfo repetitionInfo){

    }
}
