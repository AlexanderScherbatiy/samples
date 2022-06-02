#include "load_lib1.h"
#include "load_lib2.h"

int main() {

    lib1_load();

    const char *shared_lib_path = "bin/shared/libload_lib.so";

    lib2_load(shared_lib_path);

    return 0;
}