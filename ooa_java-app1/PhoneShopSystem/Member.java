package PhoneShopSystem;

import java.util.Scanner;

public class Member {
    private Database db;
    private Scanner sc;

    public Member(Database db) {
        this.db = db;
        this.sc = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== เมนูลูกค้า ===");
            System.out.println("1. ซื้อโทรศัพท์");
            System.out.println("2. แจ้งซ่อมโทรศัพท์");
            System.out.println("3. ขอคืนสินค้า");
            System.out.println("4. กลับไปหน้าหลัก");
            System.out.print("เลือกเมนู: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> buyPhone();
                case 2 -> repairPhone();
                case 3 -> returnProduct();
                case 4 -> { return; }
                default -> System.out.println("❌ กรุณาเลือกเมนูให้ถูกต้อง");
            }
        }
    }

    // 🛒 ฟังก์ชันซื้อโทรศัพท์
    private void buyPhone() {
        System.out.println("\n📱 เลือกสินค้าที่ต้องการซื้อ:");
        System.out.println("1. iPhone 17 Pro");
        System.out.println("2. Samsung S25 Ultra");
        System.out.print("เลือกหมายเลขสินค้า: ");
        int choice = sc.nextInt();
        sc.nextLine();

        String product = "";
        if (choice == 1) product = "iPhone 17 Pro";
        else if (choice == 2) product = "Samsung S25 Ultra";
        else {
            System.out.println("❌ สินค้าที่เลือกไม่ถูกต้อง");
            return;
        }

        System.out.print("กรอกชื่อผู้สั่งซื้อ: ");
        String name = sc.nextLine();

        // ✅ เพิ่มส่วนเลือกวิธีชำระเงิน
        System.out.println("\n💳 เลือกวิธีการชำระเงิน:");
        System.out.println("1. เงินสด");
        System.out.println("2. บัตรเครดิต");
        System.out.println("3. โอนเงิน");
        System.out.println("4. ผ่อนชำระ");
        System.out.print("เลือกหมายเลขวิธีชำระเงิน: ");
        int payChoice = sc.nextInt();
        sc.nextLine();

        String payment = "";
        if (payChoice == 1) payment = "เงินสด";
        else if (payChoice == 2) payment = "บัตรเครดิต";
        else if (payChoice == 3) payment = "โอนเงิน";
        else if (payChoice == 4) payment = "ผ่อนชำระ";
        else {
            System.out.println("❌ วิธีการชำระเงินไม่ถูกต้อง");
            return;
        }

        // ✅ เพิ่มข้อมูลวิธีการชำระเงินในข้อมูลการสั่งซื้อ
        String data = "ลูกค้า: " + name + " ซื้อสินค้า: " + product + " | วิธีชำระเงิน: " + payment;
        db.addPurchase(data);
        System.out.println("✅ บันทึกข้อมูลการสั่งซื้อเรียบร้อย");
    }

    // 🔧 ฟังก์ชันแจ้งซ่อมโทรศัพท์
    private void repairPhone() {
        System.out.print("\nกรอกชื่อผู้แจ้งซ่อม: ");
        String name = sc.nextLine();
        System.out.print("กรอกรายละเอียดปัญหา: ");
        String detail = sc.nextLine();

        String data = "ลูกค้า: " + name + " แจ้งซ่อม: " + detail;
        db.addRepair(data);
        System.out.println("✅ บันทึกข้อมูลการแจ้งซ่อมเรียบร้อย");
    }

    // ↩️ ฟังก์ชันขอคืนสินค้า
    private void returnProduct() {
        System.out.print("\nกรอกชื่อผู้ขอคืน: ");
        String name = sc.nextLine();
        System.out.print("กรอกรายละเอียดเหตุผลการคืน: ");
        String reason = sc.nextLine();

        String data = "ลูกค้า: " + name + " ขอคืนสินค้า เนื่องจาก: " + reason;
        db.addReturn(data);
        System.out.println("✅ บันทึกข้อมูลการคืนสินค้าเรียบร้อย");
    }
}
