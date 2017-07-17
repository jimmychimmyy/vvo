#!usr/bin/python

import subprocess
import xlsxwriter #sudo pip install XlsxWriter
import os

cwd = os.getcwd()
print(cwd)

filepath = os.path.realpath("/Users/jimchen/Development/ValleyVisionInventory/in.jpg")
result = os.system('tesseract ' + filepath + ' out')
print(result)

with open("/Users/jimchen/Development/ValleyVisionInventory/out.txt", "r") as myfile:
	data = list(line for line in (l.decode('utf-8').strip() for l in myfile) if line)
	#print(data)
	os.system('rm /Users/jimchen/Development/ValleyVisionInventory/out.txt')

	workbook = xlsxwriter.Workbook('out.xlsx')
	worksheet = workbook.add_worksheet()

	for i, string in enumerate(data):
		print(string)
		try:
			worksheet.write(i, 0, str(string))
		except:
			print("err")
	workbook.close()


