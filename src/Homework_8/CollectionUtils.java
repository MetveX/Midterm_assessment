package Homework_8;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class CollectionUtils {

    public static <T> List<T> getUniqueElements(ArrayList<T> list) {
        Set<T> uniqueSet = new LinkedHashSet<>(list);
        return new ArrayList<>(uniqueSet);
    }

}