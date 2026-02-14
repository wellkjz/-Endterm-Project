package com.berk.dragons.utils;

import com.berk.dragons.model.DragonBase;
import java.util.List;

public class SortingUtils {
    public static void sortByName(List<DragonBase> list) {
        list.sort((d1, d2) -> d1.getName().compareToIgnoreCase(d2.getName()));
    }
}