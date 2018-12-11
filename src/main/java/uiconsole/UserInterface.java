package uiconsole;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

import database.Category;
import database.ProductRepository;
import service.CategoryDoesNotExistException;
import service.Product;


public class UserInterface {

    public void menu() throws CategoryDoesNotExistException {

        ProductRepository productRepository = new ProductRepository();
        Scanner scanner = new Scanner(System.in);
        Double newDiscount;

        for (; ; ) {

            System.out.print("_____________________________________" +
                    "\n1. Add product" +
                    "\n2. Get product by ID" +
                    "\n3. Get all products" +
                    "\n4. Delete product by ID" +
                    "\n5. Get products by category" +
                    "\n6. Change price of product by ID" +
                    "\n7. Change category of product by ID" +
                    "\n8. Change discount of product by ID" +
                    "\n9. Change description of product by ID" +
                    "\n10. Change discount by category\n" +
                    "\nList. Get list of categories\n" +
                    "Exit. To Exit\n" +
                    "_____________________________________" +
                    "\nType number of preferred action, please: ");

            String choice = scanner.next();

            Long id;
            switch (choice) {

                case "1":
                    System.out.print("Name: ");
                    String name = scanner.next();


                    System.out.print("Price: ");
                    String stringPrice = scanner.next();
                    BigDecimal price;
                    try {
                        price = new BigDecimal(stringPrice.replace(",", "."));
                    } catch (NumberFormatException n) {
                        System.out.print("\nWrong price format!\n");
                        break;
                    }


                    System.out.print("Category: ");
                    String stringCategory = scanner.next().toUpperCase();

                    System.out.print("Do you want add description? (y/n)");
                    String answer = scanner.next();
                    String description = "";
                    if (answer.equals("y")) {
                        System.out.print("Description (if no description just input 0): ");
                        description = scanner.next();
                    }

                    System.out.print("Do you want add discount? (y/n)");
                    answer = scanner.next();
                    BigDecimal discount = new BigDecimal(0);
                    if (answer.equals("y")) {
                        System.out.print("Discount (%) (if no discount just input 0): ");
                        String stringDiscount = scanner.next();
                        try {
                            discount = new BigDecimal(stringDiscount.replace(",", "."));
                        } catch (NumberFormatException n) {
                            System.out.print("\nTry to input integers for discount!\n");
                            break;
                        }
                    }

                    try {
                        productRepository.add(new Product(name, price, Category.checkIfCategoryExists(stringCategory), discount, description));
                        System.out.println("Item added successfully!");
                    } catch (CategoryDoesNotExistException e) {
                        System.out.println("Such category does not exist!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error occurred: " + e.getMessage());
                    }

                    break;

                case "2":

                    System.out.print("ID of product: ");
                    try {
                        id = scanner.nextLong();
                    } catch (InputMismatchException e) {
                        System.out.println("Try to input integers as ID!");
                        break;
                    }

                    try {
                        System.out.println(productRepository.findById(id));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error occurred: " + e.getMessage());
                    }
                    break;

                case "3":
                    System.out.println(productRepository.findAll());
                    break;

                case "4":
                    System.out.print("ID of product: ");
                    try {
                        id = scanner.nextLong();
                    } catch (InputMismatchException e) {
                        System.out.println("Try to input integers as ID!");
                        break;
                    }

                    try {
                        productRepository.deleteById(id);
                        System.out.println("Item deleted successfully!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error occurred: " + e.getMessage());
                    }

                    break;

                case "5":
                    System.out.print("Category of product: ");
                    stringCategory = scanner.next().toUpperCase();

                    System.out.println(productRepository.findByCategory(stringCategory));

                    break;

                case "6":
                    System.out.print("New price of product: ");
                    stringPrice = scanner.next();
                    try {
                        price = new BigDecimal(stringPrice.replace(",", "."));
                    } catch (NumberFormatException n) {
                        System.out.print("\nWrong price format!\n");
                        break;
                    }

                    System.out.print("ID of product: ");
                    try {
                        id = scanner.nextLong();
                    } catch (InputMismatchException e) {
                        System.out.println("Try to input integers as ID!");
                        break;
                    }

                    try {
                        productRepository.changePriceById(id, price);
                        System.out.println("Price edited successfully!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error occurred: " + e.getMessage());
                    }

                    break;

                case "7":
                    System.out.print("New category of product: ");
                    String newCategory = scanner.next().toUpperCase();

                    System.out.print("ID of product: ");
                    try {
                        id = scanner.nextLong();
                    } catch (InputMismatchException e) {
                        System.out.println("Try to input integers as ID!");
                        break;
                    }

                    try {
                        productRepository.changeCategoryById(id, newCategory);
                        System.out.println("Category edited successfully!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error occurred: " + e.getMessage());
                    }

                    break;

                case "8":
                    System.out.print("New discount of product: ");
                    try {
                        newDiscount = scanner.nextDouble();
                    } catch (InputMismatchException e) {
                        System.out.println("Try to input integers for discount!");
                        break;
                    }

                    System.out.print("ID of product: ");
                    try {
                        id = scanner.nextLong();
                    } catch (InputMismatchException e) {
                        System.out.println("Try to input integers as ID!");
                        break;
                    }

                    try {
                        productRepository.changeDiscountById(id, newDiscount);
                        System.out.println("Discount edited successfully!");
                    } catch (IllegalArgumentException a) {
                        System.out.println("Error occurred: " + a.getMessage());
                    }

                    break;

                case "9":
                    System.out.print("New description of product: ");
                    String newDescription = scanner.next();

                    System.out.print("ID of product: ");
                    try {
                        id = scanner.nextLong();
                    } catch (InputMismatchException e) {
                        System.out.println("Try to input integers as ID!");
                        break;
                    }

                    try {
                        productRepository.changeDescriptionById(id, newDescription);
                        System.out.println("Description edited successfully!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error occurred: " + e.getMessage());
                    }

                    break;

                case "10":
                    System.out.print("Category of product: ");
                    stringCategory = scanner.next().toUpperCase();

                    System.out.print("New discount for category: ");
                    try {
                        newDiscount = scanner.nextDouble();
                    } catch (InputMismatchException e) {
                        System.out.println("Try to input integers for discount!");
                        break;
                    }

                    try {
                        productRepository.changeDiscountByCategory(stringCategory, newDiscount);
                        System.out.println("New discount for category added successfully!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error occurred: " + e.getMessage());
                    }

                    break;

                case "List":

                    System.out.println(java.util.Arrays.asList(Category.values()));
                    break;

                case "Exit":
                    System.exit(0);

                default:
                    System.out.println("\nTry again!");
                    break;
            }
        }

    }
}
