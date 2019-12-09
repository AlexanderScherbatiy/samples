#include <stdio.h>
#include <shared_lib.h>

int main(int argc, char* argv[]) {
    printf("call main.\n");
    call_resolved_function();
}
