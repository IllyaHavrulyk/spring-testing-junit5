package guru.springframework.sfgpetclinic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Map;

public class AnnotationsMockTest {
    @Mock
    private Map<String, Object> mockMap;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testMock() {
        mockMap.put("key", "value");
    }
}
