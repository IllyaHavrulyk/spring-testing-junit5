package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.map.SpecialityMapService;
import guru.springframework.sfgpetclinic.services.map.VetMapService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Collections;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class VetControllerTest extends ControllerTest {
    VetController vetController;
    VetMapService vetMapService;

    @BeforeEach
    void setUp() {
        vetMapService = new VetMapService(new SpecialityMapService());
        vetController = new VetController(vetMapService);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void listVets() {
        Vet vet = new Vet(1L, "Pes", "Sobaka", Collections.EMPTY_SET);
        vetMapService.save(vet);
        Set<Vet> allPets = vetMapService.findAll();
        assertThat(allPets).isNotNull();
        assertThat(allPets).hasSize(1);
        assertThat(allPets).contains(vet);
        allPets.forEach(pet -> {
            assertThat(pet)
                    .hasNoNullFieldsOrProperties();
        });
    }
}