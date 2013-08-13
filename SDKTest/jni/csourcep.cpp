#include <string.h>
#include <jni.h>
#include <android/log.h>
#include <stdio.h>

#include "JavaCatchHeader.h"

  
 extern "C" {
JNIEXPORT jstring Java_com_example_sdktest_MainActivity_invokeNativeFunction(JNIEnv* env, jobject javaThis) {
	__android_log_print(ANDROID_LOG_FATAL, "zzz", "start");
	

  
	try{
		__android_log_print(ANDROID_LOG_FATAL, "zzz", "start2");
		throw new std::bad_alloc();
	}
	CATCH_CPP_EXCEPTION_AND_THROW_JAVA_EXCEPTION
	
	__android_log_print(ANDROID_LOG_FATAL, "zzz", "start3");
	
    return env->NewStringUTF("Hello from C Code!");
}
}




