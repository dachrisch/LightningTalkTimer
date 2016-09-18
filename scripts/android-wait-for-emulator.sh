#!/bin/bash

# Originally written by Ralf Kistner <ralf@embarkmobile.com>, but placed in the public domain
# Source: https://github.com/gildegoma/chef-android-sdk/blob/master/files/default/android-wait-for-emulator
set -e

echo -n "checking for device start..."
bootanim=""
failcounter=0
until [[ "$bootanim" =~ "stopped" ]]; do
   bootanim=`adb -e shell getprop init.svc.bootanim 2>&1`
   echo -n "."
   let "failcounter += 1"
   if [[ ${failcounter} -gt ${WAIT_EMULATOR_MAX_RETRIES} ]]; then
        echo "timed out after waiting $WAIT_EMULATOR_MAX_RETRIES times ${WAIT_EMULATOR_TIME}s"
        exit 1
   fi
   sleep ${WAIT_EMULATOR_TIME}
done
echo "started!"

echo -n "checking for package service..."
service_check=""
failcounter=0
until [[ "$service_check" =~ "Service package: found" ]]; do
    service_check=`adb shell service check package`
    echo -n "."
    let "failcounter += 1"
    if [[ ${failcounter} -gt ${WAIT_EMULATOR_MAX_RETRIES} ]]; then
        echo "timed out after waiting $WAIT_EMULATOR_MAX_RETRIES times ${WAIT_EMULATOR_TIME}s"
        exit 1
    fi
    sleep ${WAIT_EMULATOR_TIME}
done
echo "found!"
