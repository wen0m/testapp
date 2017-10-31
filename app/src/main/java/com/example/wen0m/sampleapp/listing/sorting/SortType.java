package com.example.wen0m.sampleapp.listing.sorting;


import com.example.wen0m.sampleapp.models.Building;
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


    private static class ApartCntComparator implements Comparator<Building> {
        @Override
        public int compare(Building o1, Building o2) {
            return Integer.compare(o1.getApart_count(), o2.getApart_count());
        }
    }

    private static class PriceComparator implements Comparator<Building> {
        @Override
        public int compare(Building o1, Building o2) {
            return Integer.compare(o1.getMin_prices().get(0).getPrice(), o2.getMin_prices().get(0).getPrice());
        }
    }

    private static class NameComparator implements Comparator<Building> {
        @Override
        public int compare(Building o1, Building o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }
}
