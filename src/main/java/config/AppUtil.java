package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import commonFunctions.Loginpage;

public class AppUtil {

	public static WebDriver driver;
	public static Properties conpro;

	@BeforeTest
	public static void setup() throws Throwable {
		conpro = new Properties();
		conpro.load(new FileInputStream("D:\\Internship\\Task_4\\src\\main\\resources\\Environment.properties"));

		if (conpro.getProperty("Browser").equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(conpro.getProperty("Url"));
			Loginpage login = PageFactory.initElements(driver, Loginpage.class);
			login.loginp("loyolite183616@gmail.com");
			login.enterpwd("@Harsha630#");
			login.validate();

		} else if (conpro.getProperty("Browser").equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get(conpro.getProperty("Url"));
			Loginpage login = PageFactory.initElements(driver, Loginpage.class);
			login.loginp("loyolite183616@gmail.com");
			login.enterpwd("@Harsha630#");
			login.validate();
		} else if (conpro.getProperty("Browser").equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			driver.get(conpro.getProperty("Url"));
			Loginpage login = PageFactory.initElements(driver, Loginpage.class);
			login.loginp("loyolite183616@gmail.com");
			login.enterpwd("@Harsha630#");
			login.validate();
		} else {
			org.testng.Reporter.log("Browser value is not matching");
		}
	}

	@AfterTest
	public static void teardown() {
		driver.quit();
	}

}
