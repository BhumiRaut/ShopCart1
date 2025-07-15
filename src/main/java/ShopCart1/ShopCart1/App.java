package ShopCart1.ShopCart1;

import java.util.Scanner;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductDao productDao = new ProductDao();
        OrderDao orderDao = new OrderDao();

        while (true) {
            System.out.println("\n=== Online Shopping Cart ===");
            System.out.println("1) Add Product");
            System.out.println("2) Buy Product");
            System.out.println("3) View All Products");
            System.out.println("4) Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();

                    Product product = new Product();
                    product.setName(name);
                    product.setPrice(price);
                    product.setQuantity(quantity);
                    productDao.save(product);
                    System.out.println("Product added!");
                    break;

                case 2:
                    System.out.print("Enter product ID to buy: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter quantity to buy: ");
                    int qty = scanner.nextInt();

                    Product p = productDao.getById(id);
                    if (p != null && p.getQuantity() >= qty) {
                        Order order = new Order();
                        order.setProduct(p);
                        order.setQuantity(qty);
                        orderDao.save(order);

                        p.setQuantity(p.getQuantity() - qty);
                        productDao.update(p);
                        System.out.println("Order placed successfully!");
                    } else {
                        System.out.println("Product not found or insufficient quantity.");
                    }
                    break;

                case 3:
                    List<Product> products = productDao.getAll();
                    if (products.isEmpty()) {
                        System.out.println("No products found.");
                    } else {
                        for (Product pr : products) {
                            System.out.println(pr.getId() + " - " + pr.getName() + " : ₹" + pr.getPrice() + " (Qty: " + pr.getQuantity() + ")");
                        }
                    }
                    break;

                case 4:
                    System.out.println("Thank you for using Online Shopping Cart!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}