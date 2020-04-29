#include "bmp.h"
#include <stdio.h>
#include <stdlib.h>

/*
A4, problem 1, drawing a few segments
Here is a main program that produces a few segments. After compile 
gcc -Wall main_line2.c bmp.c
and run ./a.out, open test.bmp. 
*/

typedef enum {
    up,
    down,
    left,
    right
} direction;

void draw(int x, int y, direction dir, int step, int w, RGB* im, RGB c)
{
#define I(i, j) im[(i)*w + j]
    int j;
    if (dir == up)
        for (j = 0; j < step; j++)
            I(x + j, y) = c;
    if (dir == down)
        for (j = 0; j < step; j++)
            I(x - j, y) = c;
    if (dir == right)
        for (j = 0; j < step; j++)
            I(x, y + j) = c;
    if (dir == left)
        for (j = 0; j < step; j++)
            I(x, y - j) = c;
#undef I
}

int main(int argc, char** argv)
{
    int width = 500;
    int height = 500;
    
    RGB* image = malloc(sizeof(RGB) * width * height);
    RGB white = { 255, 255, 255 };
    RGB black = { 0, 0, 0 };
    RGB red = { 255, 0, 0 };
    RGB green = { 0, 255, 0 };
    RGB blue = { 0, 0, 255 };
    RGB magenta = { 255, 0, 255};
    int i;

    // set background
    for (i = 0; i < width * height; i++)
        image[i] = white;

    int x = 100, y = 100;
    int step = 100;
    draw(x, y, up, step, width, image, black);
    x += step;
    draw(x, y, right, step, width, image, red);
    y += step;
    draw(x, y, down, step, width, image, blue);
    x -= step;
    draw(x, y, right, step, width, image, green);
    y += step;
    draw(x, y, up, step, width, image, magenta);
    x+=step;
    draw(x, y, up, step, width, image, black);

    saveBMP("test.bmp", width, height, image);
    free(image);
    return 0;
}
