@echo off
echo What File Do You Want To Compile
set /p n=
call %cd:~0,3%java\bin\javac.exe %n%.java
pause