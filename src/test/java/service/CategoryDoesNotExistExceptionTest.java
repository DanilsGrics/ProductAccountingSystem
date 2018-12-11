package service;

import org.junit.Test;

import database.Category;

public class CategoryDoesNotExistExceptionTest {

    @Test (expected = CategoryDoesNotExistException.class)
    public void CategoryDoesNotExistExceptionTest() {

        Category.checkIfCategoryExists("COMPUTERS");
    }
}