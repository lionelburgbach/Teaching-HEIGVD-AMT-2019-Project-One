package ch.heigvd.amt.projectOne.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ResultTest {

    @Test
    public void shouldHaveAnException() {
        User lio = new User(1, "amt@amt.ch", "amt", "lionel", "Burgbacher", "05-03-1989", null);
        Trail trail = new Trail(1, "GR", 43, 2359, "Best trail ever", 10, "05-11-2019");
        Registration rg = new Registration(1, lio, trail, "01-11-2019");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Result(1, rg, -1);;
        });
    }


}
