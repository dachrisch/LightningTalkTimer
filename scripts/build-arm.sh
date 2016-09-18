#!/usr/bin/env bash
set -e

export SNAP_CACHE_DIR=/var/services/tmp/
export ANDROID_HOME=/var/services/homes/cda/dev/android_sdk
export ANDROID_SWT_JAR=/var/packages/CrashPlan/target/lib/swt.jar
export WAIT_EMULATOR_TIME=15
export WAIT_EMULATOR_MAX_RETRIES=15

pushd $(dirname $0)

if [ ! -d ${ANDROID_HOME}/tools/lib/arm ];then
    echo -n "copying SWT from CrashPlan..."
    mkdir ${ANDROID_HOME}/tools/lib/arm
    cp ${ANDROID_SWT_JAR} ${ANDROID_HOME}/tools/lib/arm
fi

export PATH=$PATH:${ANDROID_HOME}/tools:/var/packages/git/target/bin

echo "[1] Initialize Android SDK"
./initialize-android.sh

echo "[2] Build the app"
./gradlew test assemble

echo "[3] Startup Emulator for UI tests"
./start-emulator.sh

./android-wait-for-emulator.sh

echo "[4] Running UI tests"
./gradlew connectedAndroidTest --stacktrace

popd
