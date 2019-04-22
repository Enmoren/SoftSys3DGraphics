#include "com_dragon_opengles01_ndk_NDKTest.h"
#include <string.h>
JNIEXPORT jstring JNICALL Java_com_dragon_opengles01_ndk_NDKTest_hello
        (JNIEnv *env, jclass obj)
{
    return env->NewStringUTF("我是来自NDK的C++");
}

