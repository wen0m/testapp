package com.example.wen0m.sampleapp.shared;


import com.example.wen0m.sampleapp.mvp.models.Fighter;
import java.util.Comparator;


public class SortType {

    public static Comparator getComparator(int sortingOption) {
        Comparator comparator = null;
        switch (sortingOption) {
            case 0: comparator = new PriceComparator();
                break;
            case 1: comparator = new ApartCntComparator();
                break;
            case 3: comparator = new NameComparator();
                break;
        }
        return comparator;
    }


    private static class ApartCntComparator implements Comparator<Fighter> {
        @Override
        public int compare(Fighter o1, Fighter o2) {
            return Integer.compare(o1.getHeight(), o2.getHeight());
        }
    }

    private static class PriceComparator implements Comparator<Fighter> {
        @Override
        public int compare(Fighter o1, Fighter o2) {
            return Integer.compare(o1.getWeight(), o2.getWeight());
        }
    }

    private static class NameComparator implements Comparator<Fighter> {
        @Override
        public int compare(Fighter o1, Fighter o2) {
            return o1.getLast_name().compareTo(o2.getLast_name());
        }
    }
}
