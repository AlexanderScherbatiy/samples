rm -rf ./bin
mkdir -p bin/static
mkdir -p bin/shared

# build sources
gcc -c -fPIC src/resolved_impl.c -Iinclude -o bin/shared/resolved_impl.o
gcc -c -fPIC src/shared_lib.c    -Iinclude -o bin/shared/shared_lib.o
gcc -shared bin/shared/shared_lib.o bin/shared/resolved_impl.o -Iinclude -o bin/shared/libshared_lib.so
gcc -c src/main.c                -Iinclude -o bin/main.o
gcc bin/main.o -ldl   -o bin/main

# run
export LD_LIBRARY_PATH=./bin/shared
./bin/main