#include <iostream>
#include <GL/glew.h>
#include <GL/gl.h>
#include <GL/glut.h>
#include <GL/freeglut.h>

#include <math.h>

#include "../Common/shaders_utilities.hpp"
#define GLM_FORCE_RADIANS
// Include GLM
#include <glm/glm.hpp>
#include <glm/gtc/matrix_transform.hpp>
using namespace glm;

using namespace std;

// Position de la lumière : elle sera fixe dans la scéne
// cf ClavierClassique pour voir comment la déplacer
GLfloat lumiere[] = {0,5.0,0.0};

// Les sommets avec glDrawElements
static GLfloat coordonnees[] = {
  0,0,0, //0
  1,0,0, //1
  1,1,0, //2
  0,1,0, //3

  1,0,0, //1 4
  1,1,0, //2 5
  1,1,1, //6 6
  1,0,1, //5 7

  0,0,1, //4 8
  1,0,1, //5 9
  1,1,1, //6 10
  0,1,1, //7 11

  0,0,0, //0 12
  0,0,1, //4 13
  0,1,1, //7 14
  0,1,0, //3 15

  0,1,0, //3 16
  0,1,1, //7 17
  1,1,1, //6 18
  1,1,0, //2 19

  0,0,0, //0 20
  0,0,1, //4 21
  1,0,1, //5 22
  1,0,0  //1 23

};

// Des couleurs ...
static GLfloat couleurs[] = {
  1,1,1,
  0,1,1,
  0,0,1,
  0,1,0,

  1,1,1,
  0,1,1,
  0,0,1,
  0,1,0,

  1,1,1,
  0,1,1,
  0,0,1,
  0,1,0,

  1,1,1,
  0,1,1,
  0,0,1,
  0,1,0,

  1,1,1,
  0,1,1,
  0,0,1,
  0,1,0,

  1,1,1,
  0,1,1,
  0,0,1,
  0,1,0,
};

// Les normales calculées à la main
// comme le sommet est représenté autant de fois que de facettes auxquelles
// il contribue, la normale est calculée par rapport à la facette à laquelle le sommet contribue.
static GLfloat normales[] = {
  0,0,-1,
  0,0,-1,
  0,0,-1,
  0,0,-1,
  
  1,0,0,
  1,0,0,
  1,0,0,
  1,0,0,
  
  0,0,1,
  0,0,1,
  0,0,1,
  0,0,1,

  -1,0,0,
  -1,0,0,
  -1,0,0,
  -1,0,0,

  0,1,0,
  0,1,0,
  0,1,0,
  0,1,0,

  0,-1,0,
  0,-1,0,
  0,-1,0,
  0,-1,0
};


static GLuint indices[] = {
  0,1,2,
  2,3,0,
  
  4,5,6,
  6,7,4,
  
  8,9,10,
  10,11,8,

  12,13,14,
  14,15,12,

  16,17,18,
  18,19,16,

  20,21,22,
  22,23,20
  
};

float stepTrans=0.1;
int mouseXOld, mouseYOld;
bool leftbutton = false;
bool rightbutton = false;
bool middlebutton = false;

GLuint vboID[4];
GLuint programID;
GLuint vaoID;

// les identifiants pour envoyer à la carte graphique les matrices et la position de la lumière
GLint PVM_ID;

glm::mat4 Projection;
glm::mat4 View;
glm::mat4 Model;
glm::mat4 PVM;

glm::mat4 translation;
glm::mat4 trans_initial;
glm::mat4 rotation;


GLfloat ambiant[4] = {0.2,0.2,0.2,1.0};

GLint ambiant_ID;

void init() {

  glClearColor(0.0f, 0.0f, 0.5f, 0.0f);

  glClearDepth(1.0);
  glEnable(GL_DEPTH_TEST);

  programID = LoadShaders( "../Shaders/AmbiantVertexShader.vert", "../Shaders/AmbiantFragmentShader.frag" );
  
  glUseProgram(programID);

  glGenVertexArrays(1, &vaoID);
  glBindVertexArray(vaoID);

  glGenBuffers(4,vboID);

  glBindBuffer(GL_ARRAY_BUFFER, vboID[0]);
  glBufferData(GL_ARRAY_BUFFER,3*6*4*sizeof(float),coordonnees,GL_STATIC_DRAW);
  glVertexAttribPointer(0,3,GL_FLOAT,GL_FALSE,0,(void*)0);
  glEnableVertexAttribArray(0);
  
  glBindBuffer(GL_ARRAY_BUFFER, vboID[1]);
  glBufferData(GL_ARRAY_BUFFER,3*6*4*sizeof(float),couleurs,GL_STATIC_DRAW);
  glVertexAttribPointer(1,3,GL_FLOAT,GL_FALSE,0,(void*)0);
  glEnableVertexAttribArray(1);
  
  glBindBuffer(GL_ARRAY_BUFFER, vboID[2]);
  glBufferData(GL_ARRAY_BUFFER,3*6*4*sizeof(float),normales,GL_STATIC_DRAW);
  glVertexAttribPointer(2,3,GL_FLOAT,GL_FALSE,0,(void*)0);
  glEnableVertexAttribArray(2);

  glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboID[3]);
  glBufferData(GL_ELEMENT_ARRAY_BUFFER,3*12*sizeof(GLuint),indices,GL_STATIC_DRAW);
  
  

  // Génération des identifiants pour les variables Uniform vers le vectex shader
  PVM_ID = glGetUniformLocation(programID, "PVM");

  ambiant_ID = glGetUniformLocation(programID, "ambiant");
  
  Projection = glm::perspective(70.0f, 4.0f / 3.0f, 0.1f, 100.0f);
  View = glm::lookAt(glm::vec3(0,0,-5.0), glm::vec3(0,0,0), glm::vec3(0,1,0));
  
  trans_initial = glm::translate(glm::mat4(1.0f), glm::vec3(-0.5,-0.5,-0.5));
  
  Model = glm::mat4(1.0f);
  
  rotation = glm::mat4(1.0f);
  
  translation = glm::mat4(1.0f);

}

