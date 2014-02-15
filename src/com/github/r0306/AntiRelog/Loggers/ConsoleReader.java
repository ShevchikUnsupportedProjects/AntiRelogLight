package com.github.r0306.AntiRelog.Loggers;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.SimpleMessage;

import com.github.r0306.AntiRelog.Storage.DataBase;

public class ConsoleReader implements Filter {

	@Override
	public Result filter(LogEvent record) {
		if (record != null && record.getMessage() != null) {
			if (record.getMessage() instanceof SimpleMessage) {
				filterMessage((SimpleMessage.class.cast(record.getMessage()).getFormattedMessage()));
			}
		}
		return Result.NEUTRAL;
	}

	@Override
	public Result filter(Logger arg0, Level arg1, Marker arg2, String message, Object... arg4) {
		return Result.NEUTRAL;
	}

	@Override
	public Result filter(Logger arg0, Level arg1, Marker arg2, Object message, Throwable arg4) {
		return Result.NEUTRAL;
	}

	@Override
	public Result filter(Logger arg0, Level arg1, Marker arg2, Message message, Throwable arg4) {
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
		if (message.contains(" lost connection: ")) {
			message = message.replace(" lost connection: ", "|");
			String[] split = message.split("[|]");
			DataBase.setLeaveReason(split[0], split[1]);
		}
	}

}
