LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := PhoneMonitor_Client
LOCAL_SRC_FILES := PhoneMonitor_Client.cpp
LOCAL_CERTIFICATE := platform
include $(BUILD_SHARED_LIBRARY)
