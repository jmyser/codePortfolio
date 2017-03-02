

print "Say: ",
input = raw_input()

a = ["     #  ", "    ##  ", "   #  # ", "   #### ", "  #    #"]
b = ["  ##### ", "  #    #", "  ##### ", "  #    #", "  ##### "]
c = ["   #### ", "  #    #", "  #     ", "  #    #", "   #### "]
d = ["  ##### ", "  #    #", "  #    #", "  #    #", "  ##### "]
e = ["  ######", "  #     ", "  ####  ", "  #     ", "  ######"]
f = ["  ######", "  #     ", "  ####  ", "  #     ", "  #     "]
g = ["   #####", "  #     ", "  #  ###", "  #    #", "   #####"]
h = ["  #    #", "  #    #", "  ######", "  #    #", "  #    #"]
i = ["  ######", "    #   ", "    #   ", "    #   ", "  ######"]
j = ["       #", "       #", "  #    #", "  #    #", "   #### "]
k = ["  #   # ", "  #  #  ", "  ###   ", "  #   # ", "  #    #"]
l = ["  #     ", "  #     ", "  #     ", "  #     ", "  ######"]
m = ["   #  # ", "  # ## #", "  #  # #", "  #    #", "  #    #"]
n = ["  ##   #", "  # #  #", "  #  # #", "  #   ##", "  #    #"]
o = ["   #### ", "  #    #", "  #    #", "  #    #", "   #### "]
p = ["  ##### ", "  #    #", "  ##### ", "  #     ", "  #     "]
q = ["   #### ", "  #    #", "  #  # #", "  #   # ", "   ### #"]
r = ["  ##### ", "  #    #", "  ##### ", "  #  #  ", "  #   # "]
s = ["   #####", "  #     ", "   #### ", "       #", "  ##### "]
t = ["  ######", "    #   ", "    #   ", "    #   ", "    #   "]
u = ["  #    #", "  #    #", "  #    #", "  #    #", "   #### "]
v = ["  #    #", "  #    #", "  #   # ", "   # #  ", "    #   "]
w = ["  #    #", "  #    #", "  #  # #", "  # ## #", "   #  # "]
x = ["  #    #", "   #  # ", "    ##  ", "   #  # ", "  #    #"]
y = ["  #    #", "   #  # ", "    ##  ", "    #   ", "   #    "]
z = ["  ######", "      # ", "     #  ", "    #   ", "  ######"]
space = ["        ","        ","        ","        ","        "]

for line in range(5):
	for charIndex in range(len(input)):
		if input[charIndex] == 'a':
			print a[line],
		elif input[charIndex] == 'b':
			print b[line],
		elif input[charIndex] == 'c':
			print c[line],
		elif input[charIndex] == 'd':
			print d[line],
		elif input[charIndex] == 'e':
			print e[line],
		elif input[charIndex] == 'f':
			print f[line],
		elif input[charIndex] == 'g':
			print g[line],
		elif input[charIndex] == 'h':
			print h[line],
		elif input[charIndex] == 'i':
			print i[line],
		elif input[charIndex] == 'j':
			print j[line],
		elif input[charIndex] == 'k':
			print k[line],
		elif input[charIndex] == 'l':
			print l[line],
		elif input[charIndex] == 'm':
			print m[line],
		elif input[charIndex] == 'n':
			print n[line],
		elif input[charIndex] == 'o':
			print o[line],
		elif input[charIndex] == 'p':
			print p[line],
		elif input[charIndex] == 'q':
			print q[line],
		elif input[charIndex] == 'r':
			print r[line],
		elif input[charIndex] == 's':
			print s[line],
		elif input[charIndex] == 't':
			print t[line],
		elif input[charIndex] == 'u':
			print u[line],
		elif input[charIndex] == 'v':
			print v[line],
		elif input[charIndex] == 'w':
			print w[line],
		elif input[charIndex] == 'x':
			print x[line],
		elif input[charIndex] == 'y':
			print y[line],
		elif input[charIndex] == 'z':
			print z[line],
		elif input[charIndex] == ' ':
			print space[line],
		else:
			print
	print
