package database;

import org.junit.Test;

import service.CategoryDoesNotExistException;

import static org.junit.Assert.*;

public class CategoryTest {

    @Test
    public void categoryShouldExistTest() {

        Category category = Category.checkIfCategoryExists("ALCOHOL");
        assertEquals(Category.ALCOHOL, category);
    }

    @Test(expected = CategoryDoesNotExistException.class)
    public void categoryShouldNotExistTest() {

        Category.checkIfCategoryExists("COMPUTERS");
    }
}