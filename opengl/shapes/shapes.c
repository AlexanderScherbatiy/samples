#include <stdio.h>
#include <time.h>
#include <stdlib.h>

#include <GL/glew.h>
#include <GL/freeglut.h>

// https://open.gl/drawing

#define SHAPES_NUM 5
#define SHAPE_RADIUS 0.2
#define INFO_LOG_SIZE 512

GLuint vao;
GLuint vbo;
GLuint ebo;

GLuint vertexShader;
GLuint fragmentShader;
GLuint shaderProgram;

const GLchar *vertexSource =
    "    #version 330 core\n"
    "    in vec2 position;\n"
    "    in vec3 color;\n"
    "    out vec3 fragmentColor;\n"
    "    void main()\n"
    "    {\n"
    "        fragmentColor = color;\n"
    "        gl_Position = vec4(position, 0.0, 1.0);\n"
    "    }\0";

const GLchar *fragmentSource =
    "    #version 330 core\n"
    "    in vec3 fragmentColor;"
    "    void main()\n"
    "    {\n"
    "        gl_FragColor = vec4(fragmentColor, 1.0);\n"
    "    }\0";

void checkCompileResult(GLuint shader, GLuint status, const char *msg)
{
    int success;
    char infoLog[INFO_LOG_SIZE];
    glGetShaderiv(shader, GL_COMPILE_STATUS, &success);

    if (!success)
    {
        glGetShaderInfoLog(shader, INFO_LOG_SIZE, NULL, infoLog);
        printf("%s: %s\n", msg, infoLog);
        exit(EXIT_FAILURE);
    }
}

void checkLinkResult(GLuint program, GLuint status, const char *msg)
{
    int success;
    char infoLog[INFO_LOG_SIZE];
    glGetProgramiv(program, GL_LINK_STATUS, &success);

    if (!success)
    {
        glGetProgramInfoLog(program, INFO_LOG_SIZE, NULL, infoLog);
        printf("%s: %s\n", msg, infoLog);
        exit(EXIT_FAILURE);
    }
}

double sampleRand()
{
    return 1.6 * ((double)rand() / (double)RAND_MAX) - 0.8;
}

