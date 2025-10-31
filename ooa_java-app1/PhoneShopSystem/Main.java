package PhoneShopSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Database db = new Database();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== ระบบร้านขายโทรศัพท์ ===");
            System.out.println("1. เข้าระบบลูกค้า");
            System.out.println("2. เข้าระบบพนักงาน");
            System.out.println("3. ออกจากระบบ");
            System.out.print("เลือกเมนู: ");
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ กรุณาใส่ตัวเลขเท่านั้น");
                continue;
            }

            switch (choice) {
                case 1 -> new Member(db).showMenu();
                case 2 -> new Employee(db).showMenu();
                case 3 -> {
                    System.out.println("👋 ออกจากระบบแล้ว");
                    return;
                }
                default -> System.out.println("❌ กรุณาเลือกเมนูให้ถูกต้อง");
            }
        }
    }
}
