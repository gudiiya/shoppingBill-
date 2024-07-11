import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

class Product {
    private String id;
    private String pname;
    private int qty;
    private double price;
    private double totalPrice;

    Product(String id, String pname, int qty, double price, double totalPrice) {
        this.id = id;
        this.pname = pname;
        this.qty = qty;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public String getPname() {
        return pname;
    }

    public int getQty() {
        return qty;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public static void displayFormat() {
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.printf("%-15s %-20s %-10s %-15s %-15s\n", "Product ID", "Name", "Quantity", "Rate", "Total Price");
        System.out.println("---------------------------------------------------------------------------------------");
    }

    public void display() {
        System.out.printf("%-15s %-20s %-10d %-15.2f %-15.2f\n", id, pname, qty, price, totalPrice);
    }
}

public class ShoppingBill {
    public static void main(String[] args) {
        String id = null;
        String productName = null;
        int quantity = 0;
        double price = 0.0;
        double totalPrice = 0.0;
        double overAllPrice = 0.0;
        double cgst, sgst, subtotal = 0.0, discount = 0.0;
        char choice = '\0';

        System.out.println("------------------------------------------------------------------------------------------------------------");
System.out.println("\t\t\t\t\t\tINVOICE BILL");
System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.println("\t\t\t\t\t\tMetro Mart Grocery Shop");
        System.out.println("\t\t\t\t\t\t3/98 Mecrobertganj New Mumbai");
        System.out.println("\t\t\t\t\t\tOpposite Metro Walk");
        System.out.println("GSTIN: 03AWBPP8756K92\t\t\t\tContact: (+91)8421779879");

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        String[] days = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        System.out.println("Date: " + formatter.format(date) + " " + days[calendar.get(Calendar.DAY_OF_WEEK) - 1]);

        Scanner scan = new Scanner(System.in);

        System.out.print("Enter Customer Name: ");
        String customername = scan.nextLine();

        List<Product> product = new ArrayList<>();

        do {
            System.out.println("Enter the product details:");
            System.out.print("Product ID: ");
            id = scan.nextLine();
            System.out.print("Product Name: ");
            productName = scan.nextLine();
            System.out.print("Quantity: ");
            quantity = scan.nextInt();
            System.out.print("Price (per unit): ");
            price = scan.nextDouble();

            totalPrice = price * quantity;
            overAllPrice += totalPrice;

            product.add(new Product(id, productName, quantity, price, totalPrice));

            System.out.print("Want to add more items? (y or n): ");
            choice = scan.next().charAt(0);
            scan.nextLine();
        } while (choice == 'y' || choice == 'Y');

        Product.displayFormat();
        for (Product p : product) {
            p.display();
        }

        System.out.println("\n\n\t\t\t\t\t\tTotal Amount (Rs.): " + overAllPrice);

        discount = overAllPrice * 0.02;
        System.out.println("\n\t\t\t\t\t\tDiscount (Rs.): " + discount);

        subtotal = overAllPrice - discount;
        System.out.println("\n\t\t\t\t\t\tSubtotal: " + subtotal);

        sgst = overAllPrice * 0.12;
        System.out.println("\n\t\t\t\t\t\tSGST (%): " + sgst);

        cgst = overAllPrice * 0.12;
        System.out.println("\n\t\t\t\t\t\tCGST (%): " + cgst);

        System.out.println("\n\t\t\t\t\t\tInvoice Total: " + (subtotal + cgst + sgst));

        System.out.println("------------------------------------------------------------------------------------------------------------");
System.out.println("\t\t\t\t\t\tTHANK YOU FOR SHOPPING");
System.out.println("------------------------------------------------------------------------------------------------------------");

        scan.close();
    }
}
