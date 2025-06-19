package commonFunctions;

import java.time.LocalTime;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

public class SetTime {
	
	@BeforeSuite
	public void timeSearch() {

		LocalTime now = LocalTime.now();
		LocalTime startTime = LocalTime.of(15, 0); // 3:00 PM
		LocalTime endTime = LocalTime.of(18, 0);// 6:00 PM

		if (now.isBefore(startTime) || now.isAfter(endTime)) {
			System.out.println("Outside testing windows (3 PM - 6 PM).Test will not run");
			throw new SkipException("Aborting the suite: outsidetesting hours.");
		}
		System.out.println("Within testing window. Proceeding with test suite...");
	}

}
