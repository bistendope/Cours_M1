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
  0,0,0,
  0.5,0,0,
  0.5,0.5,0,
  0,0.5,0,  
  0,0,0.5,
  0.5,0,0.5,
  0.5,0.5,0.5,
  0,0.5,0.5,  
};

static GLfloat couleurs[] = {
  1,1,1,
  0,1,1,
  0,0,1,
  0,1,0,
  1,1,1,
  0,1,1,
  0,0,1,
  0,1,0
};

GLuint indices[] = {
  3,0,2,
  0,2,1,
  1,2,6,
  1,6,5,
  6,5,7,
  5,7,4,
  7,4,3,
  4,3,0,
  3,2,7,
  2,7,6,
  0,1,4,
  1,4,5
};



GLuint vboid[3];
GLuint programID;
GLuint vaoID;

float stepTrans=0.1;
int mouseXOld, mouseYOld;
bool leftbutton = false;
bool rightbutton = false;
bool middlebutton = false;

// Un identifiant encore ....
GLuint MatrixID;

// Des matrices ...
// On va utiliser pour les construire et les manipuler glm (OpenGL Mathematics)
glm::mat4 Projection; // une matrice de projection
glm::mat4 View; // une matrice de vue ?
glm::mat4 Model; // une matrice Model ?
glm::mat4 MVP; // Et le produit des trois

glm::mat4 translation; // une matrice de translation qui va nous permettre de déplacer le cube
glm::mat4 trans_initial; // une matrice de translation pour centrer le cube
glm::mat4 rotation; // une matrice pour construire une rotation appliquée sur le cube

void init() {
  glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
  glClearDepth(1.0);
  glEnable(GL_DEPTH_TEST);

  glGenVertexArrays(1, &vaoID);
  glBindVertexArray(vaoID);
  
  programID = LoadShaders( "../Shaders/TransformVertexShader.vert", "../Shaders/ColorFragShader.frag" );

  // Petite fonction pour changer le mode de représentation
  glPolygonMode(GL_FRONT_AND_BACK,GL_LINE);
  

  glGenBuffers(3,vboid);
  glBindBuffer(GL_ARRAY_BUFFER, vboid[0]);
  glBufferData(GL_ARRAY_BUFFER,3*8*sizeof(float),coordonnees,GL_STATIC_DRAW);
  glVertexAttribPointer(0,3,GL_FLOAT,GL_FALSE,0,(void*)0);
  glEnableVertexAttribArray(0);
  
  
  
  glBindBuffer(GL_ARRAY_BUFFER, vboid[1]);
  glBufferData(GL_ARRAY_BUFFER,3*8*sizeof(float),couleurs,GL_STATIC_DRAW);
  glVertexAttribPointer(1,3,GL_FLOAT,GL_FALSE,0,(void*)0);
  glEnableVertexAttribArray(1);
  
  glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboid[2]);
  glBufferData(GL_ELEMENT_ARRAY_BUFFER,3*12*sizeof(GLuint),indices,GL_STATIC_DRAW);
  
  
  
  // Il faut transmettre les informations à la carte graphique et au vertex shader
  // D'où cet identifiant (regarder dans TransformVertexShader et la déclaration d'une variable "uniform" MVP)
  MatrixID = glGetUniformLocation(programID, "MVP");
  
  // Création d'une matrice de translation pour déplacer le cube de -0.25 en X, Y et Z
  trans_initial = glm::translate(glm::mat4(1.0f), glm::vec3(-0.25,-0.25,-0.25));

  
  // On initialise Model à l'identité
  Model = glm::mat4(1.0f);

  // On parlera d'une matrice Model/View/Projection (MVP)
  // Pour le moment elle est réduite à la matrice Model soit déplacer l'objet dans le repère universel
  MVP = Model;

  // Initialisation d'une rotation et d'une translation à l'identité.
  rotation = glm::mat4(1.0f);  
  translation = glm::mat4(1.0f);
}

