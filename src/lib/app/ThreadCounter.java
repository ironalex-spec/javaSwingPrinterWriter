package lib.app;

public class ThreadCounter {
    private static int countThread = 0;

    public static void setCountThread(int count){
        countThread = count;
    }

    public static void decCountThread(){
        if(countThread > 0){
            countThread--;
        } else{
            countThread=0;
        }
    }

    public static boolean isCountFinish(){
        return countThread==0;
    }

}
