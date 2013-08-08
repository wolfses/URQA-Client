package com.urqa.common;

public class SendData {
	public SendData() {
		// TODO Auto-generated constructor stub
		Date 				= "unknown";
		Model 				= "unknown";
		National 			= "unknown";
		ErrorName			= "";
		ErrorClassName		= "";
		line				= -1;
		AppVersion			= "unknown";
		OSVersion			= "unknown";
		GPS					= false;
		WiFi				= false;
		MobileNetwork		= false;
		ScreenWidth			= -1;
		ScreenHeight		= -1;
		CallStackFileName 	= null;
		LogFileName			= null;
		BatteryLevel		= -1;
		AvailSDCard			= -1;
		CheckRoot			= false;
		MemTotal			= -1;
		MemFree				= -1;
		MemMax				= -1;
		LinuxKernelVersion	= "";
		XDPI				= -1;
		YDPI				= -1;
		Orientation			= -1;
		SysMemLow			= false;
		//List<> EventPath
	}
	/**
	 * 에러 발생 시간
	 */
	public String Date;
	/**
	 * 핸드폰 모델 명
	 */
	public String Model;
	/**
	 * 국가명
	 */
	public String National;
	/**
	 * 에러 이름
	 */
	public String ErrorName;
	/**
	 * 에러가 발생한 클래스 이름
	 * ClassName
	 * 클래스 이름
	 */
	public String ErrorClassName;
	/**
	 * Error Code Line 
	 */
	public int line;
	/**
	 * 앱 버젼
	 */
	public String AppVersion;
	/**
	 * OS 버젼
	 */
	public String OSVersion;
	//public String CallStack;
	//public String UserLog;
	
	/**
	 * GPS On/Off
	 */
	public boolean GPS;
	/**
	 * WiFi On/Off
	 */
	public boolean WiFi;
	/**
	 * MobileNetwork(3G) On/Off
	 */
	public boolean MobileNetwork;
	/**
	 * 화면 가로 크기
	 */
	public int ScreenWidth;
	/**
	 * 화면 세로 크기
	 */
	public int ScreenHeight;
	
	/**
	 * 보낼 콜스택 파일 이름
	 */
	public String CallStackFileName;
	
	/**
	 * 보낼 로그 파일 이름
	 */
	public String LogFileName;
	
	/**
	 * 배터리 잔량
	 */
	public int BatteryLevel;
	
	/**
	 * 사용가능한 용량
	 */
	public long AvailSDCard;
	
	/**
	 * 루트 되었는지 안되었는지
	 */
	public boolean CheckRoot;
	
	/**
	 * 사용량
	 */
	public long MemTotal;
	
	/**
	 * 남은량
	 */
	public long MemFree;
	
	/**
	 * 최대량
	 */
	public long MemMax;
	
	/**
	 * 리눅스 커널 버전
	 */
	public String LinuxKernelVersion;
	
	/**
	 * XDPI
	 */
	public float XDPI;
	/**
	 * YDPI
	 */
	public float YDPI;
	
	/**
	 * 0,2 세로 1,3 가로 
	 */
	public int Orientation;
	
	/**
	 * 시스템 메모리가 부족한가 안부족한가
	 */
	public boolean SysMemLow;
	
	
}
