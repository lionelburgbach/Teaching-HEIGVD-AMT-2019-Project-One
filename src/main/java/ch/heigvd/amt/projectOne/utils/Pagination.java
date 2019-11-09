package ch.heigvd.amt.projectOne.utils;

public class Pagination {

    public static  int getNumberPages(int rows, int elementPerPage){

        int numberOfPages = rows / elementPerPage;
        if (numberOfPages % elementPerPage > 0) {
            numberOfPages++;
        }
        return  numberOfPages;
    }
}
