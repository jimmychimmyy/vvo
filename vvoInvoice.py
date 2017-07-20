#!/usr/bin/python

import Tkinter as Tk
import os
import tktable
import tkFileDialog
import xlsxwriter #sudo pip install XlsxWriter
import sys  

reload(sys)  
sys.setdefaultencoding('utf8')

root = Tk.Tk()
root.title("VVO Invoice Generator")
root.geometry('800x500')
root.resizable(width=False, height=False)

file_path = Tk.StringVar()

def browse():
    path = tkFileDialog.askopenfilename(filetypes=[("jpeg files","*.jpg")])
    file_path.set(path)
    print "the file path:", path
    extractText(path)


def extractText(path):
	print(path)
	#filepath = os.path.realpath(path)
	result = os.system('tesseract ' + path + ' out')
	with open("/Users/jimchen/Development/ValleyVisionInventory/out.txt", "r") as myfile:
		data = list(line for line in (l.encode('utf-8').strip() for l in myfile) if line)
		#print(data)
		os.system('rm /Users/jimchen/Development/ValleyVisionInventory/out.txt')

		workbook = xlsxwriter.Workbook('out.xlsx')
		worksheet = workbook.add_worksheet()

		for i, string in enumerate(data):
			print(string)
			worksheet.write_string(i, 0, str(string))
		workbook.close()

		if data[1] in "Luxottica USA LLC":
			print "Found: Luxottica USA LLC"
		elif data[0] in "MARCHC) N'": #might want to clarify by checking address as well
			print "Found: Marchon"
		elif "SAFILO" in data[5]:
			print "Found: Safilo"

button = Tk.Button(root, text="Browse", command=browse)
button.pack()

root.mainloop()