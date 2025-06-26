import java.util.*;

public class TypeConversionDemo {
    public static void main(String[] args) {
        // 1. 基本类型转换
        int num = 100;
        double d = num; // 自动转换
        System.out.println("int转double: " + d);
        
        // 2. 字符串与数值转换
        String str = "123";
        int n = Integer.parseInt(str);
        System.out.println("字符串转int: " + n);
        
        // 3. 集合与数组转换
        String[] array = {"A", "B", "C"};
        List<String> list = Arrays.asList(array);
        System.out.println("数组转List: " + list);
        
        // 4. 日期与字符串转换
        String dateStr = "2023-01-01";
        // LocalDate date = LocalDate.parse(dateStr); // 需要Java 8+
        System.out.println("字符串转日期: " + dateStr);
    }
}
