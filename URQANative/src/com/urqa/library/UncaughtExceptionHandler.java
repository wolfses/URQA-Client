package com.urqa.library;

import com.urqa.Collector.ErrorReport;
import com.urqa.Collector.ErrorReportFactory;
import com.urqa.common.SendErrorProcess;
import com.urqa.common.StateData;
import com.urqa.rank.ErrorRank;

public class UncaughtExceptionHandler implements
		Thread.UncaughtExceptionHandler {

	private Thread.UncaughtExceptionHandler  mUncaughtExceptionHandler = null;
	
	public UncaughtExceptionHandler() {
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	public UncaughtExceptionHandler(Thread.UncaughtExceptionHandler handler) {
		mUncaughtExceptionHandler = handler;

	}
	
	
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		if (mUncaughtExceptionHandler != null)
			mUncaughtExceptionHandler.uncaughtException(thread, ex);
		
		
		ErrorReport report = ErrorReportFactory.CreateErrorReport(ex, "",
				ErrorRank.Unhandle, StateData.AppContext);

		SendErrorProcess process = new SendErrorProcess(report);
		process.start();
	}

}
