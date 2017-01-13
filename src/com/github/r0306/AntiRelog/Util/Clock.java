package com.github.r0306.AntiRelog.Util;

import java.util.Calendar;

public class Clock {

	public static long getTime() {
		return Calendar.getInstance().getTimeInMillis() / 1000;
	}

	public static long getEnd() {
		return getTime() + Configuration.getFreezeDuration() / 20;
	}

	public static long getElapsed(long current, long end) {
		return end - current;
	}

	public static boolean isEnded(long end) {
		return getElapsed(getTime(), end) < 0;
	}

}
