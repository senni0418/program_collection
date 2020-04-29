#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct pixel {
    unsigned char r, g, b;
} Pixel;

typedef struct image {
    unsigned int width, height, max;
    Pixel **data;
} Image;

void printPPM(Image *image) {
    int i,j;
    for (i=0; i< image->height; i++){
        for (j=0; j< image->width; j++){
            printf("#%02x%02x%02x", image->data[i][j].r, image->data[i][j].g, image->data[i][j].b);
        }
        printf("\n");
    }
}

Image *readPPM(char *file_name) {

    FILE *file = fopen(file_name, "r");
    if (!file) {
        fprintf(stderr, "Unable to open file \"%s\"\n", file_name);
        return NULL;
    }
    
    char format[3];
    /*fscanf(file, "%2s\n", format);*/
    if(fscanf(file, "%s\n", format) != 1) 
        return NULL;
    if (strcmp(format, "P3"))
        return NULL;

    Image *image = malloc(sizeof(Image));

    if(fscanf(file, "%u %u %u", &image->width, &image->height, &image->max) != 3)
        return NULL;

    image->data = malloc(sizeof(Pixel *) * image->height);
    int i, j;
    for (i = 0; i < image->height; i++)
        image->data[i] = malloc(sizeof(Pixel) * image->width);

    for (i = 0; i < image->height; i++)
        for (j = 0; j < image->width; j++){
            int pixels_read = fscanf(file, "%hhu %hhu %hhu", &(image->data[i][j].r), &(image->data[i][j].g), &(image->data[i][j].b));

            if (pixels_read != 3)
                return NULL;
        }

    fclose(file);
    return image;
}

int writePPM(char *file_name, Image *image){

    FILE *file = fopen(file_name, "w");
    if (!file) {
        fprintf(stderr, "Unable to open file \"%s\"\n", file_name);
        return -1;
    }

    fprintf(file, "P3\n");
    fprintf(file, "%u %u\n", image->width, image->height);
    fprintf(file,"%u\n", image->max);

    int i,j;
    for (i = 0; i < image->height; i++)
    {
        for (j = 0; j < image->width; j++)
        {
            fprintf(file, "%u %u %u ", image->data[i][j].r, image->data[i][j].g, image->data[i][j].b);
        }
        fprintf(file, "\n");
    }

    fclose(file);
    return 0;
    
}


void filter(Image *input, Image *output, int *kernel, int n, int scale){
#define KK(k,l)  kernel[(k) * n + (l)]
    int i,j,k,l; 
    int red, green, blue;
    int x, y;
    output->data = malloc(sizeof(Pixel*) * output->height);
    for (i=0; i<output->height; i++){
        output->data[i] = malloc(sizeof(Pixel) * output->width);
    }
    i = 0;
    
    while (i < input->height){
        j = 0;
        while (j < input->width)
        {
            red = 0;
            green = 0;
            blue = 0;
            k = 0;
            while (k < n)
            {
                l = 0;
                while (l < n)
                {
                    x = i + k - (n/2);
                    y = j + l - (n/2);

                    if (x>=0 && x<input->height && y>=0 && y<input->width){
                        red += ((int)((input->data)[x][y].r) * (KK(k,l))) / scale;
                        green += ((int)((input->data)[x][y].g) * (KK(k,l))) / scale;
                        blue += ((int)((input->data)[x][y].b) * (KK(k,l))) / scale;
                    }
                    else{
                        red += 0;
                        green += 0;
                        blue += 0;
                    }

                    l++;
                }
                k++;
            }
            if (red < 0)
                red = 0;
            else if (red > 255)
                red = 255;
            if (green < 0)
                green = 0;
            else if (green > 255)
                green = 255;
            if (blue < 0)
                blue = 0;
            else if (blue > 255)
                blue = 255;
            output->data[i][j].r = (unsigned char)red;
            output->data[i][j].g = (unsigned char)green;
            output->data[i][j].b = (unsigned char)blue;

            j++;
        }
        i++;
    }
#undef KK
}



int main(int argc, char **argv){
    if (argc != 4){
        printf("Usage: ./filter input_pic.ppm kernel output_pic.ppm\n");
        return -1;
    }

    char * input = argv[1];
    if (!input) {
        printf("Can not open the input ppm file\n");
        return -1;
    }
    char * kernel = argv[2];
    if (!kernel) {
        printf("Can not open the Kernel file\n");
        return -1;
    }
    char * output = argv[3];
    if (!output){
        printf("Problem with the output ppm file\n");
        return -1;
    }
    FILE *f;
    f = fopen(kernel, "r");
    int size, scale;
    if(fscanf(f,"%d",&size)!= 1)
        return 0;
    if(fscanf(f,"%d",&scale) != 1)
        return 0;

    int * K = malloc(sizeof(int) * size * size);
    int i;
    for(i = size*size - 1; i >= 0; i--){
        if(fscanf(f, "%d", K+i) != 1){
            return 0;
        }
    }
    fclose(f);
    Image *in_img = readPPM(input);
    Image *out_img =(Image*)malloc(sizeof(Image));
    out_img->width = in_img->width;
    out_img->height = in_img->height;
    out_img->max = in_img->max;
    filter(in_img,out_img,K,size,scale);
    writePPM(output,out_img);
    return 0;
}