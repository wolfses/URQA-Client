#ifndef URQA_H__
#define URQA_H__

#include <cstdlib>
#include <stdexcept>
#include <android/log.h>

#include "client/linux/handler/exception_handler.h"
#include "client/linux/handler/minidump_descriptor.h"
/*
static bool DumpCallback(const google_breakpad::MinidumpDescriptor& descriptor,
						void* context,
						bool succeeded) {

	__android_log_print(ANDROID_LOG_DEBUG, "URQAnative", "Dump path: %s\n", descriptor.path());
	//여기서 처리해줘야됨
	return succeeded;
}
*/
class UrqaNative
{
private:

public:
	UrqaNative();
	~UrqaNative();


public:
	static void URQAIntialize();
	static void URQASendException(std::exception &e);
	static bool DumpCallback(const google_breakpad::MinidumpDescriptor& descriptor,
								void* context,
								bool succeeded);
};


#endif
