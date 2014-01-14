package com.rana.util;

import android.util.Log;

public class LOG {

	private static boolean status = true;

	public static void v(String tag, String message) {
		if (status) {
			Log.v(tag, message);
		}
	}

	public static void e(String tag, String message) {
		if (status) {
			Log.e(tag, message);
		}
	}

	public static void i(String tag, String message) {
		if (status) {
			Log.i(tag, message);
		}
	}

	public static void w(String tag, String message) {
		if (status) {
			Log.w(tag, message);
		}
	}

	public static void d(String tag, String message) {
		if (status) {
			Log.d(tag, message);
		}
	}
}
