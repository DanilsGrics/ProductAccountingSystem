package Service;

import org.junit.Test;

import Database.Category;

public class CategoryDoesNotExistExceptionTest {

    @Test (expected = CategoryDoesNotExistException.class)
    public void CategoryDoesNotExistExceptionTest() {

        Category.checkIfCategoryExists("COMPUTERS");
    }
}