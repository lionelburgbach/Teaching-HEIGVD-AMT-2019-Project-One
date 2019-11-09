package ch.heigvd.amt.projectOne.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaginationTest {

    @Test
    void numberPageShouldBeOk(){

        assertEquals(Pagination.getNumberPages(100,30), 4);
        assertEquals(Pagination.getNumberPages(100,10), 10);
    }
}