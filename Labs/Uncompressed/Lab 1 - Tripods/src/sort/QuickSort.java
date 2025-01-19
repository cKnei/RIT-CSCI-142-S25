package sort;

import tripods.Direction;
import tripods.Tripod;

import java.util.ArrayList;

/**
 * Perform an "out of place" quick sort on an array list of Tripod's by
 * ascending tripod sum.
 * <pre>
 * quick_sort (not in place):
 * best=O(n * log n)
 * worst=O(n^2)
 * </pre>
 * 
 * @author RIT CS
 * @author Knei
 */
public class QuickSort {
    /**
     * Partition the array for values less than the pivot.
     * 
     * @param data  the full array of data
     * @param pivot the pivot
     * @return data less than the pivot
     */
    public static ArrayList<Tripod> partitionLess(ArrayList<Tripod> data, Tripod pivot) {
        ArrayList<Tripod> lessThanPivot = new ArrayList<>();

        for (Tripod item : data) {
            if (item.sum() < pivot.sum()) {
            lessThanPivot.add(item);
            }
        }

        return lessThanPivot;
    }

    /**
     * Partition the array for values equal to the pivot.
     * 
     * @param data  the full array of data
     * @param pivot the pivot
     * @return data equal to the pivot
     */
    public static ArrayList<Tripod> partitionEqual(ArrayList<Tripod> data, Tripod pivot) {
        ArrayList<Tripod> equalToPivot = new ArrayList<>();

        for (Tripod item : data) {
            if (item.sum() == pivot.sum()) {
                equalToPivot.add(item);
            }
        }

        return equalToPivot;
    }

    /**
     * Partition the array for values greater than the pivot.
     * 
     * @param data  the full array of data
     * @param pivot the pivot
     * @return data greater than  the pivot
     */
    public static ArrayList<Tripod> partitionGreater(ArrayList<Tripod> data, Tripod pivot) {
        ArrayList<Tripod> greaterThanPivot = new ArrayList<>();

        for (Tripod item : data) {
            if (item.sum() > pivot.sum()) {
                greaterThanPivot.add(item);
            }
        }

        return greaterThanPivot;
    }

    /**
     * Performs a quick sort and returns a newly sorted array.
     * 
     * @param data the data to be sorted
     * @return a sorted array
     */
    public static ArrayList<Tripod> quickSort(ArrayList<Tripod> data) {
        if (data.isEmpty()) {
            return new ArrayList<>(0);
        }

        Tripod pivot = data.getFirst();

        ArrayList<Tripod> less = partitionLess(data, pivot);
        ArrayList<Tripod> equal = partitionEqual(data, pivot);
        ArrayList<Tripod> greater = partitionGreater(data, pivot);

        ArrayList<Tripod> joined = new ArrayList<>();

        joined.addAll(quickSort(less));
        joined.addAll(equal);
        joined.addAll(quickSort(greater));

        return joined;
    }
}
