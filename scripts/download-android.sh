#!/bin/bash
set -e

ANDROID_SDK_URL="http://dl.google.com/android/android-sdk_r24.3.3-linux.tgz"
ANDROID_SDK_TAR="android-sdk_r24.3.3-linux.tgz"
ANDROID_TAR_PATH="${SNAP_CACHE_DIR}/downloads/${ANDROID_SDK_TAR}"
TEST_FILE=${ANDROID_HOME}/.snap-initialization

if [ ! -e "${TEST_FILE}" ]; then
  mkdir -p ${ANDROID_HOME}
  mkdir -p $(dirname ${ANDROID_TAR_PATH})
  echo "Downloading android sdk from ${ANDROID_SDK_URL}"
  wget --quiet http://dl.google.com/android/android-sdk_r24.3.3-linux.tgz -O ${ANDROID_TAR_PATH}
  echo "Verifying integrity of downloaded file."
  (sha1sum ${ANDROID_TAR_PATH} | grep --silent --word-regexp cd4cab76c2e3d926b3495c26ec56c831ba77d0d0) || (echo "Could not verify integrity of android sdk, please try again"; exit 1)
  echo "Unpacking android sdk to \$ANDROID_HOME"
  tar -zxf ${ANDROID_TAR_PATH} -C ${ANDROID_HOME} --strip-components=1
  echo "Done. Android SDK is now available in \$ANDROID_HOME($ANDROID_HOME)"
  touch ${TEST_FILE}
else
  echo "Android SDK is already downloaded and installed in \$ANDROID_HOME($ANDROID_HOME)"
fi
