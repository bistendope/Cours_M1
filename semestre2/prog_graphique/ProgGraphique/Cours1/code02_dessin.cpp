#include <iostream>
#include <GL/glew.h>
#include <GL/gl.h>
#include <GL/glut.h>
#include <GL/freeglut.h>
//#include <GL/glu.h>
#include <math.h>

#include "../Common/shaders_utilities.hpp"

#include <glm/glm.hpp>
#include <glm/gtc/matrix_transform.hpp>

using namespace std;

static GLfloat coordonnees[] = {
  0,1,0,
  -1,0,0,
  1,0,0,
  0,-1,0
};


// Le sommet est défini par un attribut supplémentaire : la couleur en RGB
static GLfloat couleurs[] = {
  1,0,0, // rouge
  0,1,0, // vert
  0,0,1, // bleu
  1,1,0 //jaune
};

// 2 attributs donc deux VBO pour les gérer donc deux identifiants
GLuint vboid[2];

GLuint programID;
GLuint vaoID;

void init() {
  glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
  glClearDepth(1.0); 
  glEnable(GL_DEPTH_TEST);
  
  glGenVertexArrays(1, &vaoID);
  glBindVertexArray(vaoID);

  // Deux nouveaux shaders 
  programID = LoadShaders( "../Shaders/ColorVertexShader.vert", "../Shaders/ColorFragShader.frag" );

  // Création de deux identifiants (un tableau) pour les deux VBO
  glGenBuffers(2,vboid);

  // 1er identifiant pour le VBO des coordonnées + chargement des données etc
  glBindBuffer(GL_ARRAY_BUFFER, vboid[0]);
  glBufferData(GL_ARRAY_BUFFER,3*4*sizeof(float),coordonnees,GL_STATIC_DRAW);
  glVertexAttribPointer(0,3,GL_FLOAT,GL_FALSE,0,(void*)0);
  glEnableVertexAttribArray(0); // layout(0) dans le shader


  
  // 2eme identifiant pour le VBO des couleurs +  chargement des données
  glBindBuffer(GL_ARRAY_BUFFER, vboid[1]);
  glBufferData(GL_ARRAY_BUFFER,3*4*sizeof(float),couleurs,GL_STATIC_DRAW);
  glVertexAttribPointer(1,3,GL_FLOAT,GL_FALSE,0,(void*)0);
  glEnableVertexAttribArray(1); // layout(1) dans le shader
  
}

void Display(void) {

  glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
  
  glUseProgram(programID);
  
  glDrawArrays(GL_TRIANGLE_STRIP, 0, 4); 

  glutSwapBuffers();
}


int main(int argc, char** argv)
{

  glutInit (&argc,argv) ;
  
  glutInitContextVersion(3, 3);
  glutInitContextFlags(GLUT_FORWARD_COMPATIBLE);
  glutInitContextProfile(GLUT_CORE_PROFILE);
    
  glutInitDisplayMode (GLUT_DOUBLE | GLUT_RGBA) ;
  glutInitWindowSize (500,500) ;
  glutInitWindowPosition (100, 100) ;
  glutCreateWindow ("GLUT") ;
  glewExperimental = GL_TRUE; 
  
  GLenum err = glewInit();
  
  init();
  glutDisplayFunc(Display);
  glutMainLoop () ;
  return 0 ;
  
}
