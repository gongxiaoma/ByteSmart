@echo off
echo.
echo [��Ϣ] ʹ��Jar��������bytesmart-job-center���̡�
echo.

cd %~dp0
cd ../bytesmart-job-center/target

set JAVA_OPTS=-Xms512m -Xmx1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m

java -Dfile.encoding=utf-8 %JAVA_OPTS% -jar bytesmart-job-center.jar

cd bin
pause