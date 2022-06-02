rm -rf ./bin
mkdir -p bin/static
mkdir -p bin/shared

# build sources
gcc -c -fPIC src/load_lib.c  -Iinclude -o bin/shared/load_lib.o
gcc -c -fPIC src/load_lib1.c -Iinclude -o bin/shared/load_lib1.o

gcc -shared bin/shared/load_lib.o  -Iinclude -o bin/shared/libload_lib.so
gcc -shared bin/shared/load_lib1.o -Iinclude -o bin/shared/libload_lib1.so

gcc -c src/main.c  -Iinclude -o bin/main.o
gcc bin/main.o -ldl -Lbin/shared -lload_lib1 -lload_lib   -o bin/main

# run
export LD_LIBRARY_PATH=./bin/shared
./bin/main