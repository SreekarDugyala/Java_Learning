package com.example.demo.zjavaUnderstandings;

class Example {
    private Integer number;

    public void display(Example obj) {
        System.out.println("Number is: " + obj.number);
    }

    public void show() {
        this.number = 10;
        display(this);  // Passes the current instance to the display method
    }

    public void methodA() {
        System.out.println("Inside methodA");
        this.methodB();  // Calls methodB from the same instance
    }

    public void methodB() {
        System.out.println("Inside methodB");
    }

    public Example setNumber(int number) {
        this.number = number;
        return this;  // Returns the current instance
    }

    public static void main(String[] args) {
        Example example = new Example();
        example.show();
        example.methodA();
        Example e = example.setNumber(5);
        System.out.println(e.number);
    }
}
