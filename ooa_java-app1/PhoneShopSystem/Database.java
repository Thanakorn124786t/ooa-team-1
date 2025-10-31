package PhoneShopSystem;

import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    public ArrayList<Purchase> purchases;
    public ArrayList<Repair> repairs;
    public ArrayList<String> returns;

    public Database() {
        purchases = new ArrayList<>();
        repairs = new ArrayList<>();
        returns = new ArrayList<>();
    }

    // 🛒 เพิ่มการซื้อสินค้า
    public void addPurchase(String customerName, String product, String payment, double unitPrice, int qty,
            String date) {
        String id = String.format("S%03d", purchases.size() + 1);
        double total = unitPrice * qty;
        purchases.add(new Purchase(id, customerName, product, payment, unitPrice, qty, total, date));
    }

    // 🔧 เพิ่มรายการซ่อม
    public void addRepair(String customerName, String symptom, String model, String brand, String imei, String color,
            String phone, String detail, double cost, String date) {
        String id = String.format("R%03d", repairs.size() + 1);
        repairs.add(new Repair(id, customerName, symptom, model, brand, imei, color, phone, detail, cost, date));
    }

    // ↩️ เพิ่มการคืนสินค้า
    public void addReturn(String data) {
        returns.add(data);
    }

    // 🏆 ฟังก์ชันช่วย: สินค้าขายดีที่สุด
    public String getBestSellingProduct() {
        if (purchases.isEmpty())
            return null;
        HashMap<String, Integer> countMap = new HashMap<>();
        for (Purchase p : purchases) {
            countMap.put(p.product, countMap.getOrDefault(p.product, 0) + p.qty);
        }
        String best = null;
        int max = 0;
        for (String key : countMap.keySet()) {
            if (countMap.get(key) > max) {
                max = countMap.get(key);
                best = key;
            }
        }
        return best;
    }

    public int getProductCount(String product) {
        int sum = 0;
        for (Purchase p : purchases) {
            if (p.product.equals(product))
                sum += p.qty;
        }
        return sum;
    }

    public double getTotalSalesByProduct(String product) {
        double sum = 0;
        for (Purchase p : purchases) {
            if (p.product.equals(product))
                sum += p.total;
        }
        return sum;
    }

    // 🏆 ฟังก์ชันช่วย: รุ่นที่ซ่อมบ่อยที่สุด
    public String getMostCommonRepairModel() {
        if (repairs.isEmpty())
            return null;
        HashMap<String, Integer> countMap = new HashMap<>();
        for (Repair r : repairs) {
            countMap.put(r.model, countMap.getOrDefault(r.model, 0) + 1);
        }
        String best = null;
        int max = 0;
        for (String key : countMap.keySet()) {
            if (countMap.get(key) > max) {
                max = countMap.get(key);
                best = key;
            }
        }
        return best;
    }

    public int getRepairCountByModel(String model) {
        int sum = 0;
        for (Repair r : repairs) {
            if (r.model.equals(model))
                sum++;
        }
        return sum;
    }

    // 📦 Class ย่อยเก็บข้อมูลการซื้อ
    public static class Purchase {
        public String id;
        public String customerName;
        public String product;
        public String payment;
        public double unitPrice;
        public int qty;
        public double total;
        public String date;

        public Purchase(String id, String customerName, String product, String payment, double unitPrice, int qty,
                double total, String date) {
            this.id = id;
            this.customerName = customerName;
            this.product = product;
            this.payment = payment;
            this.unitPrice = unitPrice;
            this.qty = qty;
            this.total = total;
            this.date = date;
        }

        @Override
        public String toString() {
            return customerName + " | สินค้า: " + product + " | จำนวน: " + qty + " | รวม: "
                    + String.format("%,.2f", total) + " บาท | วิธีชำระ: " + payment + " | วันที่: " + date;
        }
    }

    // 🔧 Class ย่อยเก็บข้อมูลการซ่อม
    public static class Repair {
        public String id;
        public String customerName;
        public String symptom;
        public String model;
        public String brand;
        public String imei;
        public String color;
        public String phone;
        public String detail;
        public double cost;
        public String date;

        public Repair(String id, String customerName, String symptom, String model, String brand, String imei,
                String color, String phone, String detail, double cost, String date) {
            this.id = id;
            this.customerName = customerName;
            this.symptom = symptom;
            this.model = model;
            this.brand = brand;
            this.imei = imei;
            this.color = color;
            this.phone = phone;
            this.detail = detail;
            this.cost = cost;
            this.date = date;
        }

        @Override
        public String toString() {
            return customerName + " | รุ่น: " + model + " | ยี่ห้อ: " + brand + " | อาการ: " + symptom
                    + " | ค่าใช้จ่าย: " + String.format("%,.2f", cost) + " บาท | วันที่: " + date;
        }
    }
}
