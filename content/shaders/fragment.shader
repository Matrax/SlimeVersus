#version 400 core

in vec4 fragmentColor;
in vec2 textureCoords;
in vec2 fragCoordinate;

uniform sampler2D texture_1;

uniform int lightPointsCount;
uniform float globalLightIntensity;
uniform float[100 * 4] lightPoints;

uniform int colorComponent;
uniform int textureComponent;
uniform int lightComponent;
uniform int textComponent;

out vec4 color;

float getLightIntensity() 
{
	float light = globalLightIntensity;
	
	for(int i = 0; i < lightPointsCount * 4; i += 4)
	{
		float x = abs(fragCoordinate.x - lightPoints[i]);
		float y = abs(fragCoordinate.y - lightPoints[i + 1]);
		float lightDistance = sqrt(x * x + y * y);
		float lightPower = lightPoints[i + 3] / (lightDistance * lightDistance) / 10;
		if(lightDistance <= lightPoints[i + 2]) light += lightPower;
		if(light > 1) break;
	}
	
	if(light > 1) light = 1;
	return light;
}

void main()
{
	color = vec4(1, 1, 1, 1);
	float light = getLightIntensity();
	vec4 texture = texture(texture_1, textureCoords);
	
	if(colorComponent == 1) 
	{
		if(fragmentColor.a < 0.1) discard;
		color = fragmentColor;
	}
	
	if(textureComponent == 1)
	{
		if(texture.a < 0.1) discard;
		color = color * texture;
	} 
	
	if(textComponent == 1)
	{
		if(texture.r < 0.05 && texture.g < 0.05 && texture.b < 0.05 || texture.a < 0.1) discard;
	}
	
	if(lightComponent == 1) 
	{
		if(light < 0.01) discard;
		color = color * light;
	}
}


