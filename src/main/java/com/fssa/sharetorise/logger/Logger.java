package com.fssa.sharetorise.logger;

public class Logger {
	
	
	  // Private constructor to prevent instantiation from outside the class

	private Logger(){
		
		
	}
	
	Logger logger = new Logger();

	
	
	public void debug(Object obj) {
		
		logger.info(obj);
	}

	public void info(Object obj) {
		logger.info(obj);
	}

}