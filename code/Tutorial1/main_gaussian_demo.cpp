//gcc -Wall -lglut -lGL `pkg-config --cflags glfw3` Tutorial1/main_gaussian_demo.cpp -o main_gaussian_demo `pkg-config --static --libs glfw3` -lGLU -lGL -lglut
//./main_gaussian_demo

#define _USE_MATH_DEFINES // M_PI constant

#include <GLFW/glfw3.h>
#include <stdlib.h>
#include <stdio.h>
#include <math.h>
// Window size
const int WINDOWS_WIDTH = 1280;
const int WINDOWS_HEIGHT = 720;

// typedef struct{
//   GLfloat x, y, z; //position
//   GLfloat r, g, b, a; //color and alpha channels
// } Vertex;
//
// void drawPoint(Vertex v1, GLfloat size);
// void drawPointsDemo(int width, int height);

typedef struct {
  GLfloat x, y, z;
} Data;

// Callback functions and helper functions for 3D plot
void framebuffer_size_callback(GLFWwindow* window, int width,
int height);
void gaussianDemo(float sigma);
void draw2DHeatMap(const Data *data, int num_points);
void drawOrigin();

// Initialize parameters for rendering the Gaussian distribution
float sigma = 0.1f;
float sign = 1.0f;
float step_size = 0.01f;
// Global variables to track mouse button and the angles of rotation or zoom level
GLboolean locked = GL_FALSE;
GLfloat alpha=210.0f, beta=-70.0f, zoom=2.0f;

// void drawPoint(Vertex v1, GLfloat size){
//   glPointSize(size);
//   glBegin(GL_POINTS);
//   glColor4f(v1.r, v1.g, v1.b, v1.a);
//   glVertex3f(v1.x, v1.y, v1.z);
//   glEnd();
// }
//
// void drawPointsDemo(int width, int height){
//   GLfloat size=5.0f;
//   for(GLfloat x = 0.0f; x<=1.0f; x+=0.2f, size+=5)
//   {
//     Vertex v1 = {x, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f};
//     drawPoint(v1, size);
//   }
// }

void drawOrigin(){
  glLineWidth(4.0f);
  glBegin(GL_LINES);
  float transparency = 0.5f;
  //draw a red line for the x-axis
  glColor4f(1.0f, 0.0f, 0.0f, transparency);
  glVertex3f(0.0f, 0.0f, 0.0f);
  glColor4f(1.0f, 0.0f, 0.0f, transparency);
  glVertex3f(0.3f, 0.0f, 0.0f);
  //draw a green line for the y-axis
  glColor4f(0.0f, 1.0f, 0.0f, transparency);
  glVertex3f(0.0f, 0.0f, 0.0f);
  glColor4f(0.0f, 1.0f, 0.0f, transparency);
  glVertex3f(0.0f, 0.0f, 0.3f);
  //draw a blue line for the z-axis
  glColor4f(0.0f, 0.0f, 1.0f, transparency);
  glVertex3f(0.0f, 0.0f, 0.0f);
  glColor4f(0.0f, 0.0f, 1.0f, transparency);
  glVertex3f(0.0f, 0.3f, 0.0f);
  glEnd();
}

void gaussianDemo(float sigma){
  const int grid_x = 400;
  const int grid_y = 400;
  const int num_points = grid_x*grid_y;
  Data *data=(Data*)malloc(sizeof(Data)*num_points);
  int data_counter=0;

  for(int x = -grid_x/2; x<grid_x/2; x+=1){
    for(int y = -grid_y/2; y<grid_y/2; y+=1){
      float x_data = 2.0f*x/grid_x;
      float y_data = 2.0f*y/grid_y;
      //compute the height z based on a
      //2D Gaussian function.
      float z_data = exp(-0.5f*(x_data*x_data)/(sigma*sigma)-0.5f*(y_data*y_data)/(sigma*sigma))/
      (sigma*sigma*2.0f*M_PI);
      data[data_counter].x = x_data;
      data[data_counter].y = y_data;
      data[data_counter].z = z_data;
      data_counter++;
    }
  }
  //visualize the result using a 2D heat map
  draw2DHeatMap(data, num_points);
  free(data);
}

//Generate visualization in Heapmap
void draw2DHeatMap(const Data *data, int num_points){
  glPointSize(3.0f);
  glBegin(GL_POINTS);
  float transparency = 0.25f;
  //locate the maximum and minimum values in the dataset
  float max_value=-999.9f;
  float min_value=999.9f;
  for(int i=0; i<num_points; i++){
    Data d = data[i];
    if(d.z > max_value)
    max_value = d.z;
    if(d.z < min_value)
    min_value = d.z;
  }
  float halfmax = (max_value + min_value) / 2;
  //display the result
  for(int i = 0; i<num_points; i++){
    Data d = data[i];
    float value = d.z;
    float b = 1.0f - value/halfmax;
    float r = value/halfmax - 1.0f;
    if(b < 0)
      b=0;
    if(r < 0)
      r=0;
    float g = 1.0f - b - r;
    glColor4f(r, g, b, transparency);
    glVertex3f(d.x, d.y, d.z);
  }
  glEnd();
}

// Set up the camera parameters for perspective rendering
void framebuffer_size_callback(GLFWwindow* window, int width, int height){
  const float fovY = 45.0f;
  const float front = 0.1f;
  const float back = 128.0f;
  float ratio = 1.0f;
  if (height > 0)
    ratio = (float) width / (float) height;
  glViewport(0, 0, width, height);
  glMatrixMode(GL_PROJECTION);
  glLoadIdentity();
  // gluPerspective(fovY, ratio, front, back);
  // Following code equals to gluPerspective()
  const double DEG2RAD = 3.14159265 / 180;
  // tangent of half fovY
  double tangent = tan(fovY/2 * DEG2RAD);
  // half height of near plane
  double height_f = front * tangent;
  // half width of near plane
  double width_f = height_f * ratio;
  //Create the projection matrix based on the near clipping
  //plane and the location of the corners
  glFrustum(-width_f, width_f, -height_f, height_f, front, back);
}

