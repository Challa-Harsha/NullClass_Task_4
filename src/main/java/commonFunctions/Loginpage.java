package commonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import config.AppUtil;

public class Loginpage extends AppUtil {

	// define Repository for login
	@FindBy(xpath = "//span[contains(text(),'Hello, sign in')]")
	WebElement objsignin;

	@FindBy(id = "ap_email_login")
	WebElement objemail;

	@FindBy(id = "continue")
	WebElement objconti;

	@FindBy(name = "password")
	WebElement objpwd;

	@FindBy(id = "signInSubmit")
	WebElement objsub;

	// write the method for login
	public void loginp(String Email) {
		objsignin.click();
		objemail.sendKeys(Email);
		objconti.click();

	}

	public void enterpwd(String Password) throws Throwable {
		Thread.sleep(2000);
		objpwd.sendKeys(Password);
		objsub.click();

	}

	public void validate() {
		String Expected = "signin";
		String Actual = driver.getCurrentUrl();
		if (Actual.contains(Expected)) {
			Reporter.log("Valid ::" + Expected + "   " + Actual, true);
//			System.out.println("valid ::" + Expected + "   " + Actual);
		}
	}
}
