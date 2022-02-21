#include <stdio.h>

#include <GL/glew.h>
#include <GL/freeglut.h>

// https://open.gl/drawing

#define INFO_LOG_SIZE 512

const GLchar *vertexSource =
    "    #version 330 core\n"
    "    in vec2 position;\n"
    "    void main()\n"
    "    {\n"
    "        gl_Position = vec4(position, 0.0, 1.0);\n"
    "    }\0";

const GLchar *fragmentSource =
    "    #version 330 core\n"
    "    void main()\n"
    "    {\n"
    "        gl_FragColor = vec4(0.1, 0.5, 0.1, 1.0);\n"
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

void initShapes()
{
    // Create Vertex Array Object
    GLuint vao;
    glGenVertexArrays(1, &vao);
    glBindVertexArray(vao);

    // Create a Vertex Buffer Object and copy the vertex data to it
    GLuint vbo;
    glGenBuffers(1, &vbo);

    GLfloat vertices[] = {
        0.0f, 0.7f,
        0.7f, -0.7f,
        -0.7f, -0.7f};

    glBindBuffer(GL_ARRAY_BUFFER, vbo);
    glBufferData(GL_ARRAY_BUFFER, sizeof(vertices), vertices, GL_STATIC_DRAW);

    // Create and compile the vertex shader
    GLuint vertexShader = glCreateShader(GL_VERTEX_SHADER);
    glShaderSource(vertexShader, 1, &vertexSource, NULL);
    glCompileShader(vertexShader);
    checkCompileResult(vertexShader, GL_COMPILE_STATUS, "vertex shader compilation failed");

    // Create and compile the fragment shader
    GLuint fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
    glShaderSource(fragmentShader, 1, &fragmentSource, NULL);
    glCompileShader(fragmentShader);
    checkCompileResult(fragmentShader, GL_COMPILE_STATUS, "fragment shader compilation failed");

    // Link the vertex and fragment shader into a shader program
    GLuint shaderProgram = glCreateProgram();
    glAttachShader(shaderProgram, vertexShader);
    glAttachShader(shaderProgram, fragmentShader);
    glLinkProgram(shaderProgram);
    checkLinkResult(shaderProgram, GL_LINK_STATUS, "shader program link failed");

    glUseProgram(shaderProgram);

    // Specify the layout of the vertex data
    GLint posAttrib = glGetAttribLocation(shaderProgram, "position");
    glEnableVertexAttribArray(posAttrib);
    glVertexAttribPointer(posAttrib, 2, GL_FLOAT, GL_FALSE, 0, 0);
}

void display(void)
{

    // Clear the screen to gray
    glClearColor(0.9f, 0.9f, 0.9f, 1.0f);
    glClear(GL_COLOR_BUFFER_BIT);

    // Draw a triangle from the 3 vertices
    glDrawArrays(GL_TRIANGLES, 0, 3);
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
        printf("Exit shapes sample.\n");
        glutLeaveMainLoop();
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

    return 0;
}
