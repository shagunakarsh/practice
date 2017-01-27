#include<iostream>
#include<cmath>

using namespace std;

int* merge(int v1[], int l1, int v2[], int l2) {
	if(l1==0) return v2;
	if(l2==0) return v1;

	int *merged=new int[l1+l2];

	int k=0, i=0, j=0;
	for(;i<l1 && j<l2 && k<l1+l2; ) {
		if(v1[i] < v2[j]) {
			merged[k] = v1[i];
			++i;
			++k;
		} else {
			merged[k] = v2[j];
			++j;
			++k;
		}
		//cout<<"i="<<i<<" j="<<j<<" k="<<k<<" merged["<<k<<"]="<<merged[k-1]<<endl;
	}
	
	for(; k<l1+l2;){
		if(i<l1) {
			merged[k]=v1[i];
			++i;
			++k;
		} else if(j<l2){
			merged[k]=v1[j];
			++j;
			++k;
		}
	}

	return merged;
}

void printArray(int *arr, int l) {

	for(int i=0;i<l; ++i) {
		cout<<arr[i]<<" ";
	}
}


int main() {
	int v1[]={1, 5, 8, 66, 90};
	int v2[]={3, 4, 5, 6, 7,8, 9, 10,20};

	printArray(v1, 5);
	cout<<endl;
	printArray(v2, 9);
	cout<<endl;
	int *merged = merge(v1, 5, v2, 9);
	printArray(merged, 14);

	return 0;
}
