#include <iostream>
#include <GL/glew.h>
#include <GL/gl.h>
#include <GL/freeglut.h>
#include "../Common/icosaedre.hpp"
#include <math.h>
#include <fstream>

#include "../Common/shaders_utilities.hpp"
#include "../Common/lecture_trajectoire.hpp"

// Include GLM
#include <glm/glm.hpp>
#include <glm/gtc/matrix_transform.hpp>
using namespace glm;

using namespace std;

// Pour la fenêtre

GLfloat* coordonnees;
GLfloat* couleurs;
GLfloat* icosaedres;
GLfloat* indices;
GLfloat* couleurs_icosaedres;

float stepTrans=1.0;
int mouseXOld, mouseYOld;
bool leftbutton = false;
bool rightbutton = false;
bool middlebutton = false;

GLuint vboID[2];
GLuint vaoID;
GLuint programID;

GLuint MatrixID;
glm::mat4 Projection;
glm::mat4 View;
glm::mat4 Model;
glm::mat4 MVP;

glm::mat4 translation;
glm::mat4 trans_initial;
glm::mat4 rotation;


XDRFILE * xdrFile;
const char* trajectoire_file;
const char* couleur_file;
GLint NbAtoms;
GLfloat bbox[6];
GLfloat centre[3];
bool eof = false;

int stepstep;
float tps;
float box[9];

/*
correction: nombre de points dans un icosaedre * nb d'icosaedres
créer un tableau d'icosaedres de cette taille
recopier n fois l'icosaedre present dans icosaedre.hpp
envoyer tout ça à la carte graphique
*/


void get_first_frame()
{
  if (!eof) {
    if (xdrfile_getframe_header(&NbAtoms, &stepstep, &tps, box, xdrFile)!=1) {
      // La lecture de l'entête permet de récupérer des informations générales sur les trajectoires
      // NbAtoms : nombre d'atomes pour chaque frame
      // On peut donc allouer une taille aux tableaux des attributs
      coordonnees = new float[3*NbAtoms];    
      couleurs = new float[3*NbAtoms];   
      icosaedres = new GLfloat [NbAtoms * 36]; 
      indices = new GLfloat [NbAtoms * 60]; 
      couleurs_icosaedres = new GLfloat [NbAtoms * kekchose];
      char c;  


      // gestion des couleurs à partir d'un fichier qui décrit une frame
      // Pour chaque atome on vient lire la couleur sous forme RGB
      ifstream f;
      float rgb[3];
      f.open(couleur_file,ios::in);

      int index = 0;
      f>>c;
      while (!f.eof()){
	choix_couleur(c,rgb);
	couleurs[index] = rgb[0];
	couleurs[index+1] = rgb[1];
	couleurs[index+2] = rgb[2];
	index+=3;
	f>>c;
      }
      f.close();

      // Lecture des positions des atomes
      // le tableau coordonnees contient x,y,z pour chaque atome 
      xdrfile_getframe_positions(NbAtoms, coordonnees, xdrFile);

      // construction de la bounding box afin de placer la caméra etc
      bbox[0] = coordonnees[0];
      bbox[1] = coordonnees[1];
      bbox[2] = coordonnees[2];
      bbox[3] = coordonnees[0];
      bbox[4] = coordonnees[1];
      bbox[5] = coordonnees[2];
      for (int i=1; i<NbAtoms; i++) {
	if (coordonnees[3*i]<bbox[0])
	  bbox[0] = coordonnees[3*i];
	if (coordonnees[3*i+1]<bbox[1])
	  bbox[1] = coordonnees[3*i+1];
	if (coordonnees[3*i+2]<bbox[2])
	  bbox[2] = coordonnees[3*i+2];
	if (coordonnees[3*i]>bbox[3])
	  bbox[3] = coordonnees[3*i];
	if (coordonnees[3*i+1]>bbox[4])
	  bbox[4] = coordonnees[3*i+1];
	if (coordonnees[3*i+2]>bbox[5])
	  bbox[5] = coordonnees[3*i+2];
      }
    }
    centre[0] = bbox[0]+(bbox[3]-bbox[0])/2;
    centre[1] = bbox[1]+(bbox[4]-bbox[1])/2;
    centre[2] = bbox[2]+(bbox[5]-bbox[2])/2;
    //ajout
    for(uint32_t i=0;i<NbAtoms; i++){
    	for(uint32_t j=0; j<12; j++){
    		couleurs_icosaedres[i*36+j*3]=couleurs[3*i];
    		couleurs_icosaedres[i*36+j*3+1]=couleurs[3*i+1];
    		couleurs_icosaedres[i*36+j*3+2]=couleurs[3*i+2];
    	}
    
    	for (uint32_t j=0; j<36; j++){
    		icosaedres[i* 36 + j] = sommets[j];
    	}
    	couleurs_icosaedres[i*36]=couleurs[i];
    	
    	for(uint32_t j=0; j<60; j++){
    		indices[i*60+j] = indices_par_icosaedre[j];
    	}
    }
    //ajout
  }

}

// Fonction supplémentaire pour lire une frame à l'infini
// cf idle()
void get_frame()
{
  if (xdrfile_getframe_header(&NbAtoms, &stepstep, &tps, box, xdrFile)!=1)  
    xdrfile_getframe_positions(NbAtoms, coordonnees, xdrFile);
  else {
    xdrfile_close(xdrFile);
    xdrFile = xdrfile_open(trajectoire_file,"r");
  }
}



void init() {
  
  
  glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
  
  glClearDepth(1.0);
  glEnable(GL_DEPTH_TEST);
  
  programID = LoadShaders( "../Shaders/TransformVertexShader.vert", "../Shaders/ColorFragShader.frag");
  get_first_frame();
  
  
  glGenVertexArrays(1, &vaoID);
  glBindVertexArray(vaoID);
  
  glGenBuffers(2,vboID);
  
  glBindBuffer(GL_ARRAY_BUFFER, vboID[0]);
  glBufferData(GL_ARRAY_BUFFER,3*NbAtoms*sizeof(float),coordonnees,GL_DYNAMIC_DRAW);
  glVertexAttribPointer(0,3,GL_FLOAT,GL_FALSE,0,(void*)0);
  glEnableVertexAttribArray(0);
   
  glBindBuffer(GL_ARRAY_BUFFER, vboID[1]);
  glBufferData(GL_ARRAY_BUFFER,3*NbAtoms*sizeof(float),couleurs,GL_STATIC_DRAW);
  glVertexAttribPointer(1,3,GL_FLOAT,GL_FALSE,0,(void*)0);
  glEnableVertexAttribArray(1);
  

  MatrixID = glGetUniformLocation(programID, "MVP");
  
  Projection = glm::perspective(70.0f, 4.0f / 3.0f, 0.1f, 1000.0f);
  View = glm::lookAt(glm::vec3(0,0,2*bbox[5]), glm::vec3(0,0,0), glm::vec3(0,1,0));
  
  trans_initial = glm::translate(glm::mat4(1.0f), glm::vec3(-centre[0],-centre[1],-centre[2]));
  
  Model = glm::mat4(1.0f);
  
  MVP = Projection * View * Model;

  rotation = glm::mat4(1.0f);
  
  translation = glm::mat4(1.0f);
  

}

void Display(void) {
  glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
  
  glUseProgram(programID);
  
  Model = translation * rotation * trans_initial;
  
  MVP =  Projection * View * Model;

  glUniformMatrix4fv(MatrixID, 1, GL_FALSE, &MVP[0][0]);
  
  
  glPointSize(5);

  glDrawArrays(GL_POINTS,0,NbAtoms);
  
  glutSwapBuffers();
  
}

// Dans la boucle infini glut quand on a rien à faire on lit une
// nouvelle frame
void Idle()
{
  
  get_frame();
  
  glBindBuffer(GL_ARRAY_BUFFER, vboID[0]);
  glBufferData(GL_ARRAY_BUFFER,3*NbAtoms*sizeof(float),coordonnees,GL_DYNAMIC_DRAW);
  
  glutPostRedisplay();
  
}

void ClavierClassique(unsigned char key, int x, int y)
{
  switch(key) {	
  case 0x1B:
    exit(0);
  }
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
       translation = glm::translate(translation, glm::vec3(0.f, 0.f, -(x-mouseYOld)*stepTrans/10));
     mouseXOld=x;
     mouseYOld=y;
   }
   else if (rightbutton) {
     translation = glm::translate(translation, glm::vec3(-(x-mouseXOld)*stepTrans/10, -(y-mouseYOld)*stepTrans/10, 0.f));
     mouseXOld=x;
     mouseYOld=y;
   }
   
   if (leftbutton){
     rotation = glm::rotate(rotation, (x-mouseXOld)*stepTrans, glm::vec3(0.f, 1.f, 0.f));
     rotation = glm::rotate(rotation, -(y-mouseXOld)*stepTrans, glm::vec3(1.f, 0.f, 0.f));
     mouseXOld=x;
     mouseYOld=y;
   }
   glutPostRedisplay();     
}

int main(int argc, char** argv)
{

  trajectoire_file = argv[1];
  couleur_file = argv[2];
  
  xdrFile = xdrfile_open(trajectoire_file,"r"); //Ouverture du fichier en lecture

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
  glutIdleFunc(Idle);
  glutKeyboardFunc(ClavierClassique);
  glutSpecialFunc(ClavierSpecial);
  glutMouseFunc(Souris);
  glutMotionFunc(Motion);

  glutMainLoop () ;

  xdrfile_close(xdrFile);
  return 0 ;
  
}
