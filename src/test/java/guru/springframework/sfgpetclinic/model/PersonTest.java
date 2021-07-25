package guru.springframework.sfgpetclinic.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PersonTest extends ModelTest {


  @Test
  public void groupedAssertions() {
    Person person = new Person(1L, "Philipp", "Plein");
    Assertions.assertAll("Test setting of properties",
        () -> Assertions.assertEquals(Long.valueOf(1), person.getId(), "ID is violated."),
        () -> Assertions.assertEquals("Plein", person.getLastName(), "Incorrect last name."),
        () -> Assertions.assertEquals("Philipp", person.getFirstName(), "Incorrect first name.")
    );
  }
}