#include <stdio.h>
#include <string.h>

int main(int argc,char** argv) {
  FILE* fp = fopen(argv[1],"r");
  if (fp == 0) {
    printf("can't open file \"%s\"\n",argv[1]);
    return 0;
  }
  char line[1000], *p;
  int count=0;

  while((p=fgets(line,1000,fp))!=0) {
    while(*p != '\0') {
      if (*p=='.') count++;
    p++;
    }
    if (count < 3) {
      printf("%s",line);
      continue;
    }
    // too many sentences, display the first three
    p = line;
    count = 0;
    while(true) {
      putchar(*p);
      if (*p == '\n') break;
      if (*p == '.') count++;
      if (count == 3) {
         putchar('\n');
     p++; // eat .
     while(*p==' ') p++;
         count = 0;
     continue;
      }
    p++;
    }
  }
  fclose(fp);
  return 0;
}
