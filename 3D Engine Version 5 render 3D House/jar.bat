@echo off
call %cd:~0,3%java\bin\jar.exe -cmf m.mf 3D.jar *.class *.png
pause