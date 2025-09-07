@echo on
set JAVA_HOME="D:\Program Files\Java\jdk-21"

REM Настройка путей
set SRC_DIR=./src/main/java
set TEST_DIR=./src/test/java
set BUILD_DIR=./build

REM Удаление старых файлов
rd /s /q "%BUILD_DIR%"
md "%BUILD_DIR%"

%JAVA_HOME%\bin\javac -cp "./lib/*" -d "%BUILD_DIR%" %SRC_DIR%/ru/syspro/Task_1_1_1.java %TEST_DIR%/ru/syspro/Task_1_1_1Test.java


java -cp "./lib/*;./%BUILD_DIR%" org.junit.platform.console.ConsoleLauncher execute --scan-class-path