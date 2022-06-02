#include <stdio.h>
#include <stdlib.h>
#include <dlfcn.h>

void* get_lib_handle() {

    const char *lib_path = "bin/shared/libshared_lib.so";
    void (*call_resolved_function)(void);

    void *handle = dlopen(lib_path, RTLD_LAZY);

    if (!handle) {
        fprintf(stderr, "dlopen failed: %s\n", dlerror());
        exit(EXIT_FAILURE);
    }

    return handle;
}

void call_resolved_function_dynamic() {

    void (*call_resolved_function)(void);

    void *handle = get_lib_handle();

    *(void **) (&call_resolved_function) = dlsym(handle, "call_resolved_function");

    char *error = dlerror();
    if (error != NULL)  {
        fprintf(stderr, "%s\n", error);
        exit(EXIT_FAILURE);
    }

    (*call_resolved_function)();
    dlclose(handle);
}

void print_dynamic_variable_value() {

    void *handle = get_lib_handle();

    int* p = (int*)dlsym(handle, "my_var");

    printf("my_variable address: %p\n", p);

    if (p) {
        printf("my_variable value  : %d\n", *p);
    } else {
        printf("dlsym failed\n");
        exit(EXIT_FAILURE);
    }

    dlclose(handle);
}

void call_get_len_function_dynamic() {

    void *handle = get_lib_handle();

    int (*my_get_len) () = dlsym(handle, "get_len");

    char* str = "abcdefghijk";

    int len = my_get_len(str);
    printf("call my_get_len directly   : %d\n", len);

    len = ( (int (*) () ) dlsym(handle, "get_len"))(str);
    printf("call my_get_len dynamically: %d\n", len);

    dlclose(handle);
}

void call_hello_function_dynamic() {

    void (*hello)(void);

    void *handle = get_lib_handle();

    *(void **) (&hello) = dlsym(handle, "hello");
    (*hello)();

    dlclose(handle);
}

int main(int argc, char* argv[]) {
    printf("call main     function.\n");
    call_resolved_function_dynamic();
    print_dynamic_variable_value();
    call_get_len_function_dynamic();
    call_hello_function_dynamic();
}
