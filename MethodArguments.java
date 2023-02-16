public class MethodArguments{
    public void methodArguments(String fname, int age){
        System.out.println("Name is :"+ fname +"Age is :"+ age);
    }
    public static void main(String[] Args){
        MethodArguments obj = new MethodArguments();
        obj.methodArguments("Sreekar", 25);
        obj.methodArguments("Praveen", 25);
        obj.methodArguments("Sai", 28);
    }
}