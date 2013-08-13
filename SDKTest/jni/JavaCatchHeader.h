 
#include <iostream>
#include <android/log.h>
#include <exception>


#define CATCH_CPP_EXCEPTION_AND_THROW_JAVA_EXCEPTION              \
	\
  catch (std::bad_alloc& e)                                 \
  {                                                               \
	  __android_log_print(ANDROID_LOG_FATAL, "Exception", "come");	\
	  env->ExceptionDescribe();    env->ExceptionClear();			\
	  jclass jc = env->FindClass("java/lang/OutOfMemoryError");     \
	   __android_log_print(ANDROID_LOG_FATAL, "Exception", "end1");   \
	  if(jc) env->ThrowNew (jc, e.what());                          \
	  __android_log_print(ANDROID_LOG_FATAL, "Exception", "end2");          \
  }                                                               \
  catch (std::ios_base::failure& e)                         \
  {                                        \
		__android_log_print(ANDROID_LOG_FATAL, "Exception", "1");                       \
	  /* IO exception */                                            \
	  jclass jc = env->FindClass("java/io/IOException");            \
	  if(jc) env->ThrowNew (jc, e.what());                          \
  }                                                               \
  catch (std::exception& e)                                 \
  {                                                               \
  /* unknown exception */                                       \
		__android_log_print(ANDROID_LOG_FATAL, "Exception", "2");\
  jclass jc = env->FindClass("java/lang/Error");                \
  if(jc) env->ThrowNew (jc, e.what());                          \
  }                                                               \
  catch (...)                                                     \
  {                                                               \
  		__android_log_print(ANDROID_LOG_FATAL, "Exception", "3");\
  /* Oops I missed identifying this exception! */               \
    __android_log_print(ANDROID_LOG_FATAL, "Exception", "4");\
    	jclass jc = env->FindClass("java/lang/OutOfMemoryError");\
	if(jc) env->ThrowNew (jc, "hello");							\
		  env->ExceptionDescribe();    env->ExceptionClear();			\
  }	\



