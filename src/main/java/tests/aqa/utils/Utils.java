package tests.aqa.utils;

import java.util.List;

public class Utils {
    public static boolean isSorted(List<Double> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1).compareTo(list.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }
}
