package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import guru.springframework.sfgpetclinic.repositories.VetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VetSDJpaServiceTest {

  @Mock
  private VetRepository vetRepository;

  @InjectMocks
  private VetSDJpaService vetSDJpaService;

  @Test
  void deleteById() {
    vetSDJpaService.deleteById(1L);
    verify(vetRepository).deleteById(any());
  }
}