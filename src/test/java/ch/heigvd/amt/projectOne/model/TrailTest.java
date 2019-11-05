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

}