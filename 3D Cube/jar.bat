@echo off
call %cd:~0,3%java\bin\jar.exe -cmf m.mf test.jar S.class S$Edge.class S$Point3D.class
pause