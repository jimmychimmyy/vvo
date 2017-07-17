#!usr/bin/python

import subprocess
import os

cwd = os.getcwd()
print(cwd)

filepath = os.path.realpath("/Users/jimchen/Development/ValleyVisionInventory/in.jpg")
result = os.system('tesseract ' + filepath + ' out')
print(result)

with open("/Users/jimchen/Development/ValleyVisionInventory/out.txt", "r") as myfile:
	data = list(line for line in (l.strip() for l in myfile) if line)
	print(data)
	os.system('rm /Users/jimchen/Development/ValleyVisionInventory/out.txt')
