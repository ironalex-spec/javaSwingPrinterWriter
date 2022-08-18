package lib.app;

import sun.applet.Main;
public class BuildVersion {
    public static String getBuildVersion(){
        return Main.class.getPackage().getImplementationVersion();
    }

}
