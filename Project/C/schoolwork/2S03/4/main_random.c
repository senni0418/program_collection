#include "bmp.h"
#include <stdio.h>
#include <stdlib.h>

/* 
A4 images
Here is a simple program that generates a random image and saves it into test.bmp. Compile as
gcc main_random.c bmp.c
*/

int main(int argc, char** argv)
{
    int width = 500;
    int height = 500;

    RGB* image = malloc(sizeof(RGB) * width * height);
    int i;
    for (i = 0; i < width * height; i++) {
        image[i].r = random() % 255;
        image[i].g = random() % 255;
        image[i].b = random() % 255;
    }

    saveBMP("test.bmp", width, height, image);
    free(image);
    return 0;
}
