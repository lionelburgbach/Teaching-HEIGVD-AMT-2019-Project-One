package ch.heigvd.amt.projectOne.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrailTest {

    @Test
    void itShouldBePossibleToHave100Trailers() {

        Trail trail = new Trail(1, "GR", 43, 2359, "Best trail ever", 100, "05-11-2019");
        assertEquals(trail.getCapacity(), 100);
    }

    @Test
    void failToAddNewTrailer() {

        Trail trail = new Trail(1, "GR", 43, 2359, "Best trail ever", 10, "05-11-2019");
        for (int i = 0; i< 10; i++){
            trail.addTrailer();
        }
        assertEquals(trail.addTrailer(), false);
    }

    @Test
    void lioShouldBeFisrt() {

        User lio = new User(1, "amt@amt.ch", "amt", "lionel", "Burgbacher", "05-03-1989");
        User Gui = new User(2, "amt@amt.ch", "amt", "Guillaume", "Blanco", "05-03-1991");
        User Jee = new User(3, "amt@amt.ch", "amt", "Guillaume", "Blanco", "05-03-1991");
        User Nico = new User(4, "amt@amt.ch", "amt", "Guillaume", "Blanco", "05-03-1991");
        User afk = new User(5, "amt@amt.ch", "amt", "Guillaume", "Blanco", "05-03-1991");
        Trail trail = new Trail(1, "GR", 43, 2359, "Best trail ever", 10, "05-11-2019");
        Registration rg = new Registration(1,lio, trail, "01-11-2019");
        Registration rg1 = new Registration(2, Gui, trail, "01-11-2019");
        Registration rg2 = new Registration(3, Jee, trail, "01-11-2019");
        Registration rg3 = new Registration(4, Nico, trail, "01-11-2019");
        Registration rg4 = new Registration(5, afk, trail, "01-11-2019");

        Result res = new Result(1, rg,10);
        Result res1 = new Result(2, rg1,11);
        Result res2 = new Result(3, rg2,12);
        Result res3 = new Result(4, rg3,13);
        Result res4 = new Result(5, rg4,14);

        trail.addResult(res);
        trail.addResult(res1);
        trail.addResult(res2);
        trail.addResult(res3);
        trail.addResult(res4);

        assertEquals(trail.resultUser(lio, rg.getCategory()), 1);
        assertEquals(trail.resultUser(afk, rg4.getCategory()), 5);
    }

}