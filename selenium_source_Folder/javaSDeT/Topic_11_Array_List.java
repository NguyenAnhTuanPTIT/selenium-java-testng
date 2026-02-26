package javaSDeT;

import org.openqa.selenium.WebDriver;

import java.util.*;

public class Topic_11_Array_List {
    public static void main(String[] args) {
        List<String> animals  = new ArrayList<>();
        animals.add("Dog");
        animals.add("Cat");

        System.out.println(animals);

        //Add vào vị trí thứ nhất trong mảng
        animals.add(0,"Pig");

        System.out.println(animals);

        List<String> pets  = new ArrayList<>();
        pets.add("Horse");
        pets.add("Bird");

        // Thêm một mảng mới vào mảng cũ
        // Thêm vào vị trí của cùng của List
        animals.addAll(pets);

        System.out.println(animals);

        // Get ra phần tử trong List
        System.out.println(animals.get(3));

        // Dùng Iterator lấy ra thứ tự phần tử trong List
        Iterator<String> iterator = animals.iterator();
        while (iterator.hasNext()){ // Kiểm tra trong List có phần tử tiếp theo không
            // In ra phần  tử trong List
            System.out.println(iterator.next());
        }

        // Thay thế giá trị 1  ptu trong List
        animals.set(1,"Mouse");
        System.out.println(animals);

        // Remove phần tử - Dùng index
        animals.remove(1);
        System.out.println(animals);

        // Remove phần tử - Dùng object (giá trị)
        animals.remove("Horse");
        System.out.println(animals);

        // Xóa hết toàn bộ phần tử - Dùng remove
        animals.removeAll(animals);
        System.out.println(animals);

        animals.add("Dog");
        animals.add("Cat");
        System.out.println(animals);

        // Xóa hết toàn bộ phần tử - Dùng clear
        // Dùng clear nhanh hơn do dùng removeAll. Do removeAll xóa theo phần tử
        animals.clear();
        System.out.println(animals);

        // Trả về độ dài (kích thước) của List
        System.out.println(animals.size());

        animals.add("Dog");
        animals.add("Cat");
        animals.add("Elephant");

        // Convert từ ArrayList thành một mảng
        // Khai báo 1 mảng trước, có số phần tử bằng số ptu ở ArrayList
        String[] animalArray = new String[animals.size()];
        // Dùng hàm toArray để convert
        animals.toArray(animalArray);
        for(String  arr_animal : animalArray){
            System.out.println(arr_animal);
        }

        // Convert từ Array sang ArrayList
        List<String> ann  = new ArrayList<String>(Arrays.asList(animalArray));
        System.out.println("List sau khi convert từ Array: " + ann);

        // Biến ArrayList thành 1 chuỗi
        System.out.println("ArrayList sau khi biến thành chuỗi: " + ann.toString());

        // Kiếm tra trong ArrayList có chứa giá trị mong muốn hay không
        System.out.println(ann.contains("Dog"));
        System.out.println(ann.contains("Pig"));

        // Kiểm tra ở arrayList hiện  tại có chứa các giá trị ở ArrayList khác không
        System.out.println(ann.contains(animals));

        // Kiểm tra arrayList còn chứa phần tử không
        System.out.println(ann.isEmpty());

        // Dò ra index của phần từ khi biết giá trị của p.tử đó
        // Nếu dò ra thì trả vè index tương  ứng
        // Còn không thì trả ra số âm
        String animalSearch = "Elephant";
        System.out.println("Index của phần từ có giá trị " + animalSearch +
                "là : " + animals.indexOf("Elephant"));

        // Dùng for truyền thống để duyệt  arrayList
        System.out.println("Các p.tử sau khi duyệt bằng for truyền thống");
        for (int i = 0; i < ann.size() ; i++) {
            System.out.println(ann.get(i));
        }

        // Dùng while
        int value = 0;
        while (ann.size() > value){
            System.out.println(ann.get(value));
            value++;
        }

        // Dùng biểu thức của  java 8 để duyệt qua p.tử của arrayList
        System.out.println("Các p.tử sau khi duyệt bằng lambda");
        ann.forEach(animal -> System.out.println(animal));

        // Dùng Enumeration Interface để duyệt qua p.tử của arrayList
        System.out.println("Các p.tử sau khi duyệt bằng Enumeration Interface");
        Enumeration<String> e = Collections.enumeration(ann);
        while(e.hasMoreElements()){
            System.out.println(e.nextElement());
        }

        ann.add("Turtle");
        ann.add("Snake");
        ann.add("Dolphin");

        // Sắp xếp lại các phần tử - theo ascending
        Collections.sort(ann);
        System.out.println("Các phần tử sau khi sort bằng Collections: " + ann);

        // Revert lại thứ tự (Đảo ngược các phần tử)
        Collections.reverse(ann);
        System.out.println("Các phần tử sau khi revert bằng Collections: " + ann);

        // Dùng chính arrayList để sắp xếp lại
        ann.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println("Các phần tử sau khi sort bằng ArrayList: " + ann);

        ann.add("Ant");
        ann.add("Big Foot");
        ann.add("Chicken");

        //  Dùng b.thức lambda của java 8
        ann.sort((name1, name2)  ->  name1.compareTo(name2));
        ann.sort(Comparator.naturalOrder());
        System.out.println("Các phần tử sau khi sort bằng Lambda: "  + ann);

    }
}
