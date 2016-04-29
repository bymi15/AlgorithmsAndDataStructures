//Created by Brian Min - 29/04/2016
//Sorting algorithms included:
//Bubblesort, Insertion sort, Selection sort, Mergesort, Quicksort, Heapsort

#include <stdio.h>
#include <stdlib.h>

//Helper Functions
int* genRandArr(int n) {
	int* a = (int *)malloc(n * sizeof(int));

	int i;
	for (i = 0; i < n; i++) {
		int num = (rand() % 100) + 1;
		*(a+i) = num;
	}

	return a;
}

void printArr(int* a, int n) {
	int i;
	for (i = 0; i < n; i++) {
		printf("[%d]", *(a+i));
	}
	printf("\n");
}

void swap(int* a, int x, int y) {
	int temp = a[x];
	a[x] = a[y];
	a[y] = temp;
}

//Bubblesort
void bubbleSort(int* a, int n) {
	int i, j;
	for (i = n - 1; i >= 0; i--) {
		for (j = 1; j <= i; j++) {
			if (a[j - 1] > a[j]) {
				swap(a, j - 1, j);
			}
		}
	}
}

//Insertion sort
void insertionSort(int* a, int n) {
	int i, j;
	for (i = 1; i < n; i++) {
		j = i;
		while (j > 0 && a[j] < a[j - 1]) {
			swap(a, j, j - 1);
			j--;
		}
	}
}

//Selection sort
void selectionSort(int* a, int n) {
	int i, j;
	for (i = 0; i < n; i++) {
		int minVal = a[i];
		int minIndex = i;
		for (j = n - 1; j >= i; j--) {
			if (a[j] < minVal) {
				minVal = a[j];
				minIndex = j;
			}
		}
		swap(a, i, minIndex);
	}
}


//Mergesort
void merge(int* a, int n, int left, int mid, int right) {
	int size = right - left + 1;
	int* temp = (int *)malloc(size * sizeof(int));
	int tempCount = 0;

	int i = left;
	int j = mid + 1;

	while (i <= mid && j <= right) {
		if (a[i] > a[j]) {
			temp[tempCount++] = a[j++];
		}else{
			temp[tempCount++] = a[i++];
		}
	}

	while (i <= mid) {
		temp[tempCount++] = a[i++];
	}
	
	while (j <= right) {
		temp[tempCount++] = a[j++];
	}

	int x;
	for (x = 0; x < size; x++) {
		a[left + x] = temp[x];
	}

	free(temp);
}

void mergeSort(int* a, int n, int left, int right) {
	if (left < right) {
		int mid = (left + right) / 2;
		mergeSort(a, n, left, mid);
		mergeSort(a, n, mid+1, right);

		merge(a, n, left, mid, right);
	}
}


//Quicksort
int partition(int* a, int n, int left, int right) {
	int i = left;
	int j = right;
	int pivot = a[(left + right) / 2];

	while (i < j) {
		while (a[i] < pivot) {
			i++;
		}
		while (a[j] > pivot) {
			j--;
		}
		swap(a, i, j);
		i++;
		j--;
	}

	return i;
}

void quickSort(int* a, int n, int left, int right) {
	int pivot = partition(a, n, left, right);
	
	if (left < pivot-1) {
		quickSort(a, n, left, pivot-1);
	}

	if (right > pivot) {
		quickSort(a, n, pivot, right);
	}
}

//Heapsort
int leftChild(int index) {
	return ((index << 1) + 1); //2i+1
}
int rightChild(int index) {
	return ((index << 1) + 2); //2i+2
}
int parent(int index) {
	return ((index - 1) >> 1); //(i-1)/2
}
void maxHeapify(int* a, int n, int i) {
	int left = leftChild(i);
	int right = rightChild(i);
	int maxIndex = i;
	int maxValue = a[i];
	
	if (left < n && a[left] > maxValue) {
		maxIndex = left;
		maxValue = a[left];
	}

	if (right < n && a[right] > maxValue) {
		maxIndex = right;
		maxValue = a[right];
	}

	if (maxIndex != i) {
		swap(a, i, maxIndex);
		maxHeapify(a, n, maxIndex);
	}
}

void buildHeap(int* a, int n) {
	int i;
	for (i = n/2 - 1; i >= 0; i--) {
		maxHeapify(a, n, i);
	}
}

void heapSort(int* a, int n) {
	buildHeap(a, n);

	int i;
	int heapSize = n;
	for (i = n - 1; i > 0; i--) {
		swap(a, 0, i);
		heapSize--;
		maxHeapify(a, heapSize, 0);
	}
}

int main() {
	srand(time(NULL));

	int size = 10;
	int* arr = genRandArr(size);

	printf("Unsorted: ");
	printArr(arr, size);

	/*
	bubbleSort(arr, size);
	insertionSort(arr, size);
	selectionSort(arr, size);
	mergeSort(arr, size, 0, size - 1);
	quickSort(arr, size, 0, size - 1);
	*/

	heapSort(arr, size);

	printf("Sorted: ");
	printArr(arr, size);

	free(arr);
	system("PAUSE");
	return 0;
}
