import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            System.out.println("Product List:");
            System.out.println("---------------------------------------------------------------");
            System.out.printf("%-5s %-15s %-10s %-10s\n", "ID", "Name", "Price", "Quantity");
            System.out.println("---------------------------------------------------------------");
            for (Product product : products) {
                System.out.printf("%-5d %-15s %-10.2f %-10d\n", product.getId(), product.getName(), product.getPrice(), product.getQuantity());
            }
            System.out.println("---------------------------------------------------------------");
        }
    }

    public Product findProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public void purchaseStock(int id, int quantity) {
        Product product = findProductById(id);
        if (product != null) {
            product.purchaseStock(quantity);
            System.out.println("Stock purchased successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    public void sellProduct(int id, int quantity) {
        Product product = findProductById(id);
        if (product != null) {
            if (product.sellStock(quantity)) {
                System.out.println("Product sold successfully.");
            } else {
                System.out.println("Insufficient stock.");
            }
        } else {
            System.out.println("Product not found.");
        }
    }

    public void invoiceBill(Product product, int quantity, boolean isPurchase, String customerName) {
        String invoiceType = isPurchase ? "Purchase Invoice" : "Sell Invoice";
        double gstRate = 0.18; // GST rate is 18%

        // Calculate total amount before taxes
        double totalAmount = product.getPrice() * quantity;

        // Calculate GST amount
        double gstAmount = totalAmount * gstRate;

        // Calculate total amount including GST
        double totalAmountWithGst = totalAmount + gstAmount;

        // Calculate SGST and CGST
        double sgst = gstAmount / 2; // Assuming equal SGST and CGST
        double cgst = gstAmount / 2;

        String invoiceDate = java.time.LocalDate.now().toString();

        System.out.println(invoiceType + ":");
        System.out.println("---------------------------------------------------------------");
        System.out.printf("%-15s %-10s %-15s %-15s %-10s %-10s\n", "Product", "Quantity", "Price", "SGST", "CGST", "Total");
        System.out.println("---------------------------------------------------------------");
        System.out.printf("%-15s %-10d %-15.2f %-15.2f %-10.2f %-10.2f\n", product.getName(), quantity, product.getPrice(), sgst, cgst, totalAmount);
        System.out.println("---------------------------------------------------------------");
        System.out.printf("%-15s %-10s %-15s %-15s %-10.2f %-10.2f\n", "", "", "", "GST (18%)", gstAmount, totalAmountWithGst);
        System.out.printf("%-15s %-10s %-15s %-15s %-10s %-10s\n", "", "", "", "Total Amount", "", "");
        System.out.printf("%-15s %-10s %-15s %-15s %-10s %-10s\n", "", "", "", "Date", "", invoiceDate);
        if (!isPurchase) {
            System.out.printf("%-15s %-10s %-15s %-15s %-10s %-10s\n", "", "", "", "Customer Name", "", customerName);
        }
        System.out.println("---------------------------------------------------------------");
    }
}
