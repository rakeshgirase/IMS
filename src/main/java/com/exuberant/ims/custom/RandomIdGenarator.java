package com.exuberant.ims.custom;

import java.util.Random;

public class RandomIdGenarator {
    public static String randomstring(int lo, int hi) {
        int n = rand(lo, hi);
        byte[] b = new byte[n];
        for (int i = 0; i < n; i++)
            b[i] = ((byte) rand(48, 57));
        return new String(b, 0);
    }

    private static int rand(int lo, int hi) {
        Random rn = new Random();
        int n = hi - lo + 1;
        int i = rn.nextInt(n);
        if (i < 0)
            i = -i;
        return lo + i;
    }

    public static String randomstring() {
        return randomstring(5, 5);
    }
}
