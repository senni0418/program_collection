#include <stdio.h>
#include <string.h>
#include <stdlib.h>     /* srand, rand */
#include <time.h>       /* time */

int asm_main(int,char**);

int main(int argc,char** argv) {
  int ret_status;
  ret_status = asm_main(argc,argv);
  return ret_status;
}