void Display(void) {
  
  glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
  
  glUseProgram(programID);

  // Mise à jour de la matrice Model
  Model = translation * rotation * trans_initial;
  MVP =  Model;
  // Transmission de la matrice à la carte graphique
  // Il est temps d'aller regarder le shader TransformVertexShader.vert
  glUniformMatrix4fv(MatrixID, 1, GL_FALSE, &MVP[0][0]);
  
  glDrawElements(GL_TRIANGLES, 3*12, GL_UNSIGNED_INT, NULL);   
  
  glutSwapBuffers();
}

// Fonction appelée lorsque une touche du clavier est utilisée (à l'exception des touches spéciales pageUp, pageDown,...)
// cf le programme principale
// key : la valeur de la touche
// (x,y) la position de la souris au moment où on appuie sur la touche
void ClavierClassique(unsigned char key, int x, int y)
{
  switch(key) {	
  case 0x1B: // code pour la touche "échap"
    exit(0); // on sort du programme
  }
}

// Fonction appelée lorsque une touche spéciale du clavier est utilisée 
// cf le programme principale
// key : la valeur de la touche cf les codes GLUT
// (x,y) la position de la souris au moment où on appuie sur la touche
void ClavierSpecial(int key, int x, int y)
{
  switch(key) {
  case GLUT_KEY_UP: // code pour la touche up
    translation = glm::translate(translation, glm::vec3(0.f, stepTrans, 0.f)); // la matrice translation est mise à jour
                                                                               // translation = translation * "translation de stepTrans sur l'axe Y"
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

// Fonction appelée lors d'un clic souris
// bouton : le bouton cliqué (cf code GLUT)
// etat : appuyé / relaché
// (x,y) la position de la souris lors de l'événement
// Ici on mémorise la position de la souris au moment du clic
// et on mémorise aussi quel bouton est cliqué afin de différentier
// les actions lors du déplacement de la souris
/// cf fonction suivante
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

// Fonction appelée lors du déplacement de la souris
// Ici on calcule les déplacements translation/rotation en fonction du
// déplacement de la souris si un clic est actif.
void Motion (int x, int y)
{
  if (rightbutton) {
    translation = glm::translate(translation, glm::vec3((x-mouseXOld)*stepTrans/10, -(y-mouseYOld)*stepTrans/10, 0.f));
    mouseXOld=x;
    mouseYOld=y;
  }  
  else if (leftbutton){
    rotation = glm::rotate(rotation, (x-mouseXOld)*stepTrans, glm::vec3(0.f, 1.f, 0.f));
    rotation = glm::rotate(rotation, -(y-mouseXOld)*stepTrans, glm::vec3(1.f, 0.f, 0.f));
    mouseXOld=x;
    mouseYOld=y;
  }
  glutPostRedisplay();     
}
int main(int argc, char** argv)
{

  glutInit (&argc,argv) ;
  
  glutInitContextVersion(3, 3);
  glutInitContextFlags(GLUT_FORWARD_COMPATIBLE);
  glutInitContextProfile(GLUT_CORE_PROFILE);
  
  glutInitDisplayMode (GLUT_DOUBLE | GLUT_RGBA | GLUT_DEPTH) ;
  glutInitWindowSize (500,500) ;
  glutInitWindowPosition (100, 100) ;
  glutCreateWindow ("GLUT") ;
  glewExperimental = GL_TRUE; 
  
  GLenum err = glewInit();
  
  init();

  // Déclaration des différents callbacks en fonction des événements
  // on a déjà vu le callback Display
  // A suivre :
  // KeyboardFunc : événement touche clavier classique / callback "ClavierClassique"
  // SpecialFunc : événement touche clavier spécial / callback "ClavierSpecial"
  // MouseFunc : événement touche clavier classique / callback "Souris"
  // MotionFunc : événement touche clavier classique / callback "Motion"
  glutDisplayFunc(Display);
  glutKeyboardFunc(ClavierClassique);
  glutSpecialFunc(ClavierSpecial);
  glutMouseFunc(Souris);
  glutMotionFunc(Motion);

  // Désormais la boucle infinie traite les interactions via le clavier et la souris.
  glutMainLoop () ;
  return 0 ;
  
}
