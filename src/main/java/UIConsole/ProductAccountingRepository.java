package UIConsole;

import Service.CategoryDoesNotExistException;

public class ProductAccountingRepository {

    public static void main(String[] args) throws CategoryDoesNotExistException {

        UserInterface userInterface = new UserInterface();

        userInterface.menu();
    }
}
