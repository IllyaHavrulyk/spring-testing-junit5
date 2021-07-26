package guru.springframework.sfgpetclinic.model;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OwnerTest extends ModelTest {

  @DisplayName("Owner and Person properties test.")
  @Test
  public void groupedAssertions() {
    Owner owner = new Owner(1L, "Justin", "Bieber");
    owner.setAddress("Hollywood, LA");
    owner.setTelephone("+380982123456");
    owner.setCity("Petrozavodsk");
    Assertions.assertAll("Properties test",
        () -> Assertions.assertAll("Person properties",
            () -> Assertions.assertEquals("Justin", owner.getFirstName(), "Incorrect first name."),
            () -> Assertions.assertEquals("Bieber", owner.getLastName(), "Incorrect last name.")),
        () -> Assertions.assertAll("Owner properties",
            () -> Assertions.assertEquals("+380982123456", owner.getTelephone(), "Wrong telephone."),
            () -> Assertions.assertEquals("Hollywood, LA", owner.getAddress(), "Wrong address.")
        )
    );
    assertThat(owner.getCity(), is("Petrozavodsk"));
  }

  @DisplayName("Value Source test")
  @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
  @ValueSource(strings = {"MY", "First", "Parameterized", "Test"})
  void testValueSource(String val){
    System.out.println(val);
  }

  @DisplayName("Value Source test")
  @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
  @EnumSource(OwnerType.class)
  void testValueSourceEnum(OwnerType val){
    System.out.println(val);
  }

  @DisplayName("CSV Input Test")
  @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
  @CsvSource({
      "FL, 1, 1",
      "MI, 3, 2",
      "OH, 2, 4"
  })
  void csvInputTest(String stateName, int val1, int val2){
    System.out.println(stateName + " = " + val1 + " : " + val2);
  }

  @DisplayName("CSV Source file Input Test")
  @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
  @CsvFileSource(resources = "/input.csv", numLinesToSkip = 1)
  void csvFileInputTest(String stateName, int val1, int val2){
    System.out.println(stateName + " = " + val1 + " : " + val2);
  }

  @DisplayName("CSV Source method Input Test")
  @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
  @MethodSource("getArgs")
  void csvMethodInputTest(String stateName, int val1, int val2){
    System.out.println(stateName + " = " + val1 + " : " + val2);
  }

  @DisplayName("CSV Source method Input Test")
  @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
  @ArgumentsSource(CustomArgsProvider.class)
  void csvCustomProviderInputTest(String stateName, int val1, int val2){
    System.out.println(stateName + " = " + val1 + " : " + val2);
  }

  static Stream<Arguments>getArgs(){
    return Stream.of(Arguments.of("FL", 4, 5), Arguments.of("OH", 12, 4), Arguments.of("FL", 3, 9));
  }
}