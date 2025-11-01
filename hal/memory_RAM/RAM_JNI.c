// hal/memory_RAM/RAM_JNI.c
// JNI bridge between Java layer and low-level RAM.S routines

#include <jni.h>
#include <stdio.h>
#include <stdint.h>

// Declarações das funções assembly (do RAM.S)
extern void ram_init(void);
extern int ram_test(void);
extern void ram_clear(void);

// JNI wrappers
JNIEXPORT void JNICALL
Java_hal_memory_1RAM_NativeRamBridge_init(JNIEnv *env, jobject thiz) {
    ram_init();
}

JNIEXPORT jboolean JNICALL
Java_hal_memory_1RAM_NativeRamBridge_testPattern(JNIEnv *env, jobject thiz) {
    int result = ram_test();
    return result ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT void JNICALL
Java_hal_memory_1RAM_NativeRamBridge_clear(JNIEnv *env, jobject thiz) {
    ram_clear();
}

// Opcional: função de log nativo
JNIEXPORT void JNICALL
Java_hal_memory_1RAM_NativeRamBridge_log(JNIEnv *env, jobject thiz, jstring msg) {
    const char *nativeMsg = (*env)->GetStringUTFChars(env, msg, 0);
    printf("[RAM JNI] %s\n", nativeMsg);
    (*env)->ReleaseStringUTFChars(env, msg, nativeMsg);
}
