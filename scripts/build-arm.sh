#!/usr/bin/env bash

export SNAP_CACHE_DIR=/var/services/tmp/
export ANDROID_HOME=/var/services/homes/cda/dev/android_sdk
export ANDROID_SWT_JAR=/var/packages/CrashPlan/target/lib/swt.jar

./download-android.sh

if [ ! -d ${ANDROID_HOME}/tools/lib/arm ];then
    echo -n "copying SWT from CrashPlan..."
    mkdir ${ANDROID_HOME}/tools/lib/arm
    cp ${ANDROID_SWT_JAR} ${ANDROID_HOME}/tools/lib/arm
fi

export PATH=$PATH:${ANDROID_HOME}/tools:/var/packages/git/target/bin

./initialize-android.sh
