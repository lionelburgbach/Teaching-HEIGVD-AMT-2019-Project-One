package ch.heigvd.amt.projectOne.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

  @Test
  void itShouldBePossibleToCreateUsers() {
    User gBlanco = User.builder()
      .firstName("Guillaume")
      .lastName("Blanco")
      .email("guitch@blanco.com")
      .build();
    assertNotNull(gBlanco);
    assertEquals("Guillaume", gBlanco.getFirstName());
  }
}