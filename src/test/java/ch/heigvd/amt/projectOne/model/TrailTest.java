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

        User lio = new User(1, "amt@amt.ch", "amt", "lionel", "Burgbacher", "05-03-1989", 2);
        User Gui = new User(2, "amt@amt.ch", "amt", "Guillaume", "Blanco", "05-03-1991", 2);
        User Jee = new User(3, "amt@amt.ch", "amt", "Guillaume", "Blanco", "05-03-1991", 2);
        User Nico = new User(4, "amt@amt.ch", "amt", "Guillaume", "Blanco", "05-03-1991", 2);
        User afk = new User(5, "amt@amt.ch", "amt", "Guillaume", "Blanco", "05-03-1991", 2);
        Trail trail = new Trail(1, "GR", 43, 2359, "Best trail ever", 10, "05-11-2019");
        Result res = new Result(lio, 10);
        Result res1 = new Result(Gui, 11);
        Result res2 = new Result(Jee, 12);
        Result res3 = new Result(Nico, 13);
        Result res4 = new Result(afk, 14);
        trail.addResult(res);
        trail.addResult(res1);
        trail.addResult(res2);
        trail.addResult(res3);
        trail.addResult(res4);

        assertEquals(trail.resultUser(lio), 1);
        assertEquals(trail.resultUser(afk), 5);
    }

}