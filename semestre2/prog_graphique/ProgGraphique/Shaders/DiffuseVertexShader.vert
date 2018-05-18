#version 330 core

layout(location = 0) in vec3 vertexPosition;
layout(location = 1) in vec3 colorPosition;
layout(location = 2) in vec3 vertexNormale;

out vec3 fragmentColor;
out float cosTheta;

vec4 Vertex;
vec3 light_direction;
vec4 normale;

//il faut d'abord creer tous les uniform sur le CPU
uniform mat4 PVM; // la matrice transmise par le programme CPU
uniform mat4 M; // Matrice Model 
uniform vec3 light_position; // la position de la lumière reçue par le CPU

out vec3 light_pos;

void main(){
  
  Vertex.xyz = vertexPosition;
  Vertex.w = 1.0;
  gl_Position = PVM * Vertex;
  
  fragmentColor = colorPosition;
  
  Vertex = M*(vec4(vertexPosition,1.0));
  
  light_direction = light_position - Vertex.xyz;

  normale = M*vec4(vertexNormale,1.0);
  
  light_pos = light_position;
  
  cosTheta = clamp (dot(normalize(normale.xyz),normalize(light_direction)),0.0,1.0);
  
}


