package test.java;

public class TestGC {
    private int iTest;

    TestGC(){
        iTest = 10;
    }

    @Override
    protected void finalize() throws Throwable
    {
        System.out.println("Garbage collector in action! Deleted TestGC object");
    }
}
