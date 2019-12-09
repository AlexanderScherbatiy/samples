rm -rf ./bin
mkdir -p bin/static
mkdir -p bin/shared

# build sources
gcc -c -fPIC src/resolved_impl.c -o bin/shared/resolved_impl.o
gcc -shared bin/shared/resolved_impl.o -o bin/shared/libresolved_lib.so
gcc -c src/main.c -o bin/main.o
gcc bin/main.o -Lbin/shared -lresolved_lib -o bin/main

# run
export LD_LIBRARY_PATH=./bin/shared
./bin/main