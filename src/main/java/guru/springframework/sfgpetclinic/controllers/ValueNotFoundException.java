package guru.springframework.sfgpetclinic.controllers;

public class ValueNotFoundException extends RuntimeException {

  public ValueNotFoundException(String message) {
    super(message);
  }
}
