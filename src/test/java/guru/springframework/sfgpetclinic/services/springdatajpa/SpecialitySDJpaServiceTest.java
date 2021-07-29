package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
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
  }
}