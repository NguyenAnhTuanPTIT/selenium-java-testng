package javaSDeT;

import java.util.*;

public class Topic_09_List {
    public static void main(String[] args) {
        // --------------------------------------- JAVA COLLECTION -------------------------------------
        // List : không quan tâm trùng lặp
        // Set : quan tâm đến sự trùng lặp
        // Queue : Hàng đợi
        ArrayList<String> address = new ArrayList<>();
        Vector<Float> studentPoint = new Vector<>();
        LinkedList<Integer> studentAge = new LinkedList<>();
        Stack<Boolean> status = new Stack<>();

        // Khai báo kiểu List nhưng có thể new ArrayList => Do ArrayList kế thừa List
        List<String> name = new ArrayList<>();
        // Add phần tử vào List
        name.add(("Ho Chi Minh");
        name.add("Da Nang");
        name.add("Hue");
        name.add("Hai Phong");
        // Lấy phần tử ra
        name.get(0); //truyền index của phần tử đó vào hàm get()
        // Lấy toàn bộ
        for (String a : name) {
            System.out.println(a);
        }

        List<Integer> age = new LinkedList<>();


    }


}
