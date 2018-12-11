package service;

import org.junit.Test;

import java.math.BigDecimal;

import database.Category;

import static org.junit.Assert.assertEquals;

public class ProductTest {

    @Test
    public void updateActualPriceIfPriceChangedTest() {

        Product victim = new Product("Rasens", BigDecimal.valueOf(1),
                Category.DAIRY, BigDecimal.valueOf(40), "Latvian");

        victim.setPrice(BigDecimal.valueOf(20)); //already contains updateActualPrice()

        assertEquals(BigDecimal.valueOf(11).intValue(), victim.getActualPrice().intValue());
    }

    @Test
    public void updateActualPriceIfDiscountChangedTest() {

        Product victim = new Product("Rasens", BigDecimal.valueOf(1),
                Category.DAIRY, BigDecimal.valueOf(40), "Latvian");

        victim.setDiscount(BigDecimal.valueOf(0)); //already contains updateActualPrice()

        assertEquals(BigDecimal.valueOf(1).intValue(), victim.getActualPrice().intValue());
    }

    @Test
    public void getPriceTest() {

        Product victim = new Product("Rasens", BigDecimal.valueOf(1),
                Category.DAIRY, BigDecimal.valueOf(40), "Latvian");

        assertEquals(BigDecimal.valueOf(1), victim.getPrice());
    }

    @Test
    public void getDescriptionTest() {

        Product victim = new Product("Rasens", BigDecimal.valueOf(1),
                Category.DAIRY, BigDecimal.valueOf(40), "Latvian");

        assertEquals("Latvian", victim.getDescription());
    }

    @Test
    public void getDiscountTest() {

        Product victim = new Product("Rasens", BigDecimal.valueOf(1),
                Category.DAIRY, BigDecimal.valueOf(40), "Latvian");

        assertEquals(BigDecimal.valueOf(40), victim.getDiscount());
    }

    @Test
    public void getCategoryTest() {

        Product victim = new Product("Rasens", BigDecimal.valueOf(1),
                Category.DAIRY, BigDecimal.valueOf(40), "Latvian");

        assertEquals(Category.DAIRY, victim.getCategory());
    }

    @Test
    public void setPriceSuccessTest() {

        Product victim = new Product("Rasens", BigDecimal.valueOf(1),
                Category.DAIRY, BigDecimal.valueOf(40), "Latvian");

        victim.setPrice(BigDecimal.valueOf(20));

        assertEquals(BigDecimal.valueOf(20), victim.getPrice());
    }

    @Test (expected = IllegalArgumentException.class)
    public void setPriceFailTest() {

        Product victim = new Product("Rasens", BigDecimal.valueOf(1),
                Category.DAIRY, BigDecimal.valueOf(40), "Latvian");

        victim.setPrice(BigDecimal.valueOf(-2));
    }

    @Test
    public void setCategoryTest() {

        Product victim = new Product("Rasens", BigDecimal.valueOf(1),
                Category.DAIRY, BigDecimal.valueOf(40), "Latvian");

        victim.setCategory(Category.ALCOHOL);

        assertEquals(Category.ALCOHOL, victim.getCategory());
    }

    @Test
    public void setDescriptionTest() {

        Product victim = new Product("Rasens", BigDecimal.valueOf(1),
                Category.DAIRY, BigDecimal.valueOf(40), "Latvian");

        victim.setDescription("Test");

        assertEquals("Test", victim.getDescription());
    }

    @Test
    public void setDiscountSuccessTest() {

        Product victim = new Product("Rasens", BigDecimal.valueOf(1),
                Category.DAIRY, BigDecimal.valueOf(40), "Latvian");

        victim.setDiscount(BigDecimal.valueOf(23));

        assertEquals(BigDecimal.valueOf(23), victim.getDiscount());
    }

    @Test (expected = IllegalArgumentException.class)
    public void setDiscountFailTest() {

        Product victim = new Product("Rasens", BigDecimal.valueOf(1),
                Category.DAIRY, BigDecimal.valueOf(40), "Latvian");

        victim.setDiscount(BigDecimal.valueOf(-1));
    }

}