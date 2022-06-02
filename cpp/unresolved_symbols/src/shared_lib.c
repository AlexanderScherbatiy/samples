#include <stdio.h>
#include <string.h>

#include "shared_lib.h"
#include "resolved_lib.h"
#include "unresolved_lib.h"


int my_var = 42;

void hello() {
    printf("Hello, World!\n");
}

void call_resolved_function() {
    resolved_function();
}

void call_unresolved_function() {
    unresolved_function();
}

int get_len(char* str) {
    return strlen(str);
}
