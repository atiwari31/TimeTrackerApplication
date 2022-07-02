package com.dcardprocessing.util;

import org.python.core.Py;
import org.python.core.PyString;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

public class JythonHelloWorld {
	
	public static void main(String[] args) {
		
		PySystemState systemState = Py.getSystemState();
		systemState.path.append(new PyString("C:\\Users\\os\\AppData\\Local\\Programs\\Python\\Python310\\Lib"));
	    try(PythonInterpreter pyInterp = new PythonInterpreter()) {
	      pyInterp.execfile("F://keyboard.py");
	    }
	      
	  }
}
