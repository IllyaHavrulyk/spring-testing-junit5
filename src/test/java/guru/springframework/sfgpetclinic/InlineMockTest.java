package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class InlineMockTest {
    @Test
    void testMock() {
        Map mockMap = mock(Map.class);
        assertThat(mockMap).hasSize(0);
    }
}
