package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

  private final Visit visitOneWeekAgo = new Visit(1L, LocalDate.now().minusWeeks(1), "One week ago", new Pet());
  private final Visit visitOneDayAgo = new Visit(2L, LocalDate.now().minusDays(1), "One day ago", new Pet());

  @InjectMocks
  private VisitSDJpaService service;

  @Mock
  private VisitRepository visitRepository;

  @Test
  void findAll() {

    Mockito.when(visitRepository.findAll()).thenReturn(Arrays.asList(visitOneDayAgo, visitOneWeekAgo));
    Set<Visit> all = service.findAll();
    all.forEach(visit -> {
      Assertions.assertThat(visit)
          .hasNoNullFieldsOrProperties()
          .isInstanceOf(Visit.class)
          .hasFieldOrProperty("description")
          .hasFieldOrProperty("pet")
          .hasFieldOrProperty("id")
          .hasFieldOrProperty("date");
      Condition<LocalDate> earlierThanNow = earlierThanNow();
      Assertions.assertThat(visit.getDate()).is(earlierThanNow);
      verify(visitRepository, times(1)).findAll();
    });

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

  private Condition<LocalDate> earlierThanNow() {
    return new Condition<>((localDate) -> LocalDate.now().isAfter(localDate), "Testing if given date is earlier than current date.");
  }
}