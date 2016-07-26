#!/usr/local/bin/python
print 'How many palindromes are in a range of numbers?'
testNumbersLimit = raw_input('Range of 0-: ')

palindromes = []

for x in range(0, long(testNumbersLimit)):
	if str(x) == str(x)[::-1]:
		palindromes.append(x);

if str(testNumbersLimit) == str(testNumbersLimit)[::-1]:
	palindromes.append(testNumbersLimit);

print 'The total number of palindromes is:'		
print len(palindromes);

textAnswer = raw_input('Would you like to see the palindromes? <y/n>: ')
if textAnswer == 'y':
	print palindromes;