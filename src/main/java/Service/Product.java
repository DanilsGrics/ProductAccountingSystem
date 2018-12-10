package Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

import Database.Category;


public class Product {

    private static final AtomicLong idCounter = new AtomicLong(0);

    private String name;
    private Long id;
    private BigDecimal price;
    private Category category;
    private BigDecimal discount;
    private BigDecimal actualPrice;
    private String description;

    public Product(String name, BigDecimal price, Category category, BigDecimal discount, String description) throws CategoryDoesNotExistException {

        setPrice(price);
        setCategory(category);
        setDiscount(discount);
        this.id = idCounter.getAndIncrement();
        this.name = name;
        this.description = description;
    }

    public void updateActualPrice() {
        if (discount != null)
            this.actualPrice = this.price.subtract(this.price.multiply(this.discount.multiply(new BigDecimal(0.01))));
    }

    public BigDecimal getPrice() {
        return price;
    }

    public static AtomicLong getIdCounter() {
        return idCounter;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setPrice(BigDecimal price) {
        if (price.signum() <= 0) {
            throw new IllegalArgumentException("The price is not defined or defined incorrectly!");
        }
        this.price = price;
        updateActualPrice();
    }

    public void setCategory(Category category) {

        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDiscount(BigDecimal discount) {
        if (discount.signum() < 0 || discount.signum() > 100) {
            throw new IllegalArgumentException("The discount cannot be less than 0 or more than 100%!");
        }
        this.discount = discount;
        updateActualPrice();
    }

    @Override
    public String toString() {

        return "\nProduct information:\nID: " + id + "\nName: " + name + "\nCategory: " + category +
                "\nRegular price: " + price.doubleValue() + " EUR\nDiscount: " + discount + "%"
                + "\nActual price: " + actualPrice.doubleValue() + " EUR\nDescription: " + description + "\n";
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Product that = (Product) o;

        return Objects.equals(name, that.name) &&
                Objects.equals(category, that.category) &&
                Objects.equals(price, that.price) &&
                Objects.equals(discount, that.discount) &&
                Objects.equals(description, that.description) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return id.hashCode() + name.hashCode() + price.hashCode() + category.hashCode()
                + discount.hashCode() + actualPrice.hashCode() + description.hashCode();
    }
}
