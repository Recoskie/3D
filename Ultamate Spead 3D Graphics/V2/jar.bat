@echo off
call %cd:~0,3%java\bin\jar.exe -cmf m.mf test.jar *.class *.png
pause