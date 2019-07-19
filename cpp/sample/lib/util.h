/*
 * File:   util.h
 * Author: stellarspot
 *
 * Created on July 5, 2019, 11:30 AM
 */

#ifndef UTIL_H
#define UTIL_H

#include <string>
#include <memory>
#include <iostream>

#include "stacktrace.h"

class Sample {
public:

    Sample(int val) {
        this->value = val;
        printf("Sample(%d): constructor: %p\n", value, this);
    }

    ~Sample() {
        printf("Sample(%d): destructor: %p\n", value, this);
    }

    Sample(const Sample &s) {
        printf("Sample(%d): copy constructor: %p -> %p\n", s.value, &s, this);
        this->value = s.value;
    }


    static Sample instance;
    static std::string global_string;

    int value;

    std::string field;
};

Sample singleton() {
    static Sample sample{2};
    return sample;
}

std::shared_ptr<Sample> shared_ptr_singleton() {
    static std::shared_ptr<Sample> sample = std::shared_ptr<Sample>(new Sample(3));
    return sample;
}

class Holder {
public:

    Holder(Sample s) : sample{s}
    {
    }

    Sample sample;
};

class HolderPtr {
public:

    HolderPtr(std::shared_ptr<Sample> s) : sample{s}
    {
    }

    std::shared_ptr<Sample> sample;
};

std::ostream& operator<<(std::ostream &strm, const Sample &s) {
    return strm << "Sample(" << s.value << ")";
}

extern "C" void c_singleton();

void c_singleton() {
    printf("call extern C singleton\n");
    Sample sing = singleton();
}


#endif /* UTIL_H */

