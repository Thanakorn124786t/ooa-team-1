package PhoneShopSystem;

import java.util.Scanner;

public class Employee {
    private Database db;
    private Scanner sc;

    public Employee(Database db) {
        this.db = db;
        this.sc = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== เมนูพนักงาน ===");
            System.out.println("1. ดูข้อมูลการสั่งซื้อ");
            System.out.println("2. ดูข้อมูลการแจ้งซ่อม");
            System.out.println("3. ดูข้อมูลการคืนสินค้า");
            System.out.println("4. ดูการแจ้งเตือนทั้งหมด");
            System.out.println("5. กลับไปหน้าหลัก");
            System.out.print("เลือกเมนู: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> showPurchases();
                case 2 -> showRepairs();
                case 3 -> showReturns();
                case 4 -> showNotifications();
                case 5 -> { return; }
                default -> System.out.println("❌ กรุณาเลือกเมนูให้ถูกต้อง");
            }
        }
    }

    private void showPurchases() {
        System.out.println("\n📦 รายการสั่งซื้อทั้งหมด:");
        if (db.purchases.isEmpty()) System.out.println("ไม่มีข้อมูลการสั่งซื้อ");
        else db.purchases.forEach(System.out::println);
    }

    private void showRepairs() {
        System.out.println("\n🔧 รายการแจ้งซ่อมทั้งหมด:");
        if (db.repairs.isEmpty()) System.out.println("ไม่มีข้อมูลการแจ้งซ่อม");
        else db.repairs.forEach(System.out::println);
    }

    private void showReturns() {
        System.out.println("\n↩️ รายการคืนสินค้าทั้งหมด:");
        if (db.returns.isEmpty()) System.out.println("ไม่มีข้อมูลการคืนสินค้า");
        else db.returns.forEach(System.out::println);
    }

    private void showNotifications() {
        System.out.println("\n🔔 การแจ้งเตือนทั้งหมด:");
        if (db.notifications.isEmpty()) System.out.println("ไม่มีการแจ้งเตือนใหม่");
        else db.notifications.forEach(System.out::println);
    }
}
