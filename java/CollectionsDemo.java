import java.util.*;

public class CollectionsDemo {
    public static void main(String[] args) {
        // ArrayList示例
        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Python");
        list.add("C++");
        System.out.println("ArrayList: " + list);

        // HashMap示例
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Apple");
        map.put(2, "Banana");
        System.out.println("HashMap: " + map);

        // Collections工具类
        Collections.sort(list);
        System.out.println("排序后: " + list);

        // 集合遍历
        System.out.println("遍历ArrayList:");
        for(String item : list) {
            System.out.println(item);
        }
    }
}
