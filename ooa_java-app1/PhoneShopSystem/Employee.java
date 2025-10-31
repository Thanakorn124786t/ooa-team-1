package PhoneShopSystem;

import java.text.SimpleDateFormat;

public class Employee {
    private Database db;

    public Employee(Database db) {
        this.db = db;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== เมนูพนักงาน ===");
            System.out.println("1. แสดงยอดขายทั้งหมด");
            System.out.println("2. แสดงรายการซ่อมทั้งหมด");
            System.out.println("3. สรุปสินค้าขายดีที่สุด");
            System.out.println("4. สรุปรุ่นที่ซ่อมบ่อยที่สุด");
            System.out.println("5. ออกจากระบบ");
            System.out.print("เลือกหมายเลข: ");

            int choice;
            try {
                choice = Integer.parseInt(System.console().readLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ กรุณาใส่ตัวเลขเท่านั้น");
                continue;
            }

            switch (choice) {
                case 1 -> showMonthlySales();
                case 2 -> showMonthlyRepairs();
                case 3 -> showBestSellingProduct();
                case 4 -> showMostCommonRepair();
                case 5 -> {
                    System.out.println("ออกจากระบบ...");
                    return;
                }
                default -> System.out.println("❌ กรุณาเลือกเมนูให้ถูกต้อง");
            }
        }
    }

    // 📦 แสดงยอดขายทั้งหมดในรูปแบบใบเสร็จ
    private void showMonthlySales() {
        if (db.purchases.isEmpty()) {
            System.out.println("❌ ยังไม่มีข้อมูลการขาย");
            return;
        }

        double totalAll = 0;
        System.out.println("\n========================================");
        System.out.println("          รายงานยอดขายทั้งหมด");
        System.out.println("========================================");

        for (Database.Purchase p : db.purchases) {
            totalAll += p.total;
            printReceipt(p);
        }
        System.out.println("----------------------------------------");
        System.out.printf("รวมยอดขายทั้งหมด: %,.2f บาท\n", totalAll);
        System.out.println("========================================\n");
    }

    private void printReceipt(Database.Purchase p) {
        System.out.println("\n----------------------------------------");
        System.out.println("เลขที่รายการ: " + p.id);
        System.out.println("วันที่: " + p.date);
        System.out.println("ลูกค้า: " + p.customerName);
        System.out.println("สินค้า: " + p.product);
        System.out.println("จำนวน: " + p.qty + " เครื่อง");
        System.out.printf("ราคาต่อหน่วย: %,.2f บาท\n", p.unitPrice);
        System.out.printf("รวม: %,.2f บาท\n", p.total);
        System.out.println("ชำระโดย: " + p.payment);
        System.out.println("----------------------------------------");
    }

    // 🔧 แสดงรายการซ่อมทั้งหมดในรูปแบบใบเสร็จ
    private void showMonthlyRepairs() {
        if (db.repairs.isEmpty()) {
            System.out.println("❌ ยังไม่มีข้อมูลการซ่อม");
            return;
        }

        double totalCost = 0;
        System.out.println("\n========================================");
        System.out.println("          รายงานการซ่อมทั้งหมด");
        System.out.println("========================================");

        for (Database.Repair r : db.repairs) {
            totalCost += r.cost;
            printRepairReceipt(r);
        }
        System.out.println("----------------------------------------");
        System.out.printf("รวมค่าซ่อมทั้งหมด: %,.2f บาท\n", totalCost);
        System.out.println("========================================\n");
    }

    private void printRepairReceipt(Database.Repair r) {
        System.out.println("\n----------------------------------------");
        System.out.println("เลขที่ใบสั่งซ่อม: " + r.id);
        System.out.println("วันที่: " + r.date);
        System.out.println("ลูกค้า: " + r.customerName);
        System.out.println("รุ่น: " + r.model);
        System.out.println("ยี่ห้อ: " + r.brand);
        System.out.println("อาการ: " + r.symptom);
        System.out.println("รายละเอียด: " + r.detail);
        System.out.println("ค่าใช้จ่าย: " + String.format("%,.2f", r.cost) + " บาท");
        System.out.println("----------------------------------------");
    }

    // 📈 แสดงสินค้าขายดีที่สุด
    private void showBestSellingProduct() {
        String product = db.getBestSellingProduct();
        if (product == null) {
            System.out.println("❌ ยังไม่มีข้อมูลการขาย");
            return;
        }
        int qty = db.getProductCount(product);
        double total = db.getTotalSalesByProduct(product);

        System.out.println("\n========================================");
        System.out.println("           สรุปสินค้าขายดีที่สุด");
        System.out.println("========================================");
        System.out.println("สินค้า: " + product);
        System.out.println("จำนวนขาย: " + qty + " เครื่อง");
        System.out.printf("ยอดขายรวม: %,.2f บาท\n", total);
        System.out.println("========================================\n");
    }

    // 🔧 แสดงรุ่นที่ซ่อมบ่อยที่สุด
    private void showMostCommonRepair() {
        String model = db.getMostCommonRepairModel();
        if (model == null) {
            System.out.println("❌ ยังไม่มีข้อมูลการซ่อม");
            return;
        }
        int count = db.getRepairCountByModel(model);

        System.out.println("\n========================================");
        System.out.println("           สรุปรุ่นที่ซ่อมบ่อยที่สุด");
        System.out.println("========================================");
        System.out.println("รุ่น: " + model);
        System.out.println("จำนวนครั้ง: " + count + " เคส");
        System.out.println("========================================\n");
    }
}
