package com.berk.dragons.utils; // <--- НОВЫЙ ПАКЕТ

import com.berk.dragons.model.DragonBase; // <--- НУЖЕН ИМПОРТ
import java.util.List;

public class SortingUtils {
    public static void sortByName(List<DragonBase> list) {
        list.sort((d1, d2) -> d1.getName().compareToIgnoreCase(d2.getName()));
    }
}