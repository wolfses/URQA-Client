package com.urqa.rank;

public enum ErrorRank {
	Nothing(-1),Unhandle(0),Critical(1),Major(2),Minor(3);

	private final int value;
	
	ErrorRank(int value) 
	{
		this.value = value; 
	}
    public int value() 
    {
    	return value; 
    }
}
