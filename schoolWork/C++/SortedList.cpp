//
//  SortedList.cpp
//  Lab3a
//
//  Created by Jeremy Myser on 3/1/16.
//  Copyright © 2016 Jay Myser. All rights reserved.
//

#include <list>
#include <iostream>
using namespace std;

// Insert a number into a list in numerical order
template <typename Number>
void insertSorted(list<Number>& seq, Number n){
    typename list<Number>::iterator iter = seq.begin();
    
    // move iterator to desired position in list
    while (iter != seq.end() && n > *iter){
        ++iter;
    }
    // insert element into list
    seq.insert(iter, n);
}

template <typename Number>
void print(list<Number>& seq) {
    typename list<Number>::iterator iter = seq.begin();
    cout << "Sorted Result:" << endl;
    while (iter != seq.end()) {		// while more remain
        cout << *iter << " ";
        ++iter;						// advance to next
    }
    cout << endl;
}

int main() {
    list<int> nums;
    
    // input a list of integers
    int n;
    cout << "Enter a sequence of integers separated by spaces (0 to stop): " << endl;
    cin >> n;
    while (n != 0) {
        // add numbers to list
        insertSorted(nums, n);
        cin >> n;
    }
    // output ordered list
    print(nums);

    
}
