package ch.heigvd.amt.projectOne.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegistrationTest {

    @Test
    public void shouldBecorrectCategory(){

        User lio = new User(1, "lionel", "Burgbacher", "05-03-1989", "amt@amt.ch", "amt");
        Trail trail = new Trail(1, "GR", 43, 2359, "Best trail ever", "05-11-2019");
        Registration rg = new Registration(1,lio, trail);
        assertNotNull(rg);
    }
}