# CMAKE_MINIMUM_REQUIRED(VERSION 2.88)
CMAKE_MINIMUM_REQUIRED(VERSION 2.8.12.2)

PROJECT(Sample C CXX)

SET (CMAKE_CXX_STANDARD 11)

SET(CMAKE_MODULE_PATH "${CMAKE_SOURCE_DIR}")

# Configure CCache if available
find_program(CCACHE_FOUND ccache)
if(CCACHE_FOUND)
    message("Use ccache.")
    set_property(GLOBAL PROPERTY RULE_LAUNCH_COMPILE ccache)
    set_property(GLOBAL PROPERTY RULE_LAUNCH_LINK ccache)
endif(CCACHE_FOUND)

add_library(stacktrace SHARED lib/stacktrace.h)
set_target_properties(stacktrace PROPERTIES LINKER_LANGUAGE CXX)
target_include_directories(stacktrace PUBLIC ${CMAKE_CURRENT_LIST_DIR}/lib)

add_library(mylib SHARED lib/util.h lib/util.cpp)
#add_library(mylib SHARED lib/util.h lib/util.cpp lib/b.cpp)
set_target_properties(mylib PROPERTIES LINKER_LANGUAGE CXX)
TARGET_LINK_LIBRARIES(mylib stacktrace)
target_include_directories(mylib PUBLIC ${CMAKE_CURRENT_LIST_DIR}/lib)

ADD_EXECUTABLE(sample src/sample.cpp)
TARGET_LINK_LIBRARIES(sample mylib stacktrace)

find_package(CxxTest REQUIRED)
if(CXXTEST_FOUND)

  message("CXXTEST_FOUND is found!")
  include_directories(${CXXTEST_INCLUDE_DIR})
  enable_testing()
  ADD_CUSTOM_TARGET(tests)

  CXXTEST_ADD_TEST(sample_test
      sample_test.cpp
      ${CMAKE_CURRENT_SOURCE_DIR}/tests/SampleUTest.h  # YOUR TEST DESCRIPTION FILE
  )
    target_link_libraries(sample_test mylib ${CMAKE_DL_LIBS})
#    target_link_libraries(testlink ${CMAKE_DL_LIBS})

 endif()

#SUMMARY_ADD("Unit tests" "Unit tests" CXXTEST_FOUND)