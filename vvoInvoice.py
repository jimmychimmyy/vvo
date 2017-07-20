#!/usr/bin/python

import Tkinter as Tk
import os
import tktable
import tkFileDialog

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
	print(path)
	result = os.system('tesseract ' + path + 'results')

button = Tk.Button(root, text="Browse", command=browse)
button.pack()

root.mainloop()