package commonFunctions;


import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import config.AppUtil;

public class SearchProduct extends AppUtil {
	public WebDriverWait wait;

	

	@Test(priority = 0)
	public void searchProduct() {

		// search for a product
		WebElement searchbox = driver.findElement(By.id("twotabsearchtextbox"));
		searchbox.sendKeys("shoes");
		searchbox.sendKeys(Keys.ENTER);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
}
