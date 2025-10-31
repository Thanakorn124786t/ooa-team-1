package PhoneShopSystem;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Member {
    private Database db;
    private Scanner sc;
    private ArrayList<String> purchaseHistory;

    public Member(Database db) {
        this.db = db;
        this.sc = new Scanner(System.in);
        this.purchaseHistory = new ArrayList<>();
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== เมนูลูกค้า ===");
            System.out.println("1. ซื้อโทรศัพท์");
            System.out.println("2. แจ้งซ่อมโทรศัพท์");
            System.out.println("3. ขอคืนสินค้า");
            System.out.println("4. แสดงประวัติการซื้อ");
            System.out.println("5. กลับไปหน้าหลัก");
            System.out.print("เลือกเมนู: ");
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ กรุณาใส่ตัวเลข");
                continue;
            }

            switch (choice) {
                case 1 -> buyPhone();
                case 2 -> repairPhone();
                case 3 -> returnProduct();
                case 4 -> showPurchaseHistory();
                case 5 -> {
                    return;
                }
                default -> System.out.println("❌ กรุณาเลือกเมนูให้ถูกต้อง");
            }
        }
    }

    // 🛒 ซื้อสินค้า
    private void buyPhone() {
        String[] products = { "iPhone 17", "iPhone 17 Pro", "iPhone 17 Pro Max",
                "Samsung S25", "Samsung S25 Plus", "Samsung S25 Ultra" };
        double[] prices = { 32900, 35900, 48900, 29900, 32900, 42900 };

        System.out.println("\n📱 เลือกสินค้าที่ต้องการซื้อ:");
        for (int i = 0; i < products.length; i++)
            System.out.printf("%d. %s (%.2f บาท)\n", i + 1, products[i], prices[i]);

        System.out.print("เลือกหมายเลขสินค้า: ");
        int choice;
        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("❌ ไม่ถูกต้อง");
            return;
        }

        if (choice < 1 || choice > products.length) {
            System.out.println("❌ สินค้าไม่ถูกต้อง");
            return;
        }

        String product = products[choice - 1];
        double price = prices[choice - 1];

        System.out.print("กรอกชื่อผู้ซื้อ: ");
        String name = sc.nextLine();

        // วิธีชำระเงินแบบแนวตั้ง
        System.out.println("\n💳 เลือกวิธีชำระเงิน:");
        System.out.println("1. เงินสด");
        System.out.println("2. บัตรเครดิต");
        System.out.println("3. โอนเงิน");
        System.out.println("4. ผ่อนชำระ");
        System.out.print("เลือกหมายเลข: ");
        int payChoice;
        try {
            payChoice = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("❌ ไม่ถูกต้อง");
            return;
        }

        String payment = switch (payChoice) {
            case 1 -> "เงินสด";
            case 2 -> "บัตรเครดิต";
            case 3 -> "โอนเงิน";
            case 4 -> "ผ่อนชำระ";
            default -> {
                System.out.println("❌ วิธีชำระเงินไม่ถูกต้อง");
                yield null;
            }
        };
        if (payment == null)
            return;

        System.out.print("กรอกจำนวนเครื่อง: ");
        int qty;
        try {
            qty = Integer.parseInt(sc.nextLine());
            if (qty <= 0) {
                System.out.println("❌ จำนวนต้องมากกว่า 0");
                return;
            }
        } catch (Exception e) {
            System.out.println("❌ กรุณาใส่ตัวเลข");
            return;
        }

        String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        db.addPurchase(name, product, payment, price, qty, date);
        purchaseHistory.add(String.format("%s | %s | จำนวน: %d | วิธีชำระ: %s | รวม: %.2f บาท", date, product, qty,
                payment, price * qty));

        System.out.println("✅ บันทึกข้อมูลเรียบร้อย!");
        printPurchaseReceipt(name, product, payment, price, qty);
    }

    // 🧾 ใบเสร็จซื้อสินค้า
    private void printPurchaseReceipt(String name, String product, String payment, double unitPrice, int qty) {
        String staff = "พี่วิน";
        String date = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());
        int orderId = db.purchases.size(); // S001, S002,...
        System.out.println("\n========================================");
        System.out.println("           ใบเสร็จการชำระเงิน");
        System.out.println("              ร้านแอปสโตร์");
        System.out.println("ติดต่อ: LINE:@Appstore | Tel: 0932783888");
        System.out.println("Staff: " + staff);
        System.out.println("----------------------------------------");
        System.out.println("เลขที่รายการ: S" + String.format("%03d", orderId));
        System.out.println("วันที่: " + date);
        System.out.println("ชำระโดย: " + payment);
        System.out.println("----------------------------------------");
        System.out.printf("%-25s x%d %,.2f\n", product, qty, unitPrice);
        System.out.println("----------------------------------------");
        System.out.printf("รวมทั้งสิ้น: %,.2f บาท\n", unitPrice * qty);
        System.out.println("*สินค้ารับประกัน 7 วัน*");
        System.out.println("ขอบคุณที่ใช้บริการ ❤️");
        System.out.println("========================================\n");
    }

    // 🔧 แจ้งซ่อม
    private void repairPhone() {
        System.out.println("\n🔧========= แบบฟอร์มรับบริการซ่อมอุปกรณ์ =========");
        System.out.print("กรอกชื่อผู้แจ้งซ่อม: ");
        String name = sc.nextLine();
        System.out.print("รุ่น: ");
        String model = sc.nextLine();
        System.out.print("ยี่ห้อ: ");
        String brand = sc.nextLine();
        System.out.print("IMEI: ");
        String imei = sc.nextLine();
        System.out.print("สีเครื่อง: ");
        String color = sc.nextLine();
        System.out.print("เบอร์โทร: ");
        String phone = sc.nextLine();
        System.out.print("อาการเสีย: ");
        String symptom = sc.nextLine();
        System.out.print("รายละเอียด: ");
        String detail = sc.nextLine();
        System.out.print("ราคาค่าซ่อม (บาท): ");
        double cost;
        try {
            cost = Double.parseDouble(sc.nextLine());
            if (cost < 0) {
                System.out.println("❌ ราคาต้อง >=0");
                return;
            }
        } catch (Exception e) {
            System.out.println("❌ ราคาต้องเป็นตัวเลข");
            return;
        }

        String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        db.addRepair(name, symptom, model, brand, imei, color, phone, detail, cost, date);
        System.out.println("✅ บันทึกการซ่อมเรียบร้อย!");
        printRepairReceipt(name, model, brand, imei, color, phone, symptom, detail, cost);
    }

    // 🧾 ใบเสร็จซ่อมสินค้า
    private void printRepairReceipt(String name, String model, String brand, String imei, String color, String phone,
            String symptom, String detail, double cost) {
        String date = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());
        int repairId = db.repairs.size(); // R001, R002,...
        System.out.println("\n========================================");
        System.out.println("           ใบเสร็จซ่อมสินค้า");
        System.out.println("              ร้านแอปสโตร์");
        System.out.println("----------------------------------------");
        System.out.println("เลขที่ใบสั่งซ่อม: R" + String.format("%03d", repairId));
        System.out.println("วันที่: " + date);
        System.out.println("ลูกค้า: " + name);
        System.out.println("รุ่น: " + model + " | ยี่ห้อ: " + brand + " | IMEI: " + imei);
        System.out.println("สี: " + color + " | โทร: " + phone);
        System.out.println("อาการ: " + symptom);
        System.out.println("รายละเอียด: " + detail);
        System.out.println("----------------------------------------");
        System.out.printf("ค่าซ่อม: %,.2f บาท\n", cost);
        System.out.println("*สินค้ารับประกันการซ่อม 7 วัน*");
        System.out.println("ขอบคุณที่ใช้บริการ ❤️");
        System.out.println("========================================\n");
    }

    // ↩️ คืนสินค้า
    private void returnProduct() {
        System.out.print("กรอกชื่อผู้ขอคืน: ");
        String name = sc.nextLine();
        System.out.print("เหตุผลการคืนสินค้า: ");
        String reason = sc.nextLine();
        db.addReturn("ลูกค้า: " + name + " คืนสินค้า | เหตุผล: " + reason);

        System.out.println("\n========================================");
        System.out.println("           ใบเสร็จคืนสินค้า");
        System.out.println("              ร้านแอปสโตร์");
        System.out.println("----------------------------------------");
        System.out.println("ลูกค้า: " + name);
        System.out.println("เหตุผล: " + reason);
        System.out.println("วันที่: " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
        System.out.println("ขอบคุณที่ใช้บริการ ❤️");
        System.out.println("========================================\n");
    }

    // 🧾 ประวัติการซื้อ
    private void showPurchaseHistory() {
        if (purchaseHistory.isEmpty()) {
            System.out.println("❌ ยังไม่มีรายการซื้อสินค้า");
            return;
        }
        System.out.println("\n🧾 ประวัติการซื้อทั้งหมด (ใบเสร็จ):");
        for (int i = 0; i < purchaseHistory.size(); i++) {
            System.out.println("========================================");
            System.out.println(purchaseHistory.get(i));
            System.out.println("========================================\n");
        }
    }
}
