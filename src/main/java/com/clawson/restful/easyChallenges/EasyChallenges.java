package com.clawson.restful.easyChallenges;

import java.util.*;

public class EasyChallenges {
    public boolean canConstructRansom(String ransomNote, String magazine) {
        HashMap<Integer, Character> ransom = new HashMap<>();
        boolean noPass = false;
        char ridx = ' ';
        char midx = ' ';
        for (int i = 0; i < ransomNote.length(); i++) {

            noPass = false;
            for (int j = 0; j < magazine.length(); j++) {
                ridx = ransomNote.charAt(i);
                midx = magazine.charAt(j);
                if (ridx == midx) {
                    if (!ransom.containsKey(j)) {
                        ransom.put(j, midx);
                        System.out.println("ridx: " + ridx + "\nmidx: " + midx + " at jidx: " + j);
                        noPass = true;
                        break;
                    } // else if(!(ransom.get(midx)==j)){
                      // ransom.put(midx,j);
                      // System.out.println("Contains Key\nridx: " + ridx + "\nmidx: " + midx + " at
                      // jidx: " + j+"\n");
                      // noPass=true;
                      // break;
                      // }
                }
            }
            if (!noPass)
                break;
        }
        return noPass;
    }

    public static List<List<Integer>> pascalsTriangle(int numRows) {
        List<List<Integer>> rows = new ArrayList<List<Integer>>();
        return mergeTriangle(numRows, rows);
    }

    public static List<List<Integer>> mergeTriangle(int numRows, List<List<Integer>> rows) {
        if (numRows < 0)
            return rows;
        if (numRows == 1) {
            rows.add(new ArrayList<Integer>(Arrays.asList(1)));
            return rows;
        }
        mergeTriangle(--numRows, rows);
        // add the number on both sides together until you get to the middle.
        if (numRows == 2) {
            rows.add(new ArrayList<Integer>(Arrays.asList(1, 1)));
            return rows;
        }
        ArrayList<Integer> newRow = new ArrayList<>(numRows);
        int first = 0;
        int last = numRows - 1;
        // newRow.add(rows.) //get the last list that was added and add the first
        // element to the new list.
        // add the next element in the list to the previous until first>=last. Add the
        // last element. Add the new row and return it.
        return null;
    }

    public static List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        List<List<Integer>> distinctSums = new ArrayList<List<Integer>>();
        ArrayList<Integer> ans1 = new ArrayList<>();
        ArrayList<Integer> ans2 = new ArrayList<>();

        HashMap<Integer, Integer> one = new HashMap<>();
        HashMap<Integer, Integer> two = new HashMap<>();

        for (int i = 0; i < nums1.length; i++) {
            one.put(nums1[i], one.getOrDefault(nums1[i], 0) + 1);
        }
        for (int i = 0; i < nums2.length; i++) {
            two.put(nums2[i], two.getOrDefault(nums2[i], 0) + 1);
        }

        // HashSet<Integer> oneKeys = one.keySet();
        // HashSet<Integer> twoKeys = two.keySet();

        for (int i = 0; i < nums1.length; i++) {
            if (null == two.get(nums1[i]) && !ans1.contains(nums1[i]))
                ans1.add(nums1[i]);
        }

        for (int i = 0; i < nums2.length; i++) {
            if (null == one.get(nums2[i]) && !ans2.contains(nums2[i]))
                ans2.add(nums2[i]);
        }
        distinctSums.add(ans1);
        distinctSums.add(ans2);

        return distinctSums;
    }

    public static int arithmeticTriplets(int[] nums, int diff) {

        // Brute Force O(n^3) solution
        // int count = 0;

        // for (int i = 0; i < nums.length - 2; i++) {
        //     for (int j = i + 1; j < nums.length - 1; j++) {
        //         if (nums[j] - nums[i] > diff)
        //             break;
        //         if (nums[j] - nums[i] < diff)
        //             continue;
        //         for (int k = j + 1; k < nums.length; k++) {
        //             if (nums[k] - nums[j] > diff)
        //                 break;
        //             if (nums[k] - nums[j] < diff)
        //                 continue;
        //             count++;
        //         }
        //     }
        // }
        // return count;

        // O(n^2) solution using hashmap
        int tripletCount = 0;
        int count = 0;

        HashMap<Integer, int[]> triplets = new HashMap<>();
        for (int i = 0; i < nums.length - 2; i++) {
            triplets.put(nums[i], new int[] { nums[i] + diff, nums[i] + (2 * diff) });
            tripletCount = 0;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == triplets.get(nums[i])[0] && tripletCount < 1) {
                    tripletCount++;
                } else if (nums[j] == triplets.get(nums[i])[1] && tripletCount >= 1) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        int llen=0;
        int lidx=0;
        int rlen=0;
        int ridx=0;

        while(llen<word1.length){
            if(word1[llen].charAt(lidx)!=word2[rlen].charAt(ridx)){
                return false;
            }
            if(lidx+1<word1[llen].length()){
                lidx++;
            }
            else if(llen+1<word1.length){
                llen++;
                lidx=0;
            }
            
            if(ridx+1<word2[rlen].length()){
                ridx++;
            }
            else if(rlen+1<word2.length){
                rlen++;
                ridx=0;
            }
        }

        return true;
    }

    public String truncateSentence(String s, int k) {
        int indx=s.length();

        for(int i=0; i<s.length(); i++){
            if(s.charAt(i)==' ') k--;
            if(k==0){
                indx=i;
                break;
            }
        }
        return s.substring(0,indx);
    }

    public int countPoints(String rings) {
        //HashMap<Integer,int[]> map = new HashMap<>();
        int[][] rods = new int[10][4];
        HashMap<Character, Integer> mapping = new HashMap<>();
        mapping.put('R',0);
        mapping.put('G',1);
        mapping.put('B',2);
        int count=0;

        // for(int i=0; i<rings.length()/2;i++){
        //     String key = rings.charAt(i*2+1);
        //     String value = 
        //     // if(map.get(i*2+1)==null){
        //     //     map.put(i,new int[]{0,0,0});
        //     // }
        //     // else if(rings.charAt(i*2)=='R'){
        //     //     map.get(i*2+1)[0]++;
        //     // }
        //     // else if(rings.charAt(i*2)=='G'){
        //     //     map.get(i*2+1)[1]++;
        //     // }
        //     // else if(rings.charAt(i*2)=='B'){
        //     //     map.get(i*2+1)[2]++;
        //     // }
        // }

        for(int i=0; i<rings.length()/2; i++){
            int key = Integer.parseInt(rings.charAt(i*2+1)+"");
            char value = rings.charAt(i*2);

            if(rods[key][mapping.get(value)] < 1){
                rods[key][mapping.get(value)]++;
                rods[key][3]++;    
            }

            if(rods[key][3]==3){
                System.out.println(key);
                rods[key][3]++;
                count++;
            }
        }

        return count;
    }

    public int[] distinctDifferenceArray(int[] nums) {
        int[] diff = new int[nums.length];

        for(int i=0;i<nums.length;i++){
            diff[i] = nums[i] - nums.length-i-2;
        }

        return diff;
    }
}
