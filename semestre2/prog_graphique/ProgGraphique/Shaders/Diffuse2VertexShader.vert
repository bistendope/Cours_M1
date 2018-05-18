#version 330 core

layout(location = 0) in vec3 vertexPosition;
layout(location = 1) in vec3 colorPosition;
layout(location = 2) in vec3 vertexNormale;

out vec3 fragmentColor;


vec4 Vertex;

out vec3 Normal_cameraspace;
out vec3 LightDirection_cameraspace;

uniform mat4 PVM; // la matrice transmise par le programme CPU
uniform mat4 M; // Matrice Model
uniform mat4 V; // Matrice View
uniform vec3 light_position; // la position de la lumière reçue par le CPU

void main(){
  
  Vertex.xyz = vertexPosition;
  Vertex.w = 1.0;
  gl_Position = PVM * Vertex;
  

  vec3 vertexPosition_cameraspace = (V*M*vec4(vertexPosition,1)).xyz;
  
  vec3 LightPosition_cameraspace = ( V * vec4(light_position,1)).xyz;
  
  LightDirection_cameraspace = LightPosition_cameraspace - vertexPosition_cameraspace;
  
  Normal_cameraspace = (V*M* vec4(vertexNormale,0)).xyz;
  //  Normal_cameraspace = normalize(vertexNormale);
  
  fragmentColor = colorPosition;
  
}





