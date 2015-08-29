@echo off
call %cd:~0,3%java\bin\jar.exe -cmf m.mf test.jar S.class S$Point3D.class S$1.class S$2.class
pause