package ch.heigvd.amt.projectOne.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegistrationTest {

    @Test
    public void shouldBecorrectCategory(){

        User lio = new User(1, "amt@amt.ch", "amt", "lionel", "Burgbacher", "05-03-1989", null);
        User Gui = new User(2, "amt@amt.ch", "amt", "Guillaume", "Blanco", "05-03-2005", null);
        User Jee = new User(3, "amt@amt.ch", "amt", "Mathieu", "Jee", "05-03-1967", null);
        Trail trail = new Trail(1, "GR", 43, 2359, "Best trail ever", 10, "05-11-2019");
        Registration rg = new Registration(1,lio, trail);
        Registration rg1 = new Registration(2, Gui, trail);
        Registration rg2 = new Registration(3, Jee, trail);
        assertEquals(trail.getNbIn(), 3);
    }
}