#version 400 core

layout (location = 0) in vec2 vertexPosition;
layout (location = 1) in vec4 fragmentPosition;
layout (location = 2) in vec2 texturePosition;

uniform float layer;
uniform mat3 projection;
uniform mat3 model;
uniform mat3 view;

uniform int colorComponent;
uniform int textureComponent;
uniform int lightComponent;

out vec4 fragmentColor;
out vec2 textureCoords;
out vec2 fragCoordinate;

void main()
{
	vec3 position = projection * view * model * vec3(vertexPosition, 1);
	vec3 lightPosition = vec3(position.x * 10, position.y * 10, 1);
	
	if(colorComponent == 1) fragmentColor = fragmentPosition;
	if(textureComponent == 1) textureCoords = texturePosition;
	if(lightComponent == 1) fragCoordinate = vec2(lightPosition.x, lightPosition.y);
	
    gl_Position = vec4(position.x, position.y, layer / 10, 1);
}