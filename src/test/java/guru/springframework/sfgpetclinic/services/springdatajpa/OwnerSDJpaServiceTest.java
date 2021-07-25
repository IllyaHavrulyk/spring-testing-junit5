package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled(value = "Disabled until learn how to mock")
class OwnerSDJpaServiceTest {

  OwnerSDJpaService ownerService;

  @BeforeEach
  void setUp() {
    ownerService = new OwnerSDJpaService(null, null, null);
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void findByLastName() {

  }

  @Test
  void findAllByLastNameLike() {
  }

  @Test
  void findAll() {
  }

  @Test
  void findById() {
  }

  @Test
  void save() {
  }

  @Test
  void delete() {
  }

  @Test
  void deleteById() {
  }
}