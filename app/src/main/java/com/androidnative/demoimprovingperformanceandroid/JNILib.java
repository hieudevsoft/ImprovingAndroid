package com.androidnative.demoimprovingperformanceandroid;

import android.util.Log;

public class JNILib {

    public static enum TYPE{
        FIBONACCI,AREA_CIRCLE
    }
    static long numLoop = 100;

    //Fibonacii
    public static long getFibJR(long n){
        return n<=0?0:n==1?1:getFibJR(n-1)+getFibJR(n-2);
    }
    public static long getFibJNR(long n){
        long prev = -1;
        long result = 1;

        for(int i = 0 ; i<n;i++){
            long sum = result+prev;
            prev = result;
            result = sum;
        }
        return result;
    }
    public static native long getFibJNI(long n);

    //Area circle

    public static double getCircleArea(long n){
        return Math.PI*n*n;
    }
    public static native double getJNICircleArea(long n);

    public static void checkPerformance(TYPE type){
        if(type==TYPE.FIBONACCI){
            long timeJR = 0;
            long timeJNR = 0;
            long timeJNI = 0;
            for(int i = 0 ;i<numLoop;i++){
                long startTime = System.nanoTime();
                long fibJR = getFibJR(i);
                timeJR+=System.nanoTime()-startTime;

                startTime = System.nanoTime();
                long fibJNR = getFibJNR(i);
                timeJNR+=System.nanoTime()-startTime;

                startTime = System.nanoTime();
                long FibJNI = getFibJNI(i);
                timeJNI = System.nanoTime()-startTime;
            }

            float averageFibJR = (float)timeJR/numLoop;
            float averageFibJNR = (float)timeJNR/numLoop;
            float averageFibJNI = (float)timeJNI/numLoop;

            Log.d("DEBUG", "checkPerformance: FibJR: " + averageFibJR +"ns");
            Log.d("DEBUG", "checkPerformance: FibJNR: " + averageFibJNR +"ns");
            Log.d("DEBUG", "checkPerformance: FibJNI: " + averageFibJNI +"ns");

            Log.d("DEBUG","compare performance - FibJNI/FibJR: " + 1/(averageFibJNI/averageFibJR));
            Log.d("DEBUG","compare performance - FibJNI/FibJNR: " + 1/(averageFibJNI/averageFibJNR));
        }else if(type==TYPE.AREA_CIRCLE){
            long timeJ = 0;
            long timeJNI = 0;
            for(long i = 0 ;i<numLoop;i++){
                long startTime = System.nanoTime();
                long result = (long) getCircleArea(i);
                timeJ+=System.nanoTime()-startTime;

                startTime = System.nanoTime();
                long result2 = (long) getJNICircleArea(i);
                timeJNI+=System.nanoTime()-startTime;
            }

            float averageJ= (float)timeJ/numLoop;
            float averageJNI = (float)timeJNI/numLoop;

            Log.d("DEBUG", "checkPerformance: J: " + averageJ +"ns");
            Log.d("DEBUG", "checkPerformance: JNI: " + averageJNI +"ns");
            Log.d("DEBUG","compare performance - JNI/J: " + (float)averageJ/averageJNI);

        }
    }
}
