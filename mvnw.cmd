@ECHO OFF
SETLOCAL ENABLEDELAYEDEXPANSION

pushd %~dp0

IF NOT EXIST ".mvn\wrapper\maven-wrapper.jar" (
  ECHO Downloading Maven Wrapper...
  IF EXIST ".mvn\wrapper\maven-wrapper.properties" (
    FOR /F "usebackq eol=# tokens=1,* delims==" %%i IN (".mvn\wrapper\maven-wrapper.properties") DO (
      IF "%%i"=="wrapperUrl" (
        SET "wrapperUrl=%%j"
      )
    )
  )
  IF NOT DEFINED wrapperUrl (
    SET wrapperUrl=https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.2.0/maven-wrapper-3.2.0.jar
  )
  ECHO From: !wrapperUrl!
  powershell -Command "& { [Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; $webClient = New-Object System.Net.WebClient; $webClient.DownloadFile('!wrapperUrl!', '.mvn\wrapper\maven-wrapper.jar') }"
  IF ERRORLEVEL 1 (
      ECHO Failed to download Maven Wrapper.
      EXIT /B 1
  )
)

SET MAVEN_CMD_LINE_ARGS=%*
java -jar .mvn\wrapper\maven-wrapper.jar %MAVEN_CMD_LINE_ARGS%

popd
EXIT /B %ERRORLEVEL%