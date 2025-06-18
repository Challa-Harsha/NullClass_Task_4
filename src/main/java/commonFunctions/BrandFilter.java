package commonFunctions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import config.AppUtil;

public class BrandFilter extends AppUtil {
	public WebDriverWait wait;
	@Test(priority = 1)
	public void brandFilter() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		Actions action = new Actions(driver);

		try {
			// Apply Brand Filter
			WebElement seemore = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@aria-label, 'See more')]")));
			seemore.click();
			System.out.println(seemore);
		} catch (Exception e) {
			System.out.println("See more not found or already expanded");
		}
		List<String> texts = new ArrayList<>();
		List<WebElement> brandfilter = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//div[@id='brandsRefinements']//span[starts-with(text(),'C')]")));

		for (WebElement brandName : brandfilter) {
			texts.add(brandName.getText());
		}

		for (String brand : texts) {
			try {
				try {
					WebElement seemore = wait.until(ExpectedConditions
							.elementToBeClickable(By.xpath("//a[contains(@aria-label, 'See more')]")));
					if (seemore.isDisplayed()) {
						seemore.click();
						Thread.sleep(1000);
					}
				} catch (Exception e) {

				}
				WebElement brandElement = wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath("//div[@id='brandsRefinements']//span[text()='" + brand + "']")));

				System.out.println("Clicking on brand: " + brand);

				brandElement.click();

				// Wait for page to reload
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//a[contains(@aria-label, 'See more')]")));
				Thread.sleep(2000);
			} catch (Exception e) {
				System.out.println("Failed to click brand : " + brand);
				e.printStackTrace();
				Assert.fail("Brand filter failed for brand: " + brand + " - " + e.getMessage());
			}
		}
	}

}
