package com.company;

public class Main {
    
    public static int maxSumTwoNoOverlap(int [] A, int K, int L) {

        if(K + L  > A.length)
            return -1;

        int[] prefix = new int[A.length+1];
        for(int i = 1; i < prefix.length; i++)
            prefix[i] = A[i-1] + prefix[i-1];

        int[] dp1 = new int[A.length];
        int[] dp2 = new int[A.length];

        int sum = prefix[L] - prefix[0];
        dp1[L-1] = sum;
        for(int i = L; i < A.length; i++) {
            sum += (A[i]-A[i-L]);
            dp1[i] = Math.max(sum, dp1[i-1]);
        }
        sum = prefix[A.length] - prefix[A.length-L];

        dp2[A.length-L] = sum;
        for(int i = A.length-L-1; i >= 0; i--) {
            sum += (A[i]-A[i+L]);
            dp2[i] = Math.max(sum, dp2[i+1]);
        }
        int result = 0;
        for(int i = 0; i + K - 1 < A.length; i++) {
            int j = i + K - 1;
            int sumL = prefix[j+1] - prefix[i];
            int sumM = Math.max(i>1 ? dp1[i-1] : 0, j+1 < A.length ? dp2[j+1] : 0);
            result = Math.max(result, sumM + sumL);
        }
        return result;
    }


    public static void main(String[] args) {
        int[] arr = {3, 8, 1 ,4};
        System.out.print(maxSumTwoNoOverlap(arr, 1, 1));
    }
}
