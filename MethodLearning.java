public class MethodLearning {
    public void method1(){
        System.out.println("Hello All I am a message from method1");
    }

    public String method2(int cost){
        if(cost>= 10)
            return "Pen";
        //else
        return "Nothing";               //execution gets out of function when return is executed.
    }
}
