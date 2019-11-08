package ch.heigvd.amt.projectOne.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrailTest {

    @Test
    void itShouldBeNotPossibleToCreateATrailWithUpAndDownLessThanZero() {

        assertThrows(IllegalArgumentException.class, () -> {
            new Trail(1, "GR", 43, -234, "Best trail ever", "05-11-2019");
        });
    }

    @Test
    void itShouldBeNotPossibleToCreateATrailWithDistanceLessThanZero() {

        assertThrows(IllegalArgumentException.class, () -> {
            new Trail(1, "GR", -1043, 100, "Best trail ever", "05-11-2019");
        });
    }
}