package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
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
}