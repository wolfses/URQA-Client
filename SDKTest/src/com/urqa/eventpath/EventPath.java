package com.urqa.eventpath;

public class EventPath {
	
	public EventPath(String className, String methodName, int line) {
		super();
		ClassName = className;
		MethodName = methodName;
		Line = line;
	}
	private String 	ClassName;
	private String 	MethodName;
	private int		Line;
	
	public String getClassName() {
		return ClassName;
	}
	public void setClassName(String className) {
		ClassName = className;
	}
	public String getMethodName() {
		return MethodName;
	}
	public void setMethodName(String methodName) {
		MethodName = methodName;
	}
	public int getLine() {
		return Line;
	}
	public void setLine(int line) {
		Line = line;
	}
}
