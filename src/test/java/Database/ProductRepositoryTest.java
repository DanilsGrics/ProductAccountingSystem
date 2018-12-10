package Database;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import Service.CategoryDoesNotExistException;
import Service.Product;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ProductRepositoryTest {

    ProductRepository victim = new ProductRepository();

    static Product product1 = new Product("Rasens", BigDecimal.valueOf(1),
            Category.DAIRY, BigDecimal.valueOf(40), "Latvian");
    static Product product2 = new Product("Piens", BigDecimal.valueOf(1.23),
            Category.DAIRY, BigDecimal.valueOf(30), "Latvian");
    static Product product3 = new Product("Milka", BigDecimal.valueOf(0.99),
            Category.MISCELLANEOUS, BigDecimal.valueOf(0), "Sweden");
    static Product product4 = new Product("Kinder", BigDecimal.valueOf(0.68),
            Category.MISCELLANEOUS, BigDecimal.valueOf(10), "UK");

    @Before
    public void setUp() {

        victim.add(product1);
        victim.add(product2);
        victim.add(product3);
    }

    @Test
    public void findAllTest() {

        List<Product> result = victim.findAll();

        List<Product> expected = new ArrayList<>();

        expected.add(product1);
        expected.add(product2);
        expected.add(product3);

        assertThat(result, is(expected));
    }

    @Test
    public void findByIdSuccessTest() {

        Product expected = product1;
        Product result = victim.findById(0L);

        assertEquals(result, expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByIdFailTest() {

        victim.findById(3L);

    }


    @Test
    public void findByCategorySuccessTest() {

        List<Product> expected = new ArrayList<>();

        expected.add(product1);
        expected.add(product2);

        List<Product> result = victim.findByCategory("DAIRY");

        assertThat(result, is(expected));
    }

    @Test(expected = CategoryDoesNotExistException.class)
    public void findByCategoryFailTest() {

        victim.findByCategory("SMARTPHONES");

    }

    @Test
    public void addTest() {

        ProductRepository result = new ProductRepository();

        result.add(product1);
        result.add(product2);
        result.add(product3);

        assertThat(result.size(), is(victim.size()));
    }

    @Test
    public void deleteByIdSuccessTest() {

        ProductRepository result = new ProductRepository();

        result.add(product1);
        result.add(product2);
        result.add(product3);
        result.add(product4);

        result.deleteById(3L);

        assertThat(result.size(), is(victim.size()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteByIdFailTest() {

        victim.deleteById(5L);
    }

    @Test
    public void changePriceByIdSuccessTest() {

        ProductRepository victim2 = new ProductRepository();
        Product testProduct = product4;
        victim2.add(testProduct);

        victim2.changePriceById(testProduct.getIdCounter().longValue() - 1, BigDecimal.valueOf(20));

        assertEquals(BigDecimal.valueOf(20), victim2.findById((testProduct.getId())).getPrice());
    }

    @Test(expected = IllegalArgumentException.class)
    public void changePriceByIdFailTest() {

        victim.changePriceById(10L, BigDecimal.valueOf(40));
    }

    @Test
    public void changeCategoryByIdSuccessTest() {

        ProductRepository victim2 = new ProductRepository();
        Product testProduct = product4;
        victim2.add(testProduct);

        victim2.changeCategoryById(testProduct.getIdCounter().longValue() - 1, "ALCOHOL");

        assertEquals(Category.ALCOHOL, victim2.findById((testProduct.getId())).getCategory());
    }

    @Test(expected = CategoryDoesNotExistException.class)
    public void changeCategoryByIdFailInCategoryTest() {

        victim.changeCategoryById(0L, "COMPUTERS");
    }

    @Test(expected = IllegalArgumentException.class)
    public void changeCategoryByIdFailInIdTest() {

        victim.changeCategoryById(10L, "ALCOHOL");
    }

    @Test
    public void changeDiscountByIdSuccessTest() {

        ProductRepository victim2 = new ProductRepository();
        Product testProduct = product4;
        victim2.add(testProduct);

        victim2.changeDiscountById(testProduct.getIdCounter().longValue() - 1, 20D);

        assertEquals(BigDecimal.valueOf(20), victim2.findById((testProduct.getId())).getDiscount());
    }


    @Test(expected = IllegalArgumentException.class)
    public void changeDiscountByIdFailTest() {

        victim.changeDiscountById(10L, 30D);
    }

    @Test
    public void changeDescriptionByIdSuccessTest() {

        ProductRepository victim2 = new ProductRepository();
        Product testProduct = product4;
        victim2.add(testProduct);

        victim2.changeDescriptionById(testProduct.getIdCounter().longValue() - 1, "New description");

        assertEquals("New description", victim2.findById((testProduct.getId())).getDescription());
    }

    @Test(expected = IllegalArgumentException.class)
    public void changeDescriptionByIdFailTest() {

        victim.changeDescriptionById(10L, "NewDescription");
    }

    @Test
    public void changeDiscountByCategorySuccessTest() {

        ProductRepository victim2 = new ProductRepository();
        Product testProduct = product1;
        victim2.add(testProduct);

        victim2.changeDiscountByCategory("DAIRY", 20D);

        assertEquals(BigDecimal.valueOf(20), victim2.findById((testProduct.getId())).getDiscount());

    }

    @Test(expected = CategoryDoesNotExistException.class)
    public void changeDiscountByCategoryFailTest() {

        victim.changeDiscountByCategory("COMPUTERS", 20D);
    }

    @Test
    public void checkIfIdExistsSuccessTest() {

        victim.checkIfIdExists(0L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIfIdExistsFailTest() {

        victim.checkIfIdExists(5L);
    }
}