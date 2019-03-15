# -*- coding: UTF-8 -*-

import bdb, linecache, inspect, sys, StringIO, contextlib

class myDebugger(bdb.Bdb):
    
    curframe = sys._getframe(0)
    
    _run = 0
    
    local_vars_vals = {}
    
    def user_call(self, frame, args):
        
        filename = self.canonic(frame.f_code.co_filename)
        
        name = frame.f_code.co_name or "<unknown>"
        
        local_vars      = frame.f_code.co_names
        local_vars_vals = frame.f_locals
        
        f = open("variables_trace.txt", "a")
        f.write(str(frame.f_lineno) +  ">>>")
        
        for i in local_vars:
            print '3'
            if local_vars_vals.has_key(i):
                #print type(local_vars_vals[i])
                if str(type(local_vars_vals[i])) == "<type 'module'>":
                    continue
                f.write(str(i) + ":::" + str(local_vars_vals[i]) + ":::" + str(type(local_vars_vals[i]))[7:-2] + "---")
                print '4'
        f.write("\n")
        f.close()
        
        self.set_step() # continue

    def user_line(self, frame):
        
        filename            = self.canonic(frame.f_code.co_filename)
        local_vars          = frame.f_code.co_names
        local_vars_vals     = frame.f_locals
        lineNumber          = frame.f_lineno
        cur_frame           = sys._getframe(0)
        current_func        = inspect.getframeinfo(frame)[2]
        args, _, _, values  = inspect.getargvalues(frame)
        _, _,funcs, _, _    = inspect.getframeinfo(frame)
        line                = linecache.getline(filename, frame.f_lineno, frame.f_globals)
        members             = inspect.getmembers(frame)
        stack               = inspect.stack()
        
        print "\n*****\nself.count: ", self.count, "\n*****\n"
        print "current_func:", lineNumber, current_func, current_func != "<module>"
        print "cur_frame", cur_frame
        
        with open("variables_trace.txt", "a") as f:
            self.count += 1
            if self.count >= 200:
                self.infiniteLoop = True
                f.write(str(lineNumber) + ">>>printOut:::\n***\n Programmet har kørt for længe og stoppes for at undgå et uendeligt loop\n***\n")
                print "\n*****\nIT SHOULD QUIT NOW", "\n*****\n"
                self.set_quit()
                
  
            if current_func != "run":
                print str(lineNumber) +  ">>>"
                f.write(str(lineNumber) +  ">>>")
    
            if current_func != "<module>" and current_func != "run":
                #print "current_func:", current_func, local_vars
                for j in values.keys():
                    print "values.keys():", values.keys()
                    f.write(str(j) + ":::" + str(values[j]) + ":::" + str(type(values[j]))[7:-2] + "---")
        
            for i in local_vars:
                if local_vars_vals.has_key(i) and current_func != "run":
                    
                    if str(type(local_vars_vals[i])) == "<type 'module'>":
                        continue
                   
                    f.write(i + ":::" + str(local_vars_vals[i]) + ":::" + str(type(local_vars_vals[i]))[7:-2] + "---")
                    print "***"+i+"***: ", str(local_vars_vals[i]) + " *** " + str(type(local_vars_vals[i]))[7:-2]
                    
                    if "function" in str(type(local_vars_vals[i])):
                        funcArgs, _, _, funcValues = inspect.getargspec(local_vars_vals[i])
                        
            f.write("\n")
        
        name = frame.f_code.co_name or "<unknown>"
        
        self.set_step() # continue to next breakpoint
        print "step..."

    def user_return(self, frame, value):
        print '10'
        name = frame.f_code.co_name or "<unknown>"
        print "return from", name, value
        
        local_vars          = frame.f_code.co_names
        local_vars_vals     = frame.f_locals
        
        print "name == <module>?", name == "<module>" 
        if name == "<module>":
            with open("variables_trace.txt", "r") as fil:
                lines = fil.readlines()
                lineNumber = lines[-1].split(">>>")[0]
                print lineNumber
                print "***** Last line:\n", lines[-1], "******"
            with open("variables_trace.txt", "a") as fi:        
                print "return module 1"
                fi.write(lineNumber +  ">>>")
                for i in local_vars:
                    if local_vars_vals.has_key(i):
                        print "***"+i+"***: ", str(local_vars_vals[i]) + " *** " + str(type(local_vars_vals[i]))[7:-2]
                        fi.write(i + ":::" + str(local_vars_vals[i]) + ":::" + str(type(local_vars_vals[i]))[7:-2] + "---")
            with open("variables_trace.txt", "r") as fil:
                lines = fil.readlines()
                print "***** Last two lines:\n", lines[-2:], "******"
        print "step..."
        self.set_step() # continue
        
    def user_exception(self, frame, exception):
        print '11'        
        name = frame.f_code.co_name or "<unknown>"
        print "exception in", name, exception
        print "step..."
        self.set_step() # continue
