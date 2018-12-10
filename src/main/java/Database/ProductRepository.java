package Database;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Service.Product;
import Service.Repository;


public class ProductRepository implements Repository<Product> {

    private Map<Long, Product> products = new HashMap<>();

    @Override
    public List<Product> findAll() {

        return new ArrayList<>(products.values());
    }

    public int size() {
        return products.size();
    }

    @Override
    public Product findById(Long id) {

        if (!products.containsKey(id)) {
            throw new IllegalArgumentException("Such product was not found!");
        }

        return products.get(id);
    }

    @Override
    public List<Product> findByCategory(String category) {

        Category.checkIfCategoryExists(category);

        List<Product> filteredProductList = new ArrayList<>();

        for (Product product : products.values()) {

            if (product.getCategory() == Category.valueOf(category)) {
                filteredProductList.add(product);
            }
        }

        return filteredProductList;
    }

    @Override
    public void add(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public void deleteById(Long id) {

        if (!products.containsKey(id)) {
            throw new IllegalArgumentException("Such product was not found!");
        }

        products.remove(id);
    }

    @Override
    public void changePriceById(Long id, BigDecimal newPrice) {

        if (!products.containsKey(id)) {
            throw new IllegalArgumentException("Such product was not found!");
        }

        Product tempProduct = products.get(id);
        tempProduct.setPrice(newPrice);
        products.remove(id);
        products.put(tempProduct.getId(), tempProduct);
    }

    @Override
    public void changeCategoryById(Long id, String category) {

        Category.checkIfCategoryExists(category);

        checkIfIdExists(id);

        Product tempProduct = products.get(id);
        tempProduct.setCategory(Category.valueOf(category));
        products.remove(id);
        products.put(tempProduct.getId(), tempProduct);
    }

    @Override
    public void changeDiscountById(Long id, Double newDiscount) {

        checkIfIdExists(id);

        Product tempProduct = products.get(id);
        tempProduct.setDiscount(new BigDecimal(newDiscount));
        products.remove(id);
        products.put(tempProduct.getId(), tempProduct);
    }

    @Override
    public void changeDescriptionById(Long id, String newDescription) {

        checkIfIdExists(id);

        Product tempProduct = products.get(id);
        tempProduct.setDescription(newDescription);
        products.remove(id);
        products.put(tempProduct.getId(), tempProduct);
    }

    @Override
    public void changeDiscountByCategory(String category, Double newDiscount) {

        Category.checkIfCategoryExists(category);

        for (Product product : products.values()) {

            if (product.getCategory() == Category.valueOf(category)) {
                product.setDiscount(new BigDecimal(newDiscount));
            }
        }
    }

    public void checkIfIdExists(Long id) {
        if (!products.containsKey(id)) {
            throw new IllegalArgumentException("Such product was not found!");
        }
    }
}