void initShapes()
{
    srand(time(NULL));

    // Create Vertex Array Object
    glGenVertexArrays(1, &vao);
    glBindVertexArray(vao);

    // Create a Vertex Buffer Object and copy the vertex data to it
    glGenBuffers(1, &vbo);

    // (x, y, red, green, blue)
    // GLfloat vertices[] = {
    //     -0.7f,  0.7f, 0.1, 0.8, 0.1, // top left
    //      0.7f,  0.7f, 0.1, 0.8, 0.1, // top right
    //      0.7f, -0.7f, 0.1, 0.8, 0.1, // bottom right
    //     -0.7f, -0.7f, 0.1, 0.8, 0.1  // bottom left
    // };

    int rows = 4;
    int columns = 5;
    int stride = rows * columns;
    GLfloat vertices[stride * SHAPES_NUM];
    for (int i = 0; i < SHAPES_NUM; i++)
    {
        int index = i * stride;
        // set colors
        for (int j = 0; j < 4; j++)
        {
            int colorIndex = index + columns * j + 2;
            vertices[colorIndex + 0] = 0.1;
            vertices[colorIndex + 1] = 0.8;
            vertices[colorIndex + 2] = 0.1;
        }

        // set coordinates
        double x1 = sampleRand();
        double y1 = sampleRand();
        double x2 = x1 + SHAPE_RADIUS;
        double y2 = y1 + SHAPE_RADIUS;

        vertices[index + 0] = x1;
        vertices[index + 1] = y1;

        index += columns;
        vertices[index + 0] = x2;
        vertices[index + 1] = y1;

        index += columns;
        vertices[index + 0] = x2;
        vertices[index + 1] = y2;

        index += columns;
        vertices[index + 0] = x1;
        vertices[index + 1] = y2;
    }

    glBindBuffer(GL_ARRAY_BUFFER, vbo);
    glBufferData(GL_ARRAY_BUFFER, sizeof(vertices), vertices, GL_STATIC_DRAW);

    // GLuint elements[] = {
    //     0, 1, 2, // the fist triangle
    //     0, 3, 2  // the second triangle
    // };

    GLuint elements[6 * SHAPES_NUM];
    for (int i = 0; i < SHAPES_NUM; i++)
    {
        int index = 6 * i;
        int vertex = 4 * i;
        elements[index + 0] = vertex + 0;
        elements[index + 1] = vertex + 1;
        elements[index + 2] = vertex + 2;
        elements[index + 3] = vertex + 0;
        elements[index + 4] = vertex + 3;
        elements[index + 5] = vertex + 2;
    }

    glGenBuffers(1, &ebo);
    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo);
    glBufferData(GL_ELEMENT_ARRAY_BUFFER,
                 sizeof(elements), elements, GL_STATIC_DRAW);

    // Create and compile the vertex shader
    vertexShader = glCreateShader(GL_VERTEX_SHADER);
    glShaderSource(vertexShader, 1, &vertexSource, NULL);
    glCompileShader(vertexShader);
    checkCompileResult(vertexShader, GL_COMPILE_STATUS, "vertex shader compilation failed");

    // Create and compile the fragment shader
    fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
    glShaderSource(fragmentShader, 1, &fragmentSource, NULL);
    glCompileShader(fragmentShader);
    checkCompileResult(fragmentShader, GL_COMPILE_STATUS, "fragment shader compilation failed");

    // Link the vertex and fragment shader into a shader program
    shaderProgram = glCreateProgram();
    glAttachShader(shaderProgram, vertexShader);
    glAttachShader(shaderProgram, fragmentShader);
    glLinkProgram(shaderProgram);
    checkLinkResult(shaderProgram, GL_LINK_STATUS, "shader program link failed");

    glUseProgram(shaderProgram);

    // Specify the layout of the vertex data
    GLint posAttrib = glGetAttribLocation(shaderProgram, "position");
    glEnableVertexAttribArray(posAttrib);
    glVertexAttribPointer(posAttrib, 2, GL_FLOAT, GL_FALSE,
                          5 * sizeof(float), 0);

    GLint colAttrib = glGetAttribLocation(shaderProgram, "color");
    glEnableVertexAttribArray(colAttrib);
    glVertexAttribPointer(colAttrib, 3, GL_FLOAT, GL_FALSE,
                          5 * sizeof(float), (void *)(2 * sizeof(float)));
}

void deleteShapes()
{
    glDeleteVertexArrays(1, &vao);
    glDeleteBuffers(1, &vbo);

    glDeleteProgram(shaderProgram);
    glDeleteShader(fragmentShader);
    glDeleteShader(vertexShader);
}

void exitApp()
{
    glutLeaveMainLoop();
    deleteShapes();
}

void display(void)
{
    glClearColor(0.9f, 0.9f, 0.9f, 1.0f);
    glClear(GL_COLOR_BUFFER_BIT);

    glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
    glDrawElements(GL_TRIANGLES, 6 * SHAPES_NUM, GL_UNSIGNED_INT, 0);
    glutSwapBuffers();
}

void reshape(int w, int h)
{
    glViewport(0, 0, (GLsizei)w, (GLsizei)h);
}

void key(unsigned char key, int x, int y)
{
    switch (key)
    {
    case 27: // ESCAPE key
        exitApp();
        break;
    }
}

int main(int argc, char *argv[])
{
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_RGBA);
    glutInitWindowSize(500, 500);

    glutInitContextVersion(3, 3);
    glutInitContextFlags(GLUT_FORWARD_COMPATIBLE | GLUT_DEBUG);
    glutInitContextProfile(GLUT_CORE_PROFILE);

    glutCreateWindow("OpenGL Shapes");

    if (glewInit())
    {
        printf("Error during GLEW initialization\n");
        exit(EXIT_FAILURE);
    }

    glutDisplayFunc(display);
    glutReshapeFunc(reshape);
    glutKeyboardFunc(key);

    initShapes();

    glutMainLoop();

    deleteShapes();

    return 0;
}
