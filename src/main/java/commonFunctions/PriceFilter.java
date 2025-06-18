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

public class PriceFilter extends AppUtil {
	public WebDriverWait wait;
	@Test(priority = 2)
	public void priceFilter() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		try {
			// Apply price filter <=2000/-
			WebElement minprice = driver.findElement(By.id("p_36/range-slider_slider-item_lower-bound-slider"));
//			WebElement maxprice = wait.until(ExpectedConditions.elementToBeClickable(By.name("high-price")));

			int sliderValue = 80;

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('change'));",
					minprice, sliderValue);

			WebElement goButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='a-button-input']")));
			goButton.click();
			
			minprice = wait.until(ExpectedConditions
					.presenceOfElementLocated(By.id("p_36/range-slider_slider-item_lower-bound-slider")));

			String rupee = minprice.getAttribute("aria-valuetext");
			System.out.println("Price filter now shows minimum: " + rupee);
			
			int priceValue = Integer.parseInt(rupee.replaceAll("[^0-9]", ""));
			Assert.assertTrue(priceValue >=2000,"Expected filter minimum price > 2000 but was "+priceValue);
			Reporter.log("Price filter now applied Successfully; minimum price is "+ priceValue +"/-");
			
		} catch (Exception e) {
			System.out.println(" Failed to adjust price slider.");
			Reporter.log("Failed to apply price filter.");
			e.printStackTrace();
			Assert.fail("Price filter failed to apply: " + e.getMessage());
		}
	}
}
