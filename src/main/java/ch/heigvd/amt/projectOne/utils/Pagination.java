package ch.heigvd.amt.projectOne.utils;

public class Pagination {

    /**
     * Return the number of page for pagination
     * @param rows              number of row that we retrieve of the DB
     * @param elementPerPage    number of element in one page
     * @return the number of page that we need (for pagination)
     */
    public static  int getNumberPages(int rows, int elementPerPage){

        int numberOfPages = rows / elementPerPage;
        if (numberOfPages % elementPerPage > 0) {
            numberOfPages++;
        }
        return  numberOfPages;
    }
}
