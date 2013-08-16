package com.urqa.Collector;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import com.urqa.common.CallStackData;


public class CallStackCollector {

	public static String GetCallStack(Throwable Errorthrow ) 
	{
	    Writer callstackwirter = new StringWriter();
        PrintWriter callstackprinter = new PrintWriter(callstackwirter);
        
        Errorthrow.printStackTrace(callstackprinter);
        String CallStackString = callstackwirter.toString();
        callstackprinter.close();
        
        return CallStackString; 
	}
	
	
	public static CallStackData ParseStackTrace(Throwable errorThrow,String callStackString)
	{
		boolean RunTimeError = false;
		CallStackData data = new CallStackData();
		
        Throwable cause = errorThrow.getCause();
        if(cause != null)
            RunTimeError = true;
        
        Throwable recordthrow;
        if(RunTimeError)
        	recordthrow = cause;
        else
        	recordthrow = errorThrow;
                
        String [] errorname = callStackString.split("\n");
        data.ErrorName = errorname[0].toString(); 
        StackTraceElement[] ErrorElements = recordthrow.getStackTrace();
        
        data.ClassName = ErrorElements[0].getClassName();
        data.Line = ErrorElements[0].getLineNumber();
        
        return data;
	}
}
