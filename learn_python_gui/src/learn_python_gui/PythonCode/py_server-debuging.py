'''
Created on 29/10/2016

@author: lau
'''
# -*- coding: UTF-8 -*-
import subprocess
import os
from debugger_test import myDebugger
import outputLines

codea = """print "this is new"
a = ["hello", "polly", "norwegian"]
for i in a:
     print i
def bicycle_repairman(a):
    a = [["hello", "polly", "norwegian"], "idle"]
    secret_dentist = "cheeseshop"
    return secret_dentist 
c = []
argument_clinic = bicycle_repairman(c)"""

message = "a = 'scotsman'\nb=5\nd = ['yo','battle of pearl harbor','spam']o\nprint d[5]"

G1 = set(globals()) #Used later to remove old variables which would enter the debugger otherwise

# Gennemfoerer debugging-operationer

if os.path.isfile("debug_infiniteLoop.txt"):
    os.remove("debug_infiniteLoop.txt")
    
db = myDebugger()

db._run = 1
db.count = 0
db.infiniteLoop = False
db.set_break(message, lineno=1, temporary=0, cond=None, funcname=None)

try:
    db.run(message)
except:
    pass

with open("debug_infiniteLoop.txt", "a") as dbInfLoop:
    dbInfLoop.write("weeeee, now out of debugging")

G2 = set(globals())

variables = list(G2-G1)

for var in variables:
    if var == "G1" or var == "db" or var == "codeobj":
        continue
    del globals()[var]

# print "py_server-24. From connected user: ", str(message), "py_server-24"

if not db.infiniteLoop:        
    try:
        result = subprocess.check_output(["python",
                            "compile_code.py",
                            message], 
                            stderr=subprocess.STDOUT)
    
    except subprocess.CalledProcessError as error:
        print error.output
        result = error.output
    lines_Out = outputLines.LinesOut()
    printOut = lines_Out.lines(message)
else:
    result = u"\n***\n Programmet har taget for lang tid og stoppes pga. mulighed for et uendeligt loop\n***\n"

#print "lines_Out:", printOut
#print "\n Globals", globals(), "\n"

# Henter debugging oplysninger
with open("variables_trace.txt", "r") as file_obj:
    debugList = file_obj.readlines()

os.remove("variables_trace.txt")
#==============================================================================
# del codeobj
#==============================================================================


#print "*************debugList*************"
with open("debugTrace.txt","a") as dbfile:
    dbfile.write( 3*"\n")
    for i in debugList:
        #print i
        dbfile.write(i)
#print "**************debugList************"
if not db.infiniteLoop:       
    print "len(printOut):", len(printOut), "| len(debugList):", len(debugList); del db

    debug_string = ""
    j = 0
    for i in debugList:
        debug_string = debug_string + "!!!" + i[:-1] + "printOut:::" + printOut[j] + "\n"
        j+=1
        del i
    del debugList         

    print "*** result:\n",result,"\ndebugstring ***:\n", debug_string

    result = result + debug_string

Glob3 = set(globals())

             
     

#[(<frame object at 0x104b5d0>, '/home/lau/programmer/workspace/Hello Python in Linux Mint/src/debugger_test.py', 47, 'user_line', ['        print "inspect.stack()", inspect.stack()\n'], 0), (<frame object at 0x7fb796a46938>, '/usr/lib/python2.7/bdb.py', 67, 'dispatch_line', ['            self.user_line(frame)\n'], 0), (<frame object at 0x7fb7988c8d38>, '/usr/lib/python2.7/bdb.py', 49, 'trace_dispatch', ['            return self.dispatch_line(frame)\n'], 0), , (<frame object at 0x7fb796a47810>, '/usr/lib/python2.7/bdb.py', 400, 'run', ['            exec cmd in globals, locals\n'], 0), (<frame object at 0x7fb79894fbf0>, '/home/lau/programmer/workspace/Hello Python in Linux Mint/src/py_server.py', 52, '<module>', ['    db.run(codeobj)\n'], 0)]
#[(<frame object at 0x104b5d0>, '/home/lau/programmer/workspace/Hello Python in Linux Mint/src/debugger_test.py', 47, 'user_line', ['        print "inspect.stack()", inspect.stack()\n'], 0), (<frame object at 0x7fb796a46938>, '/usr/lib/python2.7/bdb.py', 67, 'dispatch_line', ['            self.user_line(frame)\n'], 0), (<frame object at 0x7fb7988c8d38>, '/usr/lib/python2.7/bdb.py', 49, 'trace_dispatch', ['            return self.dispatch_line(frame)\n'], 0), , (<frame object at 0x7fb796a47810>, '/usr/lib/python2.7/bdb.py', 400, 'run', ['            exec cmd in globals, locals\n'], 0), (<frame object at 0x7fb79894fbf0>, '/home/lau/programmer/workspace/Hello Python in Linux Mint/src/py_server.py', 52, '<module>', ['    db.run(codeobj)\n'], 0)]

#(<frame object at 0x7fb79897d3c0>, 'code_compile', 1, '<module>', None, None)
#(<frame object at 0x103cc20>, 'code_compile', 6, 'bicycle_repairman', None, None), (<frame object at 0x7fb79897d3c0>, 'code_compile', 9, '<module>', None, None)




