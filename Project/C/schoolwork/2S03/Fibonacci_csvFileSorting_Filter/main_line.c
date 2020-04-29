#include "bmp.h"
#include <stdio.h>
#include <stdlib.h>
/*
Example of drawing a line
Here is a simple program that draws a line. 
*/
int main(int argc, char** argv)
{
    int width = 500;
    int height = 500;
    int k;
    RGB* image = malloc(sizeof(RGB) * width * height);
    int i;
    for (i = 0; i < width * height; i++) {
        image[i].r = 255;
        image[i].g = 255;
        image[i].b = 255;
    }

    for (i = 100; i < height-100; i++) {
      k = i*width+250;
      image[k].r = 0;
      image[k].g = 0;
      image[k].b = 0;
    }

    saveBMP("test.bmp", width, height, image);
    free(image);
    return 0;
}
