#define  _CRT_SECURE_NO_WARNINGS


#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <malloc.h>

typedef struct
{
	char * city;
	char * country;
	double population;
}City;

City cities[10005];
int cityn = 0;

int compare(const void * a, const void * b) {
	City * Ca = (City *)a;
	City * Cb = (City *)b;
	return Cb->population - Ca->population;
}


int main()
{
	FILE* fp = fopen("cities.csv", "r");
	char line[1005];
	int linen = 0;
	int i;
	while (fgets(line, 1005, fp))
	{
		if (line[strlen(line) - 1] == '\n')
			line[strlen(line) - 1] = '\0';
		if (linen > 0)
		{
			char delims[] = ",";
			char* result = NULL;
			char* record = NULL;
			record = strtok(line, ",");
			cities[cityn].city = (char*)malloc(sizeof(char) * 1005);
			strcpy(cities[cityn].city, record);
			record = strtok(NULL, ",");
			record = strtok(NULL, ",");
			record = strtok(NULL, ",");
			record = strtok(NULL, ",");
			cities[cityn].population = atof(record);
			record = strtok(NULL, ",");
			cities[cityn].country = (char*)malloc(sizeof(char) * 1005);
			strcpy(cities[cityn].country, record);
			cityn++;
		}

		linen++;
	}

	qsort(cities, cityn, sizeof(City), compare);

	fp = fopen("sorted.csv", "w");
	for (i = 0; i < cityn;i++)
	{
		fprintf(fp, "%s,%d,%s\n", cities[i].city, (int)cities[i].population, cities[i].country);
	}
	fclose(fp);

}