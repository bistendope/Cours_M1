#include "toolbox.h"

long timediff(clock_t t1, clock_t t2) {
    long elapsed;
    elapsed = ((double)t2 - t1) / CLOCKS_PER_SEC * 1000;
    return elapsed;
}

int main_time(int argc, char *argv[]) {
    clock_t t1, t2;
    long elapsed;

    t1 = clock();
    simple_cmd(argv+1);
    t2 = clock();

    elapsed = timediff(t1, t2);
    printf("elapsed: %ld ms\n", elapsed);


    return 0;
}