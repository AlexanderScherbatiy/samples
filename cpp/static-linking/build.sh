#!/bin/bash
set -e

rm -rf ./bin
mkdir -p bin/shared
mkdir -p bin/static

# build sources
gcc -c -fPIC src/load_lib.c  -Iinclude -o bin/shared/load_lib.o
gcc -c -fPIC src/load_lib1.c -Iinclude -o bin/shared/load_lib1.o
gcc -c -fPIC src/load_lib2.c -Iinclude -o bin/shared/load_lib2.o

#dynamic
gcc -shared bin/shared/load_lib.o  -Iinclude -o bin/shared/libload_lib.so
gcc -shared bin/shared/load_lib1.o -Iinclude -o bin/shared/libload_lib1.so
gcc -shared bin/shared/load_lib2.o -Iinclude -o bin/shared/libload_lib2.so

gcc -c src/main.c  -Iinclude -o bin/main.o
gcc bin/main.o -ldl -Lbin/shared -lload_lib2 -lload_lib1 -lload_lib   -o bin/main-dynamic

#static

ar rcs bin/static/libload_lib.a bin/shared/load_lib.o
ar rcs bin/static/libload_lib1.a bin/shared/load_lib1.o
ar rcs bin/static/libload_lib2.a bin/shared/load_lib2.o

gcc bin/main.o  -Lbin/static -lload_lib2 -lload_lib1 -lload_lib  -o bin/main-static

# run
export LD_LIBRARY_PATH=./bin/shared
echo "Use dynamic linking"
./bin/main-dynamic
echo "Use static  linking"
./bin/main-static
