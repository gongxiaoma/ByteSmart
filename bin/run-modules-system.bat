@echo off
echo.
echo [��Ϣ] ʹ��Jar��������bytesmart-system-center���̡�
echo.

cd %~dp0
cd ../bytesmart-system-center/target

set JAVA_OPTS=-Xms512m -Xmx1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m

java -Dfile.encoding=utf-8 %JAVA_OPTS% -jar bytesmart-system-center.jar

cd bin
pause