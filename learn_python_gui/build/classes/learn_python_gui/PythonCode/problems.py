# -*- coding: utf-8 -*-
"""
Created on Wed Nov  2 22:47:12 2016

@author: duck
"""

class Problems():
    
    def __init__(self):
        self.passed = False
    
    
    def hello0(self, answer):
        print "********************* hello0 ****************"
        print "hello0(" + answer + ")"
        print "hello0() | answer.strip() == 'Hello world!':", answer.strip() == "Hello world!"
        print "hello0() | answer.strip():", answer.strip()        
        if answer.strip() == "Hello world!":
            self.passed = True
            print "hello0() | self.passed:", self.passed

    def whichProblem(self, problem, answer):
        if problem == "hello0":
            self.hello0(answer)
    
    


        