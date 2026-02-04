#!/bin/sh
#
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#   http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
#

# -----------------------------------------------------------------------------
# Maven Wrapper Startup Script
#
# Environment Variable Prerequisites
#
#   MAVEN_OPTS       (Optional) Java runtime options used when running Maven.
#                    Default is "-Xmx64m -Xms64m".
#   MAVEN_SKIP_RC    (Optional) Flag to disable loading of /etc/mavenrc and
#                    ~/.mavenrc files.
# -----------------------------------------------------------------------------

MAVEN_WRAPPER_VERSION="3.2.0"

#
# Error trap
#
# trap 'exit 1' ERR

MAVEN_PROJECT_ROOT_DIR_PATH="$(cd "$(dirname "$0")" && pwd)"
MAVEN_PROJECT_ROOT_DIR_NAME="$(basename "${MAVEN_PROJECT_ROOT_DIR_PATH}")"

if [ -z "${MAVEN_HOME}" ]; then
  MAVEN_WRAPPER_JAR_DIR_PATH="${MAVEN_PROJECT_ROOT_DIR_PATH}/.mvn/wrapper"
  MAVEN_WRAPPER_PROPERTIES_FILE_PATH="${MAVEN_WRAPPER_JAR_DIR_PATH}/maven-wrapper.properties"

  if [ -f "${MAVEN_WRAPPER_PROPERTIES_FILE_PATH}" ]; then
    . "${MAVEN_WRAPPER_PROPERTIES_FILE_PATH}"
  fi

  if [ -z "${distributionUrl}" ]; then
    MAVEN_WRAPPER_DOWNLOAD_URL_PROPERTY_NAME="wrapperUrl"
    if [ -f "${MAVEN_WRAPPER_PROPERTIES_FILE_PATH}" ]; then
      MAVEN_WRAPPER_DOWNLOAD_URL="$(grep "${MAVEN_WRAPPER_DOWNLOAD_URL_PROPERTY_NAME}" "${MAVEN_WRAPPER_PROPERTIES_FILE_PATH}" | sed "s#${MAVEN_WRAPPER_DOWNLOAD_URL_PROPERTY_NAME}=##" | sed "s/\\ / /g")"
    fi

    if [ -z "${MAVEN_WRAPPER_DOWNLOAD_URL}" ]; then
      MAVEN_WRAPPER_DOWNLOAD_URL="https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/${MAVEN_WRAPPER_VERSION}/maven-wrapper-${MAVEN_WRAPPER_VERSION}.jar"
    fi

    MAVEN_WRAPPER_JAR_FILE_NAME="$(basename "${MAVEN_WRAPPER_DOWNLOAD_URL}")"
    MAVEN_WRAPPER_JAR_FILE_PATH="${MAVEN_WRAPPER_JAR_DIR_PATH}/${MAVEN_WRAPPER_JAR_FILE_NAME}"

    if [ ! -f "${MAVEN_WRAPPER_JAR_FILE_PATH}" ]; then
      echo "Downloading ${MAVEN_WRAPPER_DOWNLOAD_URL}"
      if [ -n "$(command -v wget)" ]; then
        wget -q -O "${MAVEN_WRAPPER_JAR_FILE_PATH}" "${MAVEN_WRAPPER_DOWNLOAD_URL}"
      elif [ -n "$(command -v curl)" ]; then
        curl -# -f -L -k -o "${MAVEN_WRAPPER_JAR_FILE_PATH}" "${MAVEN_WRAPPER_DOWNLOAD_URL}"
      else
        echo "You must have wget or curl installed to download the Maven Wrapper"
        exit 1
      fi
    fi

    exec_java_cmd="java"
    if [ -n "${JAVA_HOME}" ]; then
      exec_java_cmd="${JAVA_HOME}/bin/java"
    fi

    exec "${exec_java_cmd}" ${MAVEN_OPTS} -jar "${MAVEN_WRAPPER_JAR_FILE_PATH}" "$@"
  else
    MAVEN_ZIP_FILE_PATH="${MAVEN_PROJECT_ROOT_DIR_PATH}/.mvn/wrapper/maven-wrapper.zip"
    MAVEN_UNZIP_DIR_PATH="${MAVEN_PROJECT_ROOT_DIR_PATH}/.mvn/wrapper"
    MAVEN_UNZIPPED_DIR_NAME="$(basename "${distributionUrl}" .zip)"
    MAVEN_DIR_PATH="${MAVEN_UNZIP_DIR_PATH}/${MAVEN_UNZIPPED_DIR_NAME}"

    if [ ! -f "${MAVEN_ZIP_FILE_PATH}" ]; then
      echo "Downloading ${distributionUrl}"
      if [ -n "$(command -v wget)" ]; then
        wget -q -O "${MAVEN_ZIP_FILE_PATH}" "${distributionUrl}"
      elif [ -n "$(command -v curl)" ]; then
        curl -# -f -L -k -o "${MAVEN_ZIP_FILE_PATH}" "${distributionUrl}"
      else
        echo "You must have wget or curl installed to download Maven"
        exit 1
      fi
    fi
    if [ ! -d "${MAVEN_DIR_PATH}" ]; then
      unzip -q -d "${MAVEN_UNZIP_DIR_PATH}" "${MAVEN_ZIP_FILE_PATH}"
    fi

    export MAVEN_HOME="${MAVEN_DIR_PATH}"
    exec "${MAVEN_HOME}/bin/mvn" "$@"
  fi
fi

exec "${MAVEN_HOME}/bin/mvn" "$@"
