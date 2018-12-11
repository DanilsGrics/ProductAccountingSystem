package database;


import service.CategoryDoesNotExistException;

public enum Category {

    //http://www.florencefeldman.com/organization/grocery-shopping-list
    MEATS,
    ALCOHOL,
    FROZEN,
    CANNED,
    BAKERY,
    FRUITS,
    VEGETABLES,
    BEVERAGES,
    CEREALS,
    DAIRY,
    MISCELLANEOUS,
    CONDIMENTS;

    public static Category checkIfCategoryExists(String name) throws CategoryDoesNotExistException {
        for (Category cat : Category.values()) {
            if (cat.toString().equals(name)) {
                return cat;
            }
        }

        throw new CategoryDoesNotExistException("Category does not exist");
    }
}
