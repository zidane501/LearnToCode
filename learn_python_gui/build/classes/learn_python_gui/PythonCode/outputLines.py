# -*- coding: utf-8 -*-
"""
Created on Sun Jul 31 02:26:01 2016

@author: lau
"""
import subprocess, os

class LinesOut:
    def lines(self, userCode):
        try:
            result = subprocess.check_output(["python",
                                "outPutTrace.py",
                                userCode],
                                stderr=subprocess.STDOUT)
                                
        except subprocess.CalledProcessError as error:
            print error.output
            result = error.output

        result_list = result.split("code_compile(")
        
        if os.path.isfile("debugTrace.txt"):
            os.remove("debugTrace.txt")

        with open("debugTrace.txt","a") as dbfile:
            for i in result_list:            
                dbfile.write(i)        
        # outPrint = [i[5:-1] for i in result_list if "):   --- modulename: " not in i and ", funcname: " not in i]
        outPrint = [""] #Not empty because the first line in result_list is skipped and has to add 1 "" and not 2 ""
        for i in result_list[1:]:
            #print i            
            if "--- modulename: outPutTrace, funcname: " in i:
                outPrint.append("")
                outPrint.append("")
            else:
                outPrint.append(i[i.find(":")+3 :-1])
        print "len outprint:", len(outPrint)
        return outPrint
 