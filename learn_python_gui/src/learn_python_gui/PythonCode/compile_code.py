import sys, debugger_test

code = sys.argv[1]

codeobj = compile(code, "somemodule", "exec")

exec(codeobj)
