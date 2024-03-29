#include <stdio.h>
#include <time.h>
#include <stdlib.h>

#include <GL/glew.h>
#include <GL/freeglut.h>

// https://open.gl/drawing

#define SHAPES_NUM 50
#define WIN_WIDTH 480
#define WIN_HEIGHT 800
#define INFO_LOG_SIZE 512

#define ANIMATION_ENABLED GL_TRUE
#define ANIMATION_INTERVAL 16 // milliseconds per frame

struct Bubble
{
    double x;
    double y;
    double r;
    double speed;
    double red;
    double green;
    double blue;
};

struct Bubble bubbles[SHAPES_NUM];

GLuint vao;
GLuint vbo;
GLuint ebo;

GLuint vertexShader;
GLuint fragmentShader;
GLuint shaderProgram;

GLfloat vertices[4 * 5 * SHAPES_NUM];
GLuint elements[6 * SHAPES_NUM];

const GLchar *vertexSource =
    "    #version 330 core\n"
    "    in vec2 position;\n"
    "    in vec3 color;\n"
    "    uniform vec2 translate;\n"
    "    uniform mat4 rotation;\n"
    "    out vec3 fragmentColor;\n"
    "    void main()\n"
    "    {\n"
    "        fragmentColor = color;\n"
    "        vec2 nextPos = position + translate;\n"
    "        gl_Position = rotation * vec4(nextPos, 0.0, 1.0);\n"
    "    }\0";

const GLchar *fragmentSource =
    "    #version 330 core\n"
    "    in vec3 fragmentColor;"
    "    void main()\n"
    "    {\n"
    "        gl_FragColor = vec4(fragmentColor, 1.0);\n"
    "    }\0";

double scaleToNDC(double value, double size)
{
    return (2.0 * (value / size)) - 1.0;
}

double rangeRandom(double min, double max)
{
    return min + (max - min) * ((double)rand() / (double)RAND_MAX);
}

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

void initVertices()
{

    // [(x, y), (red, green, blue)]
    // GLfloat vertices[] = {
    //     -0.7f,  0.7f, 0.1f, 0.8f, 0.1f, // top left
    //      0.7f,  0.7f, 0.1f, 0.8f, 0.1f, // top right
    //      0.7f, -0.7f, 0.1f, 0.8f, 0.1f, // bottom right
    //     -0.7f, -0.7f, 0.1f, 0.8f, 0.1f  // bottom left
    // };

    int rows = 4;
    int columns = 5;
    int stride = rows * columns;
    for (int i = 0; i < SHAPES_NUM; i++)
    {
        struct Bubble bubble = bubbles[i];
        int index = i * stride;

        // set colors
        for (int j = 0; j < 4; j++)
        {
            int colorIndex = index + columns * j + 2;
            vertices[colorIndex + 0] = bubble.red;
            vertices[colorIndex + 1] = bubble.green;
            vertices[colorIndex + 2] = bubble.blue;
        }

        // set coordinates
        double x1 = scaleToNDC(bubble.x, WIN_WIDTH);
        double y1 = scaleToNDC(bubble.y, WIN_HEIGHT);
        double rx = bubble.r / WIN_WIDTH;
        double ry = bubble.r / WIN_HEIGHT;
        double x2 = x1 + rx;
        double y2 = y1 + ry;

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
}

void initShapes()
{
    srand(time(NULL));

    for (int i = 0; i < SHAPES_NUM; i++)
    {
        struct Bubble bubble;
        bubble.x = rangeRandom(0, WIN_WIDTH);
        bubble.y = rangeRandom(0, WIN_WIDTH);
        bubble.r = 50;
        bubble.speed = rangeRandom(2.0, 8.0);
        bubble.red = 0.1;
        bubble.green = 0.1;
        bubble.blue = 0.8;
        bubbles[i] = bubble;
    }

    // Create Vertex Array Object
    glGenVertexArrays(1, &vao);
    glBindVertexArray(vao);

    // Create a Vertex Buffer Object and copy the vertex data to it
    glGenBuffers(1, &vbo);

    initVertices();
    glBindBuffer(GL_ARRAY_BUFFER, vbo);
    glBufferData(GL_ARRAY_BUFFER, sizeof(vertices), vertices, GL_STREAM_DRAW);

    // GLuint elements[] = {
    //     0, 1, 2, // the fist triangle
    //     0, 3, 2  // the second triangle
    // };

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
                 sizeof(elements), elements, GL_STREAM_DRAW);

    // Translate(0, WIN_HEIGHT)
    GLfloat translateVector[] = {
        0.0f, scaleToNDC(WIN_HEIGHT, WIN_HEIGHT)};

    // Rotate(90)
    // (0 -1)
    // (1  0)
    GLfloat rotationMatrix[] = {
        0.0f, -1.0f, 0.0f, 0.0f,
        1.0f, 0.0f, 0.0f, 0.0f,
        0.0f, 0.0f, 1.0f, 0.0f,
        0.0f, 0.0f, 0.0f, 1.0f};

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

    GLint translateAttrib = glGetUniformLocation(shaderProgram, "translate");
    glUniform2fv(translateAttrib, 1, translateVector);

    GLint rotateAttrib = glGetUniformLocation(shaderProgram, "rotation");
    glUniformMatrix4fv(rotateAttrib, 1, GL_FALSE, rotationMatrix);
}

void updateShapes()
{
    for (int i = 0; i < SHAPES_NUM; i++)
    {
        bubbles[i].y -= bubbles[i].speed;
        if (bubbles[i].y < 0)
        {
            bubbles[i].y = WIN_WIDTH;
        }
    }

    initVertices();
    glBindBuffer(GL_ARRAY_BUFFER, vbo);
    glBufferSubData(GL_ARRAY_BUFFER, 0, sizeof(vertices), vertices);
    glBindBuffer(GL_ARRAY_BUFFER, 0);
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

void animation(int id)
{
    updateShapes();
    glutPostRedisplay();
}

void display(void)
{

    if (ANIMATION_ENABLED)
    {
        glutTimerFunc(ANIMATION_INTERVAL, animation, 0);
    }

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
    case 'm':
        updateShapes();
        glutPostRedisplay();
        break;
    case 27: // ESCAPE key
        exitApp();
        break;
    }
}

int main(int argc, char *argv[])
{
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_RGBA);
    // The screen is rotated on 90 degree
    glutInitWindowSize(WIN_HEIGHT, WIN_WIDTH);

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
