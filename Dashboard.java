import java.util.Scanner;
import java.util.stream.Stream;
import java.util.Date;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
public class Dashboard {
    private Store store = new Store();
    private Scanner scanner = new Scanner(System.in);
    public void showLogin() {
        System.out.println("\n********************************* WELCOME TO STORE MANAGEMENT SYSTEM *************************************");
         System.out.print("\n\t\t LOGIN PAGE");
        System.out.print("\n\nEnter Username: ");
        String username = scanner.nextLine();
        System.out.print("\nEnter Password: ");
        String password = scanner.nextLine();
        if (authenticate(username, password)) {
            System.out.println("\n\t\tLogin successful!");
            printMsgWithProgressBar("\t", 25, 100);
            showMenu();
        } else {
            System.out.println("Invalid username or password. Exiting...");
        }
    }

    private boolean authenticate(String username, String password) {
        return username.equals("admin") && password.equals("password");
    }

    private void printMsgWithProgressBar(String message, int length, long timeInterval) {
        char incomplete = '#'; 
        char complete = '#'; 
        StringBuilder builder = new StringBuilder();
        Stream.generate(() -> incomplete).limit(length).forEach(builder::append);
        System.out.println(message);
        for (int i = 0; i < length; i++) {
            builder.replace(i, i + 1, String.valueOf(complete));
            String progressBar = "\r" + builder;
            System.out.print(progressBar);
            try {
                Thread.sleep(timeInterval);
            } catch (InterruptedException ignored) {
            }
        }
        System.out.println(); 
    }

    public void showMenu() {
        int choice = 0;
        while (choice != 6) {
           System.out.println("\n*********************************** DASHBOARD ***************************************************************");
System.out.println("\n\t\t\t\t1. Add Product");
System.out.println("\n\t\t\t\t2. Display Products");
System.out.println("\n\t\t\t\t3. Purchase Stock");
System.out.println("\n\t\t\t\t4. Sell Product");
System.out.println("\n\t\t\t\t5. Invoice Bill");
System.out.println("\n\t\t\t\t6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    displayProducts();
                    break;
                case 3:
                    purchaseStock();
                    break;
                case 4:
                    sellProduct();
                    break;
                case 5:
                    generateInvoice();
                    break;
                case 6:
                    System.out.println("\n********* THANK YOU ***********");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        scanner.close();
    }

    private void addProduct() {
        System.out.print("Enter Product ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Product Price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter Product Quantity: ");
        int quantity = scanner.nextInt();
        Product product = new Product(id, name, price, quantity);
        store.addProduct(product);
        System.out.println("\nProduct added successfully.");
    }

    private void displayProducts() {
        store.displayProducts();
    }

    private void purchaseStock() {
        System.out.print("Enter Product ID to purchase stock: ");
        int purchaseId = scanner.nextInt();
        System.out.print("Enter quantity to purchase: ");
        int purchaseQuantity = scanner.nextInt();
        store.purchaseStock(purchaseId, purchaseQuantity);
    }

    private void sellProduct() {
        System.out.print("Enter Product ID to sell: ");
        int sellId = scanner.nextInt();
        System.out.print("Enter quantity to sell: ");
        int sellQuantity = scanner.nextInt();
        store.sellProduct(sellId, sellQuantity);
    }
   private void generateInvoice() 
{
System.out.println("------------------------------------------------------------------------------------------------------------");
System.out.println("\t\t\t\t\t\tINVOICE BILL");
System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.println("\t\t\t\t\t\t Shiv Grocery Shop");
        System.out.println("\t\t\t\t\t\t 102 Virar Mumbai");
        System.out.println("\t\t\t\t\t\tOpposite Police Station");
        System.out.println("GSTIN: 03AWBPP8756K92\t\t\t\tContact: (+91)8421779879");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        String[] days = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        System.out.println("Date: " + formatter.format(date) + " " + days[calendar.get(Calendar.DAY_OF_WEEK) - 1]);
        Scanner scan = new Scanner(System.in);
System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();
    System.out.print("Enter Product ID for invoice: ");
    int productId = scanner.nextInt();
    System.out.print("Enter quantity: ");
    int quantity = scanner.nextInt();
    System.out.print("Is this a purchase invoice (true/false): ");
    boolean isPurchase = scanner.nextBoolean();
    scanner.nextLine(); 
    Product product = store.findProductById(productId);
    if (product != null) {
        String invoiceType = isPurchase ? "Purchase Invoice" : "Sell Invoice";
        //String date = java.time.LocalDate.now().toString();
        double price = product.getPrice();
        double gstRate = 0.18; 
        double totalAmount = price * quantity;
        double gstAmount = totalAmount * gstRate;
        double totalAmountWithGst = totalAmount + gstAmount;
        double sgst = gstAmount / 2; 
        double cgst = gstAmount / 2;

        System.out.println(invoiceType + ":");
         
System.out.println("------------------------------------------------------------------------------------------------------------");
        //System.out.printf("%-15s %-10s %-15s %-15s %-10s %-10s\n", "", "", "", "Customer Name", "", customerName);
        System.out.printf("\n%-15s %-10s %-15s %-15s %-10s %-10s\n", "Product", "Quantity", "Price", "SGST", "CGST", "Total");
System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-15s %-10d %-15.2f %-15.2f %-10.2f %-10.2f\n", product.getName(), quantity, price, sgst, cgst, totalAmount);
System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-15s %-10s %-15s %-15s %-10.2f %-10.2f\n", "", "", "", "GST (18%)", gstAmount, totalAmountWithGst);
        System.out.printf("%-15s %-10s %-15s %-15s %-10s %-10s\n", "", "", "", "", "", "");
        //System.out.printf("%-15s %-10s %-15s %-15s %-10s %-10s\n", "", "", "", "Date", "", date);
//System.out.println("------------------------------------------------------------------------------------------------------------");
System.out.println("------------------------------------------------------------------------------------------------------------");
System.out.println("\t\t\t\t\tTHANK YOU FOR SHOPPING");
System.out.println("------------------------------------------------------------------------------------------------------------");

    } else {
        System.out.println("Product not found.");
    }
}
    public static void main(String[] args) {
        Dashboard dashboard = new Dashboard();
        dashboard.showLogin(); 
    }
}
