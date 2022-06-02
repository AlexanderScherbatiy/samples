#include <stdio.h>
#include <stdlib.h>
#include <dlfcn.h>

#include "load_lib2.h"

void lib2_load(const char* shared_lib_path) {

    printf("inside lib2: lib2_load()\n");

    printf("inside lib2: call sample_load() dynamically\n");
    void (*call_sample_load)(void);

    void *handle = dlopen(shared_lib_path, RTLD_LAZY);

    if (!handle) {
        fprintf(stderr, "dlopen failed: %s\n", dlerror());
        exit(EXIT_FAILURE);
    }

    *(void **) (&call_sample_load) = dlsym(handle, "sample_load");

    char *error = dlerror();
    if (error != NULL)  {
        fprintf(stderr, "%s\n", error);
        exit(EXIT_FAILURE);
    }

    (*call_sample_load)();
}
