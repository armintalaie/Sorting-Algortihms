import java.util.*;

public class QuickSort {

    // options to choose a pivot for QuickSort
    enum Pivot {FIRST, MIDDLE, LAST, MEDIAN, RANDOM}

    // method to execute program
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // creates empty array of desired length
        System.out.println("Enter array's length");
        int length = Integer.parseInt(scanner.nextLine());
        int[] unsortedArray = new int[length];

        System.out.println("What should be the pivot? if nothing is entered it's random");
        System.out.println("Options: FIRST - MIDDLE - LAST - MEDIAN - RANDOM");
        Pivot piv = Pivot.RANDOM;

        // get array as input
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                String input = scanner.nextLine();
                switch (input) {
                    case "FIRST":
                        piv = Pivot.FIRST;
                        break;
                    case "MIDDLE":
                        piv = Pivot.MIDDLE;
                        break;
                    case "LAST":
                        piv = Pivot.LAST;
                        break;
                    case "MEDIAN":
                        piv = Pivot.MEDIAN;
                        break;
                    case "RANDOM":
                        break;
                    default:
                        unsortedArray[i] = Integer.parseInt(input);
                        continue;
                }
            }
            unsortedArray[i] = scanner.nextInt();
        }

        partition(unsortedArray, 0, length, piv);
        System.out.println(Arrays.toString(unsortedArray));
    }


    // sorts the array by partitioning based on the pivot
    // into two arrays of smaller and greater than pivot
    public static void partition(int[] array, int start, int end, Pivot type) {


        // returns if array has length < 2, and can't be partitioned
        if (start >= end - 1) {
            return;
        }
        // chooses pivotIndex and element based on type of pivot chosen
        int pIndex = findPivot(array, start, end, type);


        // swaps the pivot and start Element
        swap(array, pIndex, start);
        int piv = array[start];
        int less = start + 1;

        // separates less than pivot and greater than pivot
        for (int i = less; i < array.length; i++) {

            if (array[i] < piv) {
                swap(array, less, i);
                less++;
            }
        }

        // swaps the pivot (which is at index start) with the last element smaller than pivot
        swap(array, start, less - 1);

        // partition the two halves (less and greater than the pivot)
        partition(array, start, less - 1, type);
        partition(array, less, end, type);
    }


    // return the index the pivot is in the array based on the pivot type
    private static int findPivot(int[] array, int start, int end, Pivot type) {

        int last = end - 1;
        int middle = (last - start) / 2 + start;
        int median = findMedian(array, start, middle, last);

        switch (type) {
            case FIRST:
                return start;
            case MIDDLE:
                return middle;
            case LAST:
                return last;
            case MEDIAN:
                return median;
            default:
                Random random = new Random();
                return random.nextInt(end - start) + start;
        }


    }

    // determine median of elements in the beginning,end and middle of array
    private static int findMedian(int[] array, int first, int middle, int last) {

        if ((array[first] <= array[middle] && array[middle] < array[last]) ||
                (array[first] >= array[middle] && array[middle] > array[last]))
            return middle;
        else if ((array[middle] <= array[first] && array[first] < array[last]) ||
                (array[middle] >= array[first] && array[first] > array[last])) {
            return first;
        } else {
            return last;
        }

    }

    // swaps two elements in array
    private static void swap(int[] array, int index1, int index2) {

        if (index1 == index2)
            return;

        array[index1] += array[index2];
        array[index2] = array[index1] - array[index2];
        array[index1] -= array[index2];

    }
}
