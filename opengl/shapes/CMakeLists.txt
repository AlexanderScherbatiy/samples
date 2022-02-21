cmake_minimum_required(VERSION 2.8)

project(shapes)
add_executable(shapes triangle.c)

find_package(OpenGL REQUIRED)
find_package(GLEW REQUIRED)
find_package(GLUT REQUIRED)

include_directories(
    ${PROJECT_SOURCE_DIR}
    ${OPENGL_INCLUDE_DIRS}
    ${GLUT_INCLUDE_DIR}
    ${GLEW_INCLUDE_DIRS}
    )

target_link_libraries(shapes
    ${OPENGL_LIBRARY}
    ${GLUT_LIBRARY}
    ${GLEW_LIBRARIES}
    )

