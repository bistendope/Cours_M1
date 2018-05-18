#version 330 core

layout(location = 0) in vec3 vertexPosition;
layout(location = 1) in vec3 colorPosition;
layout(location = 2) in vec3 Translation;
out vec3 fragmentColor;

vec4 Vertex;

uniform mat4 MVP; // la matrice transmise par le programme CPU

void main(){
  Vertex.xyz = (vertexPosition+Translation);
  //  Vertex.xyz = vertexPosition;
  Vertex.w = 1.0;
  gl_Position = MVP * Vertex;
  
  fragmentColor = colorPosition;
}
