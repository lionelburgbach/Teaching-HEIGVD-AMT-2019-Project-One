package ch.heigvd.amt.projectOne.utils;

public class Pagination {

    public static  int getNumberPages(int rows){

        int numberOfPages = rows / Consts.ELEMENT_PER_PAGE;
        if (numberOfPages % Consts.ELEMENT_PER_PAGE > 0) {
            numberOfPages++;
        }
        return  numberOfPages;
    }
}
