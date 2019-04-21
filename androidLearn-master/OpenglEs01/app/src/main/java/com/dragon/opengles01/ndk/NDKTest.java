package com.dragon.opengles01.ndk;

/**
 * This file created by dragon on 2016/7/31 0:25,
 * belong to com.dragon.opengles01.ndk .
 */
public class NDKTest {

    public native static String hello();
//    默认是NDK.moduleName
    static {
        System.loadLibrary("ndk_test");
    }
}
