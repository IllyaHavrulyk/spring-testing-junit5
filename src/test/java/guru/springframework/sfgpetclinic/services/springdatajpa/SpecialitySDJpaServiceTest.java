package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

  @Mock
  SpecialtyRepository specialtyRepository;

  @InjectMocks
  SpecialitySDJpaService specialitySDJpaService;

  @Test
  void delete() {
    specialitySDJpaService.delete(new Speciality());
    specialitySDJpaService.delete(new Speciality());
    //If second argument didn't pass, mockito sets times(1) by default.
    verify(specialtyRepository, times(2)).delete(any());
  }

  @Test
  void testDeleteByObject(){
    Speciality speciality = new Speciality();
    specialitySDJpaService.delete(speciality);
    verify(specialtyRepository).delete(any(Speciality.class));
  }

  @Test
  void deleteByIdAtLeast() {
    specialitySDJpaService.deleteById(1L);
    specialitySDJpaService.deleteById(1L);
    verify(specialtyRepository, atLeast(2)).deleteById(1L);
  }

  @Test
  void deleteByIdAtMost() {
    specialitySDJpaService.deleteById(1L);
    specialitySDJpaService.deleteById(1L);
    verify(specialtyRepository, atMost(5)).deleteById(1L);
  }

  @Test
  void deleteByIdNever() {
    specialitySDJpaService.deleteById(1L);
    specialitySDJpaService.deleteById(2L);
    verify(specialtyRepository, atMost(5)).deleteById(1L);
    verify(specialtyRepository, never()).deleteById(3L);
    then(specialtyRepository).should(atLeastOnce()).deleteById(anyLong());
  }

  @Test
  void findById(){
    Speciality speciality = new Speciality(1L, "Doctor");
    when(specialtyRepository.findById(anyLong())).thenReturn(Optional.of(speciality));
    Speciality foundSpeciality = specialitySDJpaService.findById(anyLong());
    assertThat(foundSpeciality)
        .hasNoNullFieldsOrProperties()
        .hasFieldOrProperty("description")
        .hasFieldOrProperty("id");
  }

  @Test
  void testThrow(){
    doThrow(new RuntimeException("Boom")).when(specialtyRepository).delete(any());
    assertThrows(RuntimeException.class, () -> specialitySDJpaService.delete(new Speciality()));
    verify(specialtyRepository).delete(any());
  }

  @Test
  void testFindByIdThrows(){
    given(specialtyRepository.findById(1L)).willThrow(new RuntimeException("boom"));
    assertThrows(RuntimeException.class, () -> specialitySDJpaService.findById(1L));
    then(specialtyRepository).should().findById(1L);
  }

  @Test
  void testDeleteBDD(){
    willThrow(new RuntimeException("boom")).given(specialtyRepository).delete(any());
    assertThrows(RuntimeException.class, () -> specialitySDJpaService.delete(new Speciality()));
    then(specialtyRepository).should().delete(any());
  }

  @Test
  void testSaveLambda() {
    //given
    final String MATCH_ME = "MATCH_ME";
    Speciality speciality = new Speciality();
    speciality.setDescription(MATCH_ME);

    Speciality savedSpecialty = new Speciality();
    savedSpecialty.setId(1L);

    //need mock to only return on match MATCH_ME string
    given(specialtyRepository.save(argThat(argument -> argument.getDescription().equals(MATCH_ME)))).willReturn(savedSpecialty);

    //when
    Speciality returnedSpecialty = specialitySDJpaService.save(speciality);

    //then
    assertThat(returnedSpecialty.getId()).isEqualTo(1L);
  }

  @Test
  void testSaveLambdaNoMatch() {
    //given
    final String MATCH_ME = "MATCH_ME";
    Speciality speciality = new Speciality();
    speciality.setDescription("Not a match");

    Speciality savedSpecialty = new Speciality();
    savedSpecialty.setId(1L);

    //need mock to only return on match MATCH_ME string
    lenient().when(specialtyRepository.save(argThat(argument -> argument.getDescription().equals(MATCH_ME)))).thenReturn(savedSpecialty);
    //when
    Speciality returnedSpecialty = specialitySDJpaService.save(speciality);

    //then
    assertNull(returnedSpecialty);
  }
}