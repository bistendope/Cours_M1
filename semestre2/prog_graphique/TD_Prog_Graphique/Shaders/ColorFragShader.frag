#version 430 core

// Données reçues (interpolées) de la part du vertex shader
in vec3 fragmentColor;

// Résultats du Fragment shader
out vec3 Color;

void main(){
  Color = fragmentColor;  
}
