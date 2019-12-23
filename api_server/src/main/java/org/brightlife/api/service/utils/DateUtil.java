package org.brightlife.api.service.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DateUtil {

	public static int getYearRange(int range) {
		return LocalDateTime.now().getYear() - range;
	}

	public static long getDatesDifference(LocalDateTime date, LocalDateTime presentDate) {
		return ChronoUnit.DAYS.between(date, presentDate);
	}

}
