package com.clawson.restful.easyChallenges;

import java.lang.System;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HelloWorld {
    public static void main(String[] args) {
        
        EasyChallenges ec = new EasyChallenges();
        int diff[] = ec.distinctDifferenceArray(new int[]{1,2,3,4,5});
        printArray(diff);
    }

    public static String binarySearch(int[] nums, int value) {  
        if (nums.length < 1) {
            return null;
        }

        int low = 0;
        int high = nums.length - 1;
        int mid = (low + high) / 2;

        System.out.println("Low: " + low + "\nMid: " + mid + "\nHigh: " + high);

        while (low <= high) {
            if (nums[mid] == value) {
                return "Found " + value + " at index " + mid;
            } else if (mid < value) {
                low = mid + 1;
                System.out.println("Too Low!");
            } else {
                high = mid - 1;
                System.out.println("Too High!");
            }
            mid = (low + high) / 2;
            System.out.println("Low: " + low + "\nMid: " + mid + "\nHigh: " + high);
        }

        return null;
    }

    /*
     * Selection Sort not in place is trivial
     */
    public static int[] inPlaceSelectionSort(int[] items) {
        if (items.length < 1)
            return null;

        int low;
        int idx;

        for (int i = 0; i < items.length; i++) {
            low = items[i];
            idx = i;
            for (int j = i + 1; j < items.length; j++) {
                if (low > items[j]) {
                    low = items[j];
                    idx = j;
                }
            }

            for (int k = idx; k > i; k--) {
                items[k] = items[k - 1];
            }
            items[i] = low;
        }

        return items;
    }

    /*
     * bubbleSort is just an easier way to do selection sort in place
     */
    public static int[] bubbleSort(int[] values) {
        if (values.length < 0)
            return null;

        int low;
        int idx;
        int tmp;

        for (int i = 0; i < values.length; i++) {
            low = values[i];
            idx = i;
            for (int j = i + 1; j < values.length; j++) {
                if (low > values[j]) {
                    low = values[j];
                    idx = j;
                }
            }

            tmp = values[i];
            values[i] = values[idx];
            values[idx] = tmp;
        }
        return values;
    }

    /*
     * 
     * [2 1] start 0 end 2
     * [2] start 0 end 1 [1] start 1 end 2
     * compare [2] [1]
     * [1 2]
     */

    /*
     * [8 7 6 5 4 3 2 1] start 0 end 8
     * [8 7 6 5] start 0 end 4 [4 3 2 1] start 4 end 8
     * [8 7] start 0 end 2 [6 5] start 2 end 4 [4 3] start 4 end 6 [2 1] start 6 end
     * 8
     * 8 7 6 5 4 3 2 1
     * [8] [7] [6] [5] [4] [3] [2] [1]
     */

    /*
     * [3 2 1] start 0 end 3 lsize: 1 rsize: 2
     * [3] start 0 end 1 [2 1] start 1 end 3 lsize: 1 rsize: 1
     * [3] start 0 end 1 [2] start 1 end 2 [1] start 2 end 3
     * [3] start 0 end 0 [2] start 1 end 1 [1] start 1 end 3
     */

    public static int[] mergeSort(int[] items, int start, int end, int[] lArr, int[] rArr, int[] sArr) {
        int mid = (start + end) / 2;
        int lSize = mid - start;
        int rSize = end - mid;

        if (end - start <= 0) {
            System.out.println("Stuck here" + " Left size: " + lSize + " Right size: " + rSize + "\n");
            return new int[] {};
        }

        if (end - start == 1) {
            System.out.println("Ready to start");
            return new int[] { items[start] };
        }

        System.out.println("Left size: " + lSize + "\nRight size: " + rSize + "\n");

        if (lSize >= 1) {
            lArr = mergeSort(items, start, mid, lArr, rArr, sArr);
        }

        if (rSize >= 1) {
            rArr = mergeSort(items, mid, end, lArr, rArr, sArr);
        }
        System.out.println("Left Array:");
        printArray(lArr);
        System.out.println("Right Array:");
        printArray(rArr);

        int lidx = 0;
        int ridx = 0;
        int size = lArr.length + rArr.length;
        // System.out.println("sArr size: " + size + "\n");
        int idx = 0;
        sArr = new int[size];

        while (lidx < lArr.length & ridx < rArr.length) {
            if (lArr[lidx] > rArr[ridx]) {
                sArr[idx] = rArr[ridx];
                ridx++;
            } else {
                sArr[idx] = lArr[lidx];
                lidx++;
            }
            idx++;
            // System.out.println("sArr: ");
            printArray(sArr);
            // System.out.println("idx: " + idx + "\nridx: " + ridx + "\nlidx: " + lidx);
        }

        // System.out.println("Outside main merge\nidx: " + idx + "\nridx: " + ridx +
        // "\nlidx: " + lidx);

        if (lidx < lArr.length) {
            for (int i = lidx; i < lArr.length; i++) {
                sArr[idx] = lArr[i];
                idx++;
            }
        } else {
            for (int i = ridx; i < rArr.length; i++) {
                sArr[idx] = rArr[i];
                idx++;
            }
        }
        // System.out.println("merge step: ");
        // printArray(sArr);
        return sArr;

    }

    public static int[] quickSort(int[] items, int low, int high) {
        if (high - low <= 1) {
            System.out.println("End of division");
            printArray(items);
            return items;
        }
        int mid = (low + high) / 2;
        int pivot = items[mid];
        int[] lArr = new int[high];
        int lIdx = 0;
        int[] rArr = new int[high];
        int rIdx = 0;
        System.out.println("pre-division");
        printArray(items);
        for (int i = low; i < high; i++) {
            if (items[i] < items[mid]) {
                lArr[lIdx] = items[i];
                lIdx++;
            }
        }

        int temp[] = new int[lIdx];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = lArr[i];
        }
        lArr = temp;

        System.out.println("Left Array: ");
        printArray(lArr);
        for (int i = low; i < high; i++) {
            if (i == mid) {
                i++;
                if (i >= high)
                    break;
            }

            if (items[i] >= items[mid]) {
                rArr[rIdx] = items[i];
                rIdx++;
            }
        }

        temp = new int[rIdx];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = rArr[i];
        }
        rArr = temp;

        System.out.println("Right Array: ");
        printArray(rArr);
        System.out.println("Pivot: " + pivot + "\n");
        lArr = quickSort(lArr, low, lIdx);
        rArr = quickSort(rArr, low, rIdx);

        System.out.println("Combining:");
        System.out.println("Left Array:");
        printArray(lArr);

        System.out.println("Right Array:");
        printArray(rArr);

        int[] sArr = new int[lIdx + rIdx + 1];
        for (int i = 0; i < lIdx; i++) {
            sArr[i] = lArr[i];
        }
        sArr[lIdx] = pivot;
        int counter = 0;
        for (int i = lIdx + 1; i < sArr.length; i++) {
            sArr[i] = rArr[counter];
            counter++;
        }
        System.out.println("Sorted Array: ");
        printArray(sArr);
        return sArr;

    }

    public static int sumArr(int[] arr, int idx) {
        if (idx > arr.length - 1) {
            return 0;
        }
        if (idx == arr.length - 1) { // idx<=0 return
            return arr[idx];
        }

        return arr[idx] + sumArr(arr, ++idx);
    }

    public static int countItems(int[] arr, int idx) {
        if (idx > arr.length - 1) {
            return 0;
        }
        if (idx == arr.length - 1) {
            return ++idx;
        }
        return countItems(arr, ++idx);
    }

    public static int maxNumber(int[] arr, int idx, int max) {
        if (arr.length == 0 | idx > arr.length - 1)
            return 0;
        if (idx == arr.length - 1) {
            return Math.max(max, arr[idx]);
        }
        return maxNumber(arr, idx + 1, Math.max(max, arr[idx]));
    }

    public static String rBinarySearch(int[] arr, int low, int high, int value) {
        int mid = (low + high) / 2;
        if (arr.length == 0)
            return "Value can't be found in an empty array";
        if (low > high & arr[mid] != value)
            return "Value of: " + value + " not found";
        if (arr[mid] == value) {
            return "Value Found!\n Value: " + value + " found at index: " + mid;
        }
        if (value > arr[mid]) {
            low = mid + 1;
        }
        if (value < arr[mid]) {
            high = mid - 1;
        }
        return rBinarySearch(arr, low, high, value);
    }

    public static void printArray(int[] values) {
        for (int i : values) {
            System.out.println(i);
        }
        System.out.println("\n");
    }
}