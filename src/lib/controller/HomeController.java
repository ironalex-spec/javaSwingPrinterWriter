package lib.controller;

public class HomeController {
    private static HomeController single_instance = null;
    // Declaring a variable of type String

    public String s;

    // Constructor of this class
    // Here private constructor is used to
    // restricted to this class itself
    private HomeController()
    {
        s = "Hello I am a string part of Singleton class";
        System.out.println(s);
    }

    // Method
    // Static method to create instance of Singleton class
    public static HomeController getInstance()
    {
        // To ensure only one instance is created
        if (single_instance == null) {
            single_instance = new HomeController();
        }
        return single_instance;
    }
}
