package PhoneShopSystem;

import java.util.ArrayList;

public class Database {
    // F1: ข้อมูลการซื้อโทรศัพท์
    public ArrayList<String> purchases = new ArrayList<>();

    // F2: ข้อมูลการแจ้งซ่อม
    public ArrayList<String> repairs = new ArrayList<>();

    // F7: ข้อมูลการคืนสินค้า
    public ArrayList<String> returns = new ArrayList<>();

    // แจ้งเตือนต่าง ๆ
    public ArrayList<String> notifications = new ArrayList<>();

    public void addPurchase(String data) {
        purchases.add(data);
        notifications.add("📦 มีการสั่งซื้อใหม่: " + data);
    }

    public void addRepair(String data) {
        repairs.add(data);
        notifications.add("🔧 มีการแจ้งซ่อมใหม่: " + data);
    }

    public void addReturn(String data) {
        returns.add(data);
        notifications.add("↩️ มีการขอคืนสินค้าใหม่: " + data);
    }
}
