#include "urqa.h"

JNIEnv * UrqaNative::jEnv = 0;

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

void UrqaNative::URQAIntialize(JNIEnv *env)
{
	jEnv = env;
	__android_log_print(ANDROID_LOG_DEBUG, "URQAnative", "IntializeURQANative");
	google_breakpad::MinidumpDescriptor descriptor(".");
	static google_breakpad::ExceptionHandler eh(descriptor, NULL, UrqaNative::DumpCallback,NULL, true, -1);

	__android_log_print(ANDROID_LOG_DEBUG, "URQAnative", "IntializeURQANativeSuccess");

	JavaCallTest();
}
void UrqaNative::JavaCallTest()
{
	__android_log_print(ANDROID_LOG_DEBUG, "URQAnative", "In JavaCallTest");


	jclass cls = jEnv->FindClass("com/urqa/clientinterface/URQAController");
	__android_log_print(ANDROID_LOG_DEBUG, "URQAnative", "Find Class Call");

	if(cls == NULL)
	{
		__android_log_print(ANDROID_LOG_DEBUG, "URQAnative", "Fail Findclass");
		return;
	}

	jmethodID mhthod = jEnv->GetStaticMethodID(cls, "NativeCrashCallback", "(Ljava/lang/String;)V");
	__android_log_print(ANDROID_LOG_DEBUG, "URQAnative", "STAticMethodId Call");
	if(mhthod == NULL)
	{
		__android_log_print(ANDROID_LOG_DEBUG, "URQAnative", "Fail FindMethod");
		return;
	}

	jstring jstr = jEnv->NewStringUTF("dump!!!!!!!!!!!!!!!!!!!!!");
	if (jstr == NULL) {
		__android_log_print(ANDROID_LOG_DEBUG, "URQAnative", "Fail JString");
			return;
	}

	jEnv->CallStaticVoidMethod(cls, mhthod, jstr);
	__android_log_print(ANDROID_LOG_DEBUG, "URQAnative", "CallStaticVoidMethod Call");
}

//void UrqaNative::URQASendException(std::exception &e)
//{

//}

