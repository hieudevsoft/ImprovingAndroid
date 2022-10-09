//
// Created by hieudevs on 2022-09-23.
//

#include<jni.h>
#include<string>
#include<iostream>

extern "C" JNIEXPORT jlong JNICALL
Java_com_androidnative_demoimprovingperformanceandroid_JNILib_getFibJNI(JNIEnv *env, jclass clazz,jlong n) {
    long prev = -1;
    long result = 1;

    for(int i = 0 ; i<n;i++){
        long sum = result+prev;
        prev = result;
        result = sum;
    }

    return result;
}

extern "C"
JNIEXPORT jdouble JNICALL
Java_com_androidnative_demoimprovingperformanceandroid_JNILib_getJNICircleArea(JNIEnv *env,jclass clazz,jlong n) {
    return 3.14*n*n;
}