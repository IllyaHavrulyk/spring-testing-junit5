package guru.springframework.sfgpetclinic.model;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class CustomArgsProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    return Stream.of(Arguments.of("FL", 4, 5), Arguments.of("OH", 12, 4), Arguments.of("FL", 3, 9));
  }
}