void key_callback(GLFWwindow* window, int key, int scancode,int action, int mods){
  // ignore any event other than the key press event
  if (action != GLFW_PRESS)
    return;
  switch (key){
    case GLFW_KEY_ESCAPE:
      glfwSetWindowShouldClose(window, GL_TRUE);
      break;
    case GLFW_KEY_SPACE:
      glfwSetWindowShouldClose(window, GL_TRUE);
      break;
    // If the direction keys (up, down, left, and right) are pressed, update the variables that
    // control the angles of rotation for the rendered object
    case GLFW_KEY_LEFT:
      alpha += 5.0f;
      break;
    case GLFW_KEY_RIGHT:
      alpha -= 5.0f;
      break;
    case GLFW_KEY_UP:
      beta -= 5.0f;
      break;
    case GLFW_KEY_DOWN:
      beta += 5.0f;
      break;
    case GLFW_KEY_PAGE_UP:
      zoom -= 0.25f;
      if (zoom < 0.0f)
        zoom = 0.0f;
        break;
    case GLFW_KEY_PAGE_DOWN:
      zoom += 0.25f;
      break;
      default:
      break;
  }
  printf("alpha:%d \n",alpha);
}

void mouse_button_callback(GLFWwindow* window, int button,int action, int mods){
  // ignore all mouse input except left click
  if (button != GLFW_MOUSE_BUTTON_LEFT){
    return;
  }
  if (action == GLFW_PRESS){
    glfwSetInputMode(window, GLFW_CURSOR,GLFW_CURSOR_DISABLED);
    locked = GL_TRUE;
  }else{
    locked = GL_FALSE;
    glfwSetInputMode(window, GLFW_CURSOR,GLFW_CURSOR_NORMAL);
  }
}

void cursor_position_callback(GLFWwindow* window, double x,double y){
  int cursorX, cursorY;
  //if the mouse button is pressed
  if (locked)
  {
  alpha += (GLfloat) (x - cursorX) / 2.0f;
  beta += (GLfloat) (y - cursorY) / 2.0f;
  }
  //update the cursor position
  cursorX = (int) x;
  cursorY = (int) y;
}

void scroll_callback(GLFWwindow* window, double x, double y){
  zoom += (float) y / 4.0f;
  if (zoom < 0.0f){
    zoom = 0.0f;
  }
}

int main(void)
{
  GLFWwindow* window;
  int width, height;
  if (!glfwInit()){
    exit(EXIT_FAILURE);
  }
  window = glfwCreateWindow(WINDOWS_WIDTH, WINDOWS_HEIGHT,"Chapter 3: 3D Data Plotting", NULL, NULL);
  if (!window){
    glfwTerminate();
    exit(EXIT_FAILURE);
  }
  glfwMakeContextCurrent(window);
  glfwSwapInterval(1);
  // NEW: Callbakc functions
  glfwGetFramebufferSize(window, &width, &height);
  framebuffer_size_callback(window, width, height);
  //enable anti-aliasing
  glEnable(GL_BLEND);
  //smooth the points
  glEnable(GL_LINE_SMOOTH);
  //smooth the lines
  glEnable(GL_POINT_SMOOTH);
  glHint(GL_LINE_SMOOTH_HINT, GL_NICEST);
  glHint(GL_POINT_SMOOTH_HINT, GL_NICEST);
  //needed for alpha blending
  glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
  glEnable(GL_ALPHA_TEST) ;
  //keyboard input callback
  glfwSetKeyCallback(window, key_callback);
  //framebuffer size callback
  glfwSetFramebufferSizeCallback(window, framebuffer_size_callback);
  //mouse button callback
  glfwSetMouseButtonCallback(window, mouse_button_callback);
  //mouse movement callback
  glfwSetCursorPosCallback(window, cursor_position_callback);
  //mouse scroll callback
  glfwSetScrollCallback(window, scroll_callback);

  while (!glfwWindowShouldClose(window))
  {
    // //keyboard input callback
    // glfwSetKeyCallback(window, key_callback);
    // //framebuffer size callback
    // glfwSetFramebufferSizeCallback(window, framebuffer_size_callback);
    // //mouse button callback
    // glfwSetMouseButtonCallback(window, mouse_button_callback);
    // //mouse movement callback
    // glfwSetCursorPosCallback(window, cursor_position_callback);
    // //mouse scroll callback
    // glfwSetScrollCallback(window, scroll_callback);

    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
    // Specify the matrix mode as GL_MODELVIEW to allow subsequent matrix operations to
    // be applied to the MODELVIEW matrix stack
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    glTranslatef(0.0, 0.0, -2.0);
    // rotate by beta degrees around the x-axis
    glRotatef(beta, 1.0, 0.0, 0.0);
    // rotate by alpha degrees around the z-axis
    glRotatef(alpha, 0.0, 0.0, 1.0);

    drawOrigin();
    sigma=sigma+sign*step_size;
    if(sigma>1.0f){
      sign = -1.0f;
    }
    if(sigma<0.1){
      sign = 1.0f;
    }
    gaussianDemo(sigma);
    glfwSwapBuffers(window);
    glfwPollEvents();
  }
  glfwDestroyWindow(window);
  glfwTerminate();
  exit(EXIT_SUCCESS);
}
