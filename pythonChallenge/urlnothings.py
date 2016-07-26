import urllib2
newNothing = '33110'
urlCount = 1
while urlCount < 400:
	def getPage():
		url="http://www.pythonchallenge.com/pc/def/linkedlist.php?nothing=" + newNothing
 
		req = urllib2.Request(url)
		response = urllib2.urlopen(req)
		return response.read()
	 
	newNothing = getPage()[24:]
	print newNothing
	urlCount = urlCount + 1

 
#if __name__ == "__main__":
#    namesPage = getPage()
#    print namesPage
    
    

#urlCounter = 0
#while urlCounter < 400:
#	urlCounter = urlCounter + 1
        	