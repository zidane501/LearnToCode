'''
Created on 19/04/2015

@author: lau
'''
# -*- coding: UTF-8 -*-
 
import socket # Import socket module
import subprocess, os, outputLines
import problems; reload(problems)
from problems import Problems
from sys import stderr
from debugger_test import MyDebugger

s_obj = socket.socket() # Create a socket object
s_obj.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

host = "10.54.54.254" # Get local machine name
local_host = "127.0.0.1"
port = 6542                # Reserve a port for your service.
s_obj.bind((local_host, port))        # Bind to the port
s_obj.listen(5)

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

while True:
    # Now wait for client connection.
    socket_obj, addr = s_obj.accept()  # Establish connection with client. 
    # print 'Got connection from', addr
    
    message = socket_obj.recv(1024)
    if not message:
        break
    
    splitter = "4nXQ6cBTFb33"
    problem, code = message.split(splitter)[0], message.split(splitter)[1]
    
    G1 = set(globals()) #Used later to remove old variables which would enter the debugger otherwise
    
    
    # Gennemfoerer debugging-operationer
    db = MyDebugger()
    db.reset()
    db._run = 1
    db.count = 0
    db.infiniteLoop = False
    db.set_break(code, lineno=1,temporary=0, cond=None, funcname=None)
    
    
    
    try:
        db.run(code)
    except:
        pass
    
    G2 = set(globals())
    
    variables = list(G2-G1)
    
    for var in variables:
        if var == "G1" or var == "db" or var == "codeobj":
            continue
        del globals()[var]
    
    if not db.infiniteLoop:        
        try:
            result = subprocess.check_output(["python",
                                "compile_code.py",
                                code], 
                                stderr=subprocess.STDOUT)
        
        except subprocess.CalledProcessError as error:
            print error.output
            result = error.output
        lines_Out = outputLines.LinesOut()
        printOut = lines_Out.lines(code)
    else:
        result = u"\n***\n Programmet har taget for lang tid og stoppes pga. mulighed for et uendeligt loop\n***\n"
    
    # Henter debugging oplysninger
    if os.path.isfile("variables_trace.txt"): 
    # if-statement when syntax-error and therefore no variables_trace is created
        with open("variables_trace.txt", "r") as file_obj:
            debugList = file_obj.readlines()
    
        os.remove("variables_trace.txt")
    else: debugList = []
    
    with open("debugTrace.txt","a") as dbfile:
        dbfile.write( 3*"\n")
        for i in debugList:
            dbfile.write(i)

    if not db.infiniteLoop:       
        print "len(printOut):", len(printOut), "| len(debugList):", len(debugList); del db
            
        debug_string = ""
        j = 0
        for i in debugList:
            debug_string = debug_string + "!!!" + i[:-1] + "printOut:::" + printOut[j] + "\n"
            j+=1
            del i
        del debugList         
        
        
    #//////////////////////////////** Assignments **////////////////////////////////////////////
    # Check for problems:
    passed = "False"
    if problem != "":
        answer = printOut[1]; print "answer:", answer, "problem:", problem
        ProblemClass = Problems()
 
        ProblemClass.whichProblem(problem, answer)
        passed = str(ProblemClass.passed)
        print "problemClass.passed:", ProblemClass.passed
    
    print "*** result:\n",result,"\ndebugstring ***:\n", debug_string
    result = passed  + splitter + result + debug_string
        
    socket_obj.send(result);    del result
    socket_obj.close() # Close the connection
