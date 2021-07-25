package guru.springframework.sfgpetclinic.controllers;

import java.time.Duration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;
import static org.assertj.core.api.Assertions.*;

@TestInstance(Lifecycle.PER_CLASS)
class IndexControllerTest {

  private IndexController indexController;

  @BeforeAll
  void setUp() {
    indexController = new IndexController();
  }

  @Test
  void testIndex() {
    Assertions.assertEquals("index", indexController.index());
    Assertions.assertNotEquals("index1234", indexController.index(), "View is the same.");
    assertThat(indexController.index()).isEqualTo("index");
  }

  @DisplayName("Test Exception")
  @Test
  void testOopsHandler() {
    Assertions.assertThrows(ValueNotFoundException.class, () -> {
      indexController.oopsHandler();
    });
  }

  @Disabled("Demo of timeout test.")
  @Test
  void testTimeout() {
    Assertions.assertTimeout(Duration.ofMillis(100), () -> {
      Thread.sleep(5000);
      System.out.println("I got here despite test failure.");
    });
  }

  @Disabled("Demo of timeout preemptive test.")
  @Test
  void testTimeoutPreemptively() {
    Assertions.assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
      Thread.sleep(5000);
      System.out.println("I got here despite test failure.");
    });
  }

  @DisplayName("Testing false assumption.")
  @Test
  void testIfAssumptionIsTrue() {
    Assumptions.assumeTrue("GURU".equals(System.getenv("GURU")));
  }

  @DisplayName("Testing true assumption.")
  @Test
  void testTrueAssumption() {
    Assumptions.assumeTrue("GURU".equalsIgnoreCase("GURU"));
  }

  @EnabledOnOs(OS.MAC)
  @Test
  void testOnlyOnMac() {
    System.out.println("You can run me only on mac.");
  }

  @EnabledOnOs(OS.WINDOWS)
  @Test
  void testOnlyOnWindows() {
    System.out.println("You can run me only on windows.");
  }

  @EnabledOnJre(JRE.JAVA_11)
  @Test
  void testOnlyOnJava11() {
    System.out.println("You can run me only on JAVA 11.");
  }

  @EnabledOnJre(JRE.JAVA_8)
  @Test
  void testOnlyOnJava8() {
    System.out.println("You can run me only on JAVA 8.");
  }

  @EnabledIfEnvironmentVariable(named = "USER_FRED", matches = "fred")
  @Test
  void testIfUserFred(){
    System.out.println("You can only test me only if you have environment variable USER_FRED=fred");
  }
}