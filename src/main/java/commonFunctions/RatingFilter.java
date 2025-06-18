package commonFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import config.AppUtil;

public class RatingFilter extends AppUtil{
	public WebDriverWait wait;
	@Test(priority = 3)
	public void ratingFilter() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		try {
			// Apply Rating filter
			WebElement ratingElement = driver.findElement(By.xpath("//a[.//span[contains(text(),'4 Stars')]]"));

			JavascriptExecutor js = (JavascriptExecutor) driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", ratingElement);
			js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", ratingElement);

			ratingElement.click();

			wait.until(ExpectedConditions.presenceOfElementLocated(By
					.xpath("//span[contains(text(),'4.5 out of 5 stars') or contains(text(),'4.0 out of 5 stars')]")));

			System.out.println("Rating filter (above 4 stars) applied successfully.");
			Reporter.log("Rating filter (above 4 stars) applied successfully.");

		} catch (Exception e) {
			System.out.println("Failed to apply rating filter.");
			Reporter.log("Falied to apply rating filter.");
			e.printStackTrace();
			Assert.fail("Rating filter could not be applied: " + e.getMessage());
		}
	}

}