void Display(void) {
  glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);


  Model = translation * rotation * trans_initial;

  PVM =  Projection * View * Model;


  glUniformMatrix4fv(PVM_ID, 1, GL_FALSE, &PVM[0][0]);

  glUniform4fv(ambiant_ID,1,ambiant);
  
  glDrawElements(GL_TRIANGLES, 3*12, GL_UNSIGNED_INT, NULL);   
  glutSwapBuffers();
}

void Idle()
{
}

void ClavierClassique(unsigned char key, int x, int y)
{
  switch(key) {	
  case 'A':
    for (int i=0; i<3; i++)
      ambiant[i]+=0.1;
    break;
  case 'a':
    for (int i=0; i<3; i++)
      ambiant[i]-=0.1;
    break;    
  case 0x1B:
    exit(0);
  }
  glutPostRedisplay();  
}

void ClavierSpecial(int key, int x, int y)
{
  switch(key) {
  case GLUT_KEY_UP:
    translation = glm::translate(translation, glm::vec3(0.f, stepTrans, 0.f));
    break;
  case GLUT_KEY_DOWN:
    translation = glm::translate(translation, glm::vec3(0.f, -stepTrans, 0.f));
    break;
  case GLUT_KEY_RIGHT:
    translation = glm::translate(translation, glm::vec3(stepTrans, 0.f, 0.f));
    break;
  case GLUT_KEY_LEFT:
    translation = glm::translate(translation, glm::vec3(-stepTrans, 0.f, 0.f));
    break;
  case GLUT_KEY_PAGE_UP:
    translation = glm::translate(translation, glm::vec3(0.f, 0.f, stepTrans));
    break;
  case GLUT_KEY_PAGE_DOWN:
    translation = glm::translate(translation, glm::vec3(0.f, 0.f, -stepTrans));
    break;
  }
  glutPostRedisplay();  
}

void Souris(int bouton, int etat, int x, int y)
{	  
    switch (bouton) {
      case GLUT_LEFT_BUTTON :
	if (etat==GLUT_DOWN) {
	  leftbutton=true;
	  mouseXOld = x;
	  mouseYOld = y;
	}
	else
	leftbutton=false;
	break;
      case GLUT_MIDDLE_BUTTON :
	if (etat==GLUT_DOWN) {
	  middlebutton=true;
	  mouseXOld = x;
	  mouseYOld = y;
	}
	else
	middlebutton=false;
	break;
      case GLUT_RIGHT_BUTTON:
	if (etat==GLUT_DOWN) {
	  rightbutton=true;
	  mouseXOld = x;
	  mouseYOld = y;
	}
	else
	  rightbutton=false;
	break;
    }
    glutPostRedisplay();  
}  
void Motion (int x, int y)
{
   if (middlebutton) {
     if (abs(y-mouseYOld) > abs(x-mouseXOld)) 
       translation = glm::translate(translation, glm::vec3(0.f, 0.f, -(y-mouseYOld)*stepTrans/10));
     else 
       translation = glm::translate(translation, glm::vec3(0.f, 0.f, -(x-mouseXOld)*stepTrans/10));
     mouseXOld=x;
     mouseYOld=y;
   }
   else if (rightbutton) {
     translation = glm::translate(translation, glm::vec3(-(x-mouseXOld)*stepTrans/10, -(y-mouseYOld)*stepTrans/10, 0.f));
     mouseXOld=x;
     mouseYOld=y;
   }
   
   if (leftbutton){
     rotation = glm::rotate(rotation, (x-mouseXOld)*stepTrans/10, glm::vec3(0.f, 1.f, 0.f));
     rotation = glm::rotate(rotation, -(y-mouseYOld)*stepTrans/10, glm::vec3(1.f, 0.f, 0.f));
     mouseXOld=x;
     mouseYOld=y;
   }
   glutPostRedisplay();     
}

int main(int argc, char** argv)
{

  glutInit (&argc,argv) ;
  glutInitContextVersion(3, 3);
  glutInitContextProfile(GLUT_CORE_PROFILE);
  glutInitContextFlags(GLUT_FORWARD_COMPATIBLE);
  glutInitDisplayMode (GLUT_DOUBLE | GLUT_RGBA | GLUT_DEPTH) ;
  glutInitWindowSize (500,500) ;
  glutInitWindowPosition (100, 100) ;
  glutCreateWindow ("GLUT") ;
  glewExperimental = GL_TRUE;   
  GLenum err = glewInit();

  init();
  glutDisplayFunc(Display);

  glutKeyboardFunc(ClavierClassique);
  glutSpecialFunc(ClavierSpecial);
  glutMouseFunc(Souris);
  glutMotionFunc(Motion);

  glutMainLoop () ;
  return 0 ;
  
}
