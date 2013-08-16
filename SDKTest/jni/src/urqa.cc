#include "urqa.h"



UrqaNative::UrqaNative()
{

}

UrqaNative::~UrqaNative()
{

}

bool UrqaNative::DumpCallback(const google_breakpad::MinidumpDescriptor& descriptor,
		void* context,
		bool succeeded)
{
	__android_log_print(ANDROID_LOG_DEBUG, "URQAnative", "Dump path: %s\n", descriptor.path());
	//여기서 처리해줘야됨
	return succeeded;
}

void UrqaNative::URQAIntialize()
{
	__android_log_print(ANDROID_LOG_DEBUG, "URQAnative", "IntializeURQANative");
	google_breakpad::MinidumpDescriptor descriptor(".");
	google_breakpad::ExceptionHandler eh(descriptor, NULL, UrqaNative::DumpCallback,
	                                       NULL, true, -1);

	__android_log_print(ANDROID_LOG_DEBUG, "URQAnative", "IntializeURQANativeSuccess");
}

void UrqaNative::URQASendException(std::exception &e)
{

}

