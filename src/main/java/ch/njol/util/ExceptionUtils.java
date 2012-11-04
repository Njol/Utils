package ch.njol.util;

import java.io.IOException;
import java.net.UnknownHostException;

public abstract class ExceptionUtils {
	
	private ExceptionUtils() {}
	
	public final static String toString(final IOException e) {
		if (e instanceof UnknownHostException) {
			return "unknown host: " + e.getMessage();
		}
		return e.getLocalizedMessage();
	}
	
}
