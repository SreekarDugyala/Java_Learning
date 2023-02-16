public class Classes {
    int x= 5;

    public static void main(String[] args) {
        Classes obj1 = new Classes();
        Classes obj2 = new Classes();
        obj2.x = 4;
        System.out.println(obj1.x);
        System.out.println(obj2.x);
    }
}
