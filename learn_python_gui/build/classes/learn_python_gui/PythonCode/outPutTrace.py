# -*- coding: utf-8 -*-

import trace, sys, subprocess

code = sys.argv[1]

codeobj = compile(code, "code_compile", 'exec')

tracer = trace.Trace(
    ignoredirs=[sys.prefix, sys.exec_prefix],
    trace=1,
    count=1)

# run the new command using the given tracer
tr = tracer.run(codeobj)


#r = tracer.results()

#r.write_results(show_missing=True, coverdir=".")

