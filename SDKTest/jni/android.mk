LOCAL_PATH := $(call my-dir)
 
include $(CLEAR_VARS)

 
LOCAL_DEFAULT_CPP_EXTENSION := cpp
 
# Here we give our module name and source file(s)
LOCAL_MODULE := csourcep
LOCAL_SRC_FILES := csourcep.cpp
LOCAL_LDLIBS := -L$(SYSROOT)/usr/lib -llog
LOCAL_SHARED_LIBRARIES := liblog libcutils
   
 
include $(BUILD_SHARED_LIBRARY)