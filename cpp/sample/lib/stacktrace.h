#ifndef STACKTRACE_H
#define STACKTRACE_H

#include <stdio.h>
#include <execinfo.h>
#include <signal.h>
#include <stdlib.h>
#include <unistd.h>

void print_stack_trace() {
  int N = 50;
  void *array[N];
  size_t size;

  // get void*'s for all entries on the stack
  size = backtrace(array, N);

  // print out all the frames to stderr
  fprintf(stderr, "---Print Stack Trace:---\n");
  backtrace_symbols_fd(array, size, STDERR_FILENO);
  fprintf(stderr, "------------------------\n");
}

#endif /* STACKTRACE_H */

