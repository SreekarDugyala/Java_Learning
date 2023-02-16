public class Demo {
    public static void main(String[] args) {
         Calculator calc = new Calculator();
         double h = calc.add(4, 5);
         System.out.println("the value of addition is "+ h);

         MethodLearning obj = new MethodLearning();
         obj.method1();
         String a = obj.method2(12);
         System.out.println(a);

        MethodOverloading obj1 = new MethodOverloading();
        System.out.println("The output is "+obj1.add(5, 4, 8));
        System.out.println("The output is "+obj1.add(5, 4));
    }
}