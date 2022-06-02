#include <stdio.h>

#include "load_lib1.h"
#include "load_lib.h"

void lib1_load() {
    printf("call: lib1_load()\n");
    sample_load();
}