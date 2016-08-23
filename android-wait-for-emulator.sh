#!/bin/bash

# Originally written by Ralf Kistner <ralf@embarkmobile.com>, but placed in the public domain
# Source: https://github.com/gildegoma/chef-android-sdk/blob/master/files/default/android-wait-for-emulator

set +e

bootanim=""
failcounter=0
until [[ "$bootanim" =~ "running" ]]; do
   bootanim=`adb -e shell getprop init.svc.bootanim 2>&1`
   echo "$bootanim"
   if [[ "$bootanim" =~ "error" ]]; then
      let "failcounter += 1"
      if [[ $failcounter -gt ${WAIT_EMULATOR_MAX_RETRIES} ]]; then
        echo "Failed to start emulator"
        exit 1
      fi
   fi
   sleep ${WAIT_EMULATOR_TIME}
done
echo "Done"

set -e