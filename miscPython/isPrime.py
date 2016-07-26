#!/usr/local/bin/python
import math

primeCandidate = raw_input('Enter a number to test for prime: ')

listOddTrys = [2]

for x in range(2, int(math.sqrt(long(primeCandidate)))+1):
	if x % 2 == 1:
		listOddTrys.append(x);

# print listOddTrys

primeCandidateAnswer = "is prime!"

for x in listOddTrys:
	if long(primeCandidate) % x == 0:
		primeCandidateAnswer = "isn't prime";
		break
		
print primeCandidate, primeCandidateAnswer;