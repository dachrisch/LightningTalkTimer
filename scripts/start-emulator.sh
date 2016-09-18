#!/usr/bin/env bash
set -e

echo no | android create avd --force -n test -t android-24 --abi armeabi-v7a
emulator -avd test -no-skin -no-audio -no-window -gpu off &
