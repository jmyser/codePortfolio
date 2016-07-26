#!/usr/local/bin/python
import math

primeCandidate = raw_input('Enter a number to test for prime: ')

factorTrys = [2]
primeCandidateFactors = []
primeCandidateRemainder = long(primeCandidate)
primeCandidateAnswer = "is prime."

for x in range(2, int(long(primeCandidate))+1):
	if x % 2 == 1:
		factorTrys.append(x);

while primeCandidateRemainder > 1:
	for x in factorTrys:
		if primeCandidateRemainder % x == 0:
			primeCandidateFactors.append(x);
			primeCandidateAnswer = "isn't prime.";
			primeCandidateRemainder = long(primeCandidateRemainder)/x;
			print primeCandidateFactors;
			
if primeCandidateAnswer == "is prime.":
	print primeCandidate, primeCandidateAnswer;
else: print primeCandidate, primeCandidateAnswer, "and it's factors are", primeCandidateFactors