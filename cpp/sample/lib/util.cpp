/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


#include "util.h"

Sample Sample::instance(1);
std::string Sample::global_string = "global string";

struct Test {
    static std::string test;
};

std::string Test::test = "abc";