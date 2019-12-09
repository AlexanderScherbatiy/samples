#include <stdio.h>
#include <stdlib.h>
#include <dlfcn.h>

void call_resolved_function_dynamic() {

    const char *lib_path = "bin/shared/libshared_lib.so";
    void (*call_resolved_function)(void);

    void *handle = dlopen(lib_path, RTLD_LAZY);

    if (!handle) {
        fprintf(stderr, "dlopen failed: %s\n", dlerror());
        exit(EXIT_FAILURE);
    }

    *(void **) (&call_resolved_function) = dlsym(handle, "call_resolved_function");

    char *error = dlerror();
    if (error != NULL)  {
        fprintf(stderr, "%s\n", error);
        exit(EXIT_FAILURE);
    }

    (*call_resolved_function)();
    dlclose(handle);
}

int main(int argc, char* argv[]) {
    printf("call main.\n");
    call_resolved_function_dynamic();
}
