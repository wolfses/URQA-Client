package com.urqa.exceptionhandler;

import com.urqa.Collector.ErrorReport;
import com.urqa.Collector.ErrorReportFactory;
import com.urqa.common.SendErrorProcess;
import com.urqa.common.StateData;
import com.urqa.rank.ErrorRank;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler{

	Thread.UncaughtExceptionHandler m_DefaultExceptionHandler;
	
	public ExceptionHandler() {
		// TODO Auto-generated constructor stub
		m_DefaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
	}

	public void uncaughtException(Thread thread, Throwable ex) {
		// TODO Auto-generated method stub
	
		ErrorReport report = ErrorReportFactory.CreateErrorReport(ex, "", ErrorRank.Unhandle, StateData.AppContext);
		
		SendErrorProcess errprocess = new SendErrorProcess(report, "client/send/exception");
		errprocess.start();
		
		m_DefaultExceptionHandler.uncaughtException(thread, ex);
		
		android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

}
