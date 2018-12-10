package Service;

public class CategoryDoesNotExistException extends IllegalArgumentException {

    public CategoryDoesNotExistException(String message) {
        super(message);
    }
}
