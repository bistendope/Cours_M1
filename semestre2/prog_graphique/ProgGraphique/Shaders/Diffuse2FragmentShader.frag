#version 330 core

// Données reçues (interpolées) de la part du vertex shader
in vec3 fragmentColor;
in vec3 Normal_cameraspace;
in vec3 LightDirection_cameraspace;

float cosTheta;

uniform vec4 ambiant;
uniform vec4 diffuse;

// Résultats du Fragment shader
out vec3 Color;

void main(){
  
  cosTheta = clamp (dot(normalize(Normal_cameraspace.xyz),normalize(LightDirection_cameraspace.xyz)),0.0,1.0);
  Color = ambiant.xyz*fragmentColor + diffuse.xyz*cosTheta;
}


