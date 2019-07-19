#include <cxxtest/TestSuite.h>
#include "util.h"

#include <stdio.h>
#include <stdlib.h>
#include <dlfcn.h>


void c_singleton();

class SampleUTest : public CxxTest::TestSuite {
public:

    void testSample(void) {

        // Get sample from lib:
        Sample sing = singleton();

        const char *buf = "c_singleton";
        const char *lib_path = "/home/stellarspot/opencog/merge-2/sample-cpp/target/libmylib.so";
        void *hndl = dlopen(lib_path, RTLD_LAZY);
        if (!hndl) {
            fprintf(stderr, "dlopen failed: %s\n", dlerror());
            exit(EXIT_FAILURE);
        };

        //        void (*fptr) (void) = dlsym(hndl, buf);
        //        void (*fptr) (void) = dlsym(hndl, buf);
        auto fptr = reinterpret_cast<void(*)()> (dlsym(hndl, buf));
        //        fptr = (void (*)())dlsym(handle , buf);

        if (fptr != NULL) {
            fptr();
        } else {
            fprintf(stderr, "dlsym %s failed: %s\n", buf, dlerror());
        }

        dlclose(hndl);

        printf("test sample: END\n");

    }
};