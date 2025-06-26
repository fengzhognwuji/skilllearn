public class Main {
    public static void main(String[] args) {
        // Basic variable declarations
        int number = 10;
        double decimal = 5.5;
        boolean flag = true;
        String text = "Hello Java!";
        
        // Print statements
        System.out.println("Basic Java Demo");
        System.out.println("Number: " + number);
        System.out.println("Decimal: " + decimal);
        System.out.println("Boolean: " + flag);
        System.out.println("Text: " + text);
        
        // Conditional statement
        if (number > 5) {
            System.out.println("Number is greater than 5");
        } else {
            System.out.println("Number is 5 or less");
        }
        
        // Loop example
        System.out.println("Counting from 1 to 5:");
        for (int i = 1; i <= 5; i++) {
            System.out.println(i);
        }
        
        // Method call
        int sum = addNumbers(3, 4);
        System.out.println("Sum of 3 and 4: " + sum);
    }
    
    // Method definition
    public static int addNumbers(int a, int b) {
        return a + b;
    }
}
