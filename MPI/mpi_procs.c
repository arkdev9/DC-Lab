#include <mpi.h>
#include <stdio.h>
#include <stdlib.h>
int main(int argc, char *argv[]) {
	int i;
	int nitems = 10;
	int mynode, totalnodes;
	double *array;
	MPI_Status status;
	MPI_Init(NULL, NULL);
	MPI_Comm_size(MPI_COMM_WORLD, &totalnodes);
	MPI_Comm_rank(MPI_COMM_WORLD, &mynode);
	array = (double *)malloc(sizeof(double) * nitems);

	if (mynode == 0)
		for (i = 0; i < nitems; i++) array[i] = (double)i;

	if (mynode == 0)
		for (i = 1; i < totalnodes; i++)
			MPI_Send(array, nitems, MPI_DOUBLE, i, 1, MPI_COMM_WORLD);
	else
		MPI_Recv(array, nitems, MPI_DOUBLE, 0, 1, MPI_COMM_WORLD, &status);

	for (i = 0; i < nitems; i++) {
		printf("Processor %d", mynode);
		printf(": array[%d] = %f\n", i, array[i]);
	}
	MPI_Finalize();
}