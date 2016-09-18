#!/usr/bin/env bash

export SNAP_CACHE_DIR=/var/services/tmp/
export ANDROID_HOME=/var/services/homes/cda/dev/android_sdk

./download-android.sh

export PATH=$PATH:$ANDROID_HOME/tools:/var/packages/git/target/bin

./initialize-android.sh

