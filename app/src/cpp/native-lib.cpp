#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_umeng_error_ErrorActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    int* p = NULL;
    *p = 4;
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
