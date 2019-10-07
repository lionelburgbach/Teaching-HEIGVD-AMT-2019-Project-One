package ch.heigvd.amt.projectOne.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrailTest {

    @Test
    void itShouldBePossibleToHave100Trailers() {

        Trail trail = Trail.builder()
                .capacity(100)
                .build();
        assertEquals(trail.getCapacity(), 100);
    }

}