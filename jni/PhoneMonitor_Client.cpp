#include <string.h>
#include <jni.h>

extern "C" jstring
Java_com_feiyu_gui_GuardPage_stringTestNdk( JNIEnv* env,
                                                  jobject thiz )
{
    return env->NewStringUTF("Hello Test NDK !");
}
