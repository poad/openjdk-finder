#!/usr/bin/env sh

CUR=$(pwd)

YARN_PROJECTS="\
  web-client
"

for target in ${YARN_PROJECTS}
do
  cd ${CUR}/${target}
  yarn upgrade
  yarn install
done


NPM_PROJECTS="\
  backend/OpenJdkReleaseInformationCollector
"

for target in ${NPM_PROJECTS}
do
  cd ${CUR}/${target}
  npm update
  npm upgrade
done

cd ${CUR}
