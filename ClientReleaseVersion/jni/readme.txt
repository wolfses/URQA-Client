android.mk Example
----------------------------------------------------------------------
LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS )
LOCAL_MODULE := urqa
LOCAL_SRC_FILES  = ./$(TARGET_ARCH_ABI)/liburqanative.a
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)

LOCAL_DEFAULT_CPP_EXTENSION := cpp

LOCAL_MODULE := csourcep
LOCAL_SRC_FILES := csourcep.cpp
LOCAL_STATIC_LIBRARIES += urqa
LOCAL_LDLIBS := -L$(SYSROOT)/usr/lib \

LOCAL_C_INCLUDES        := $(LOCAL_PATH)/header/common/android/include \
                           $(LOCAL_PATH)/header
 
include $(BUILD_SHARED_LIBRARY)
----------------------------------------------------------------------

Application.mk example
APP_STL := stlport_static
APP_ABI := armeabi armeabi-v7a 
