#!/bin/bash

# raise an error if any command fails!
set -e

# existence of this file indicates that all dependencies were previously installed, and any changes to this file will use a different filename.
INITIALIZATION_FILE="$ANDROID_HOME/.initialized-dependencies-$(git log -n 1 --format=%h -- $0)"

if [ ! -e ${INITIALIZATION_FILE} ]; then
  echo "Fetch and initialize $ANDROID_HOME"
  download-android

  echo -n "Use the final android sdk tools..."
  echo y | android update sdk --no-ui --filter platform-tool > /dev/null
  echo y | android update sdk --no-ui --filter tool > /dev/null
  echo "done."

  echo -n "Update build tools to 24..."
  echo y | android update sdk --no-ui --filter build-tools-24.0.2 --all > /dev/null
  echo "done."

  echo -n "Update SDK to 23..."
  echo y | android update sdk --no-ui --filter android-23 > /dev/null
  echo "done."

  # uncomment to install the Extra/Android Support Library
  # echo y | android update sdk --no-ui --filter extra-android-support --all > /dev/null

  echo -n "Install repositories..."
  # uncomment these if you are using maven/gradle to build your android project
  echo y | android update sdk --no-ui --filter extra-google-m2repository --all > /dev/null
  echo y | android update sdk --no-ui --filter extra-android-m2repository --all > /dev/null
  echo "done."

  echo -n "Install emulator..."
  # Specify at least one system image if you want to run emulator tests
  echo y | android update sdk --no-ui --filter sys-img-armeabi-v7a-android-23 --all > /dev/null
  echo "done."

  touch ${INITIALIZATION_FILE}
fi
