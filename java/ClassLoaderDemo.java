public class ClassLoaderDemo {
    public static void main(String[] args) {
        // 1. 显示类加载器层次结构
        ClassLoader loader = ClassLoaderDemo.class.getClassLoader();
        System.out.println("当前类加载器: " + loader);
        System.out.println("父类加载器: " + loader.getParent());
        System.out.println("祖父类加载器: " + loader.getParent().getParent());

        // 2. 类加载过程演示
        System.out.println("\n类加载过程:");
        try {
            Class<?> clazz = loader.loadClass("java.util.ArrayList");
            System.out.println("成功加载: " + clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 3. 自定义类加载器示例
        System.out.println("\n自定义类加载器示例:");
        ClassLoader customLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                System.out.println("尝试加载: " + name);
                return super.loadClass(name);
            }
        };
        
        try {
            customLoader.loadClass("java.lang.String");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
