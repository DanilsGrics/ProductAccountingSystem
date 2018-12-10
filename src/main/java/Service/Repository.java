package Service;

import java.math.BigDecimal;
import java.util.List;

public interface Repository<T> {

    List<T> findAll();

    T findById(Long id) throws IllegalArgumentException;

    List<T> findByCategory(String category) throws IllegalArgumentException;

    void add(T item);

    void deleteById(Long id) throws IllegalArgumentException;

    void changePriceById(Long id, BigDecimal newPrice) throws IllegalArgumentException;

    void changeCategoryById(Long id, String category) throws IllegalArgumentException;

    void changeDiscountById(Long id, Double newDiscount) throws IllegalArgumentException;

    void changeDescriptionById(Long id, String newDescription) throws IllegalArgumentException;

    void changeDiscountByCategory(String category, Double newDiscount) throws IllegalArgumentException;
}
