#!/bin/sh
# grab the artifact and version from gradle.env and settings.gradle

artifact=`grep "rootProject.name" settings.gradle  | cut -f2 -d= | tr -d " '"`
version=`grep "^version=" gradle.properties | cut -d= -f2`

if [ -z "${artifact}" ] || [ -z "${version}" ]; then
    echo "Unable to determine artifact and version from gradle.env and settings.gradle"
    exit 2
fi

cd build/libs

java -server -Xmx300M -XX:+UseG1GC \
      -Dinput.path='D:\\workspace-spring\\test' \
      -jar "${artifact}-${version}.jar" \
      --spring.output.ansi.enabled=always \
      --spring.profiles.active=local