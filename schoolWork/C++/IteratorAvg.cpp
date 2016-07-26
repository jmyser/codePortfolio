//
//  IteratorAvg.cpp
//  Lab3a
//
//  Created by Jeremy Myser on 3/1/16.
//  Copyright © 2016 Jay Myser. All rights reserved.
//

#include <vector>
#include <iostream>
using namespace std;

// Return the average of a vector of numbers
template <typename E>
double avg(vector<E>& seq){
    typename vector<E>::iterator iter = seq.begin();
    double total = 0;
    
    // total all the elements in the vector
    while (iter != seq.end()){
        total = total + *iter;
        ++iter;
    }
    // return result
    return (total / seq.size());
}

int main() {
	vector<int> intNums;
    vector<double> doubleNums;
    double average;
	
	// Input a list of integers
	int n;
	cout << "Enter a sequence of integers separated by spaces (0 to stop): " << endl;
	cin >> n;
	while (n != 0) {
		intNums.push_back(n);
		cin >> n;
    }
    // Perform average and output results
    average = avg(intNums);
    cout << "The average is: " << average << endl;
    
    // Input a list of doubles
    double m;
    cout << "Enter a sequence of doubles separated by spaces (0 to stop): " << endl;
    cin >> m;
    while (m != 0) {
        doubleNums.push_back(m);
        cin >> m;
    }
    // Perform average and output results
    average = avg(doubleNums);
    cout << "The average is: " << average << endl;

}
