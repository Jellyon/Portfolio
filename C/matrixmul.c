#include <stdio.h>
#include <stdlib.h>
int main(){
	
	printf("Hello.\n");
	int x =1; int y =0; 
	int row=0,row1=0, col=0, col1=0;
	int a[300][300]; int b[300][300]; 
	while(x){
		printf("Please enter the dimensions of the matrix as 2 separate integers 1-300.\n");
		scanf("%d %d", &row, &col);
		if(row < 301 && row > 0 && col < 301 && col > 0){
			a[row][col];
			x=0;
		}
		else
		printf("between 1 and 300 please");
		
	}
	x=1;

	printf("Enter values for Matrix A between -1000 to 1000:\n");
	
	for(int i = 0; i< row; i++){
		for(int j = 0; j<col; j++){
			x=1;
			while(x){
				scanf("%d", &y);
				if(abs(y) < 1001){
					a[i][j] = y;	
					x=0;}
				else
					printf("Between -1000 and 1000 please.\n");
			}
		}	
	}
	printf("Matrix A is:\n");
	for(int i = 0; i< row; i++){
		for(int j = 0; j<col; j++){
			printf("%d\t", a[i][j]);	
		}
		printf("\n");	
	}
	x=1;
		while(x){
			printf("Enter dimensions of Matrix B, up to 300 \n");
			
			scanf("%d %d", &row1, &col1);
			
			if(row1 == col && col1 < 301 && col1 > 0)
				x=0;
			else{
				printf("Dimension 1 must match dimension 2 of matrix 1. ");
				printf("Also make sure dimension 2 is between 0 to 300.\n");}
		}
		
	b[row1][col1];
	
	printf("Enter values for Matrix B between -1000 to 1000:\n");
	
	for(int i = 0; i< row1; i++){
		for(int j = 0; j<col1; j++){
			x=1;
			while(x){
				scanf("%d", &y);
				if(abs(y) < 1001){
					b[i][j] = y;	
					x=0;}
				else
					printf("Between -1000 and 1000 please.\n");
			}
		}	
	}
	
	printf("Matrix B is:\n");
	for(int i = 0; i< col; i++){
		for(int j = 0; j<col1; j++){
			printf("%d\t", b[i][j]);
		}
		printf("\n");
	}
	

	
	
	
	printf("The following matrix, C, is the product of A and B\n");
	
	int c[row][col1];   
	
	
	for(int i = 0; i < row; i++){
		for (int j = 0; j < col1; j++){
			c[i][j] = 0;
			for (int k = 0; k < row1; k++){
				c[i][j] += a[i][k] * b[k][j];	
			}
			
			printf("%d\t", c[i][j]);
		}
		printf("\n");
	}
	
	
	printf("Enter any integer to exit\n");
	scanf("%d", &row);
	return(0);
	
	
}
