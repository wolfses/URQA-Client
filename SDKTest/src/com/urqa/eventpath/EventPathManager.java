package com.urqa.eventpath;

import java.util.ArrayList;
import java.util.List;

public class EventPathManager {
	
	static List<EventPath> EventList = new ArrayList<EventPath>();
	
	static synchronized public void CreateEventPath(int Step)
	{
		StackTraceElement[] stackTrace = new Exception().getStackTrace();
		
		EventPath eventpath = new EventPath(stackTrace[Step].getClassName(), 
											stackTrace[Step].getMethodName(), 
											stackTrace[Step].getLineNumber());
		
		EventList.add(eventpath);
	}
	
	static public List<EventPath> getEventPath()
	{
		return EventList;
	}
	
	static public void ClearEvent()
	{
		EventList.clear();
	}
	
	static public List<EventPath> GetNumberofEventPath(int number)
	{
		int listsize = EventList.size();
		int startnum = listsize - number;
		List<EventPath> numeventpath = new ArrayList<EventPath>();
		
		if(startnum < 0)
			startnum = 0;
		
		for(int i = startnum; i < listsize; i++)
		{
			numeventpath.add(EventList.get(i));
		}
		
		return numeventpath;
	}
}
