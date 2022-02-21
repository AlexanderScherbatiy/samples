cmake_minimum_required(VERSION 2.8)

project(shapes)
add_executable(shapes triangle.c)
find_package(OpenGL REQUIRED)
find_package(GLUT REQUIRED)
include_directories( ${OPENGL_INCLUDE_DIRS}  ${GLUT_INCLUDE_DIRS} )

target_link_libraries(shapes ${OPENGL_LIBRARIES} ${GLUT_LIBRARY} )

