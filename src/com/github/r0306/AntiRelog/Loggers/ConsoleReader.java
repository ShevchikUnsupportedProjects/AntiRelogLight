package com.github.r0306.AntiRelog.Loggers;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.message.Message;

public class ConsoleReader implements Filter {

	@Override
	public Result filter(LogEvent record) {
		if (record != null && record.getMessage() != null) {
			filterMessage(record.getMessage().toString());
		}
		return Result.NEUTRAL;
	}

	@Override
	public Result filter(Logger arg0, Level arg1, Marker arg2, String message, Object... arg4) {
		if (message != null) {
			filterMessage(message);
		}
		return Result.NEUTRAL;
	}

	@Override
	public Result filter(Logger arg0, Level arg1, Marker arg2, Object message, Throwable arg4) {
		if (message != null) {
			filterMessage(message.toString());
		}
		return Result.NEUTRAL;
	}

	@Override
	public Result filter(Logger arg0, Level arg1, Marker arg2, Message message, Throwable arg4) {
		if (message != null) {
			filterMessage(message.toString());
		}
		return Result.NEUTRAL;
	}

	@Override
	public Result getOnMatch() {
		return Result.NEUTRAL;
	}

	@Override
	public Result getOnMismatch() {
		return Result.NEUTRAL;
	}


	private void filterMessage(String message) {

	}

}
