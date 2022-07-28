package main.java.service;

import java.util.ArrayList;

public class Service {
    public static boolean isAllDataNumericInteger(ArrayList<Object> arrayList){

        for (int i = 0 ; i < arrayList.size(); i++) {
            try {
                Integer.parseInt((String) arrayList.get(i));
            } catch (NumberFormatException nfe) {
                return false;
            } catch (NullPointerException nullPointerException) {
                return false;
            }
        }

        return true;
    }
}
