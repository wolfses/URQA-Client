#ifndef URQA_H__
#define URQA_H__

#include <cstdlib>
#include <stdexcept>
#include <android/log.h>
#include <jni.h>

#include "client/linux/handler/exception_handler.h"
#include "client/linux/handler/minidump_descriptor.h"


class UrqaNative
{
private:
	static JNIEnv *jEnv;

	static bool DumpCallback(const google_breakpad::MinidumpDescriptor& descriptor,
								void* context,
								bool succeeded);

	static void ExceptionJavaCall(const char* path);

public:
	UrqaNative();
	~UrqaNative();


public:
	static void URQAIntialize(JNIEnv *env);
	//static void URQASendException(std::exception &e);
};


#endif
