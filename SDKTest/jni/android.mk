

LOCAL_PATH := $(call my-dir)
 
 
 
 include $(CLEAR_VARS)
LOCAL_MODULE := urqanative         		 # 대충 적으시면 됩니다.
LOCAL_SRC_FILES = ./liburqanative.a         # 준비된 라이브러리 파일명
include $(PREBUILT_STATIC_LIBRARY)
 
 
 
include $(CLEAR_VARS)

LOCAL_DEFAULT_CPP_EXTENSION := cpp
 
# Here we give our module name and source file(s)
LOCAL_MODULE := csourcep
LOCAL_SRC_FILES := csourcep.cpp
LOCAL_LDLIBS := -L$(SYSROOT)/usr/lib -llog \
				
LOCAL_STATIC_LIBRARIES := urqanative


LOCAL_C_INCLUDES        := $(LOCAL_PATH)/src/common/android/include \
                           $(LOCAL_PATH)/src

LOCAL_EXPORT_C_INCLUDES := $(LOCAL_C_INCLUDES)   
 
include $(BUILD_SHARED_LIBRARY)