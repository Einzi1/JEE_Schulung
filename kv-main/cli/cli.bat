@echo off
set NOPAUSE=true

if %1. == . goto info
if %1 == cli goto cli

call %JBOSS_HOME%\bin\jboss-cli.bat --file=%1.cli
goto end

:cli
call %JBOSS_HOME%\bin\jboss-cli.bat
goto end

:info
echo cli.bat dateiname (ohne ".cli")

:end