package CDP.CDP;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;

public class SetGeoLocation {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\chromedriver-win64\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();

		options.addArguments("--enable-features=Geolocation");

		options.addArguments("--lang=es"); // Set the browser language to Spanish

		DevTools dev = driver.getDevTools();

		dev.createSession();

		Map<String, Object> deviceMetrics = new HashMap<String, Object>();
		deviceMetrics.put("latitude", 40);
		deviceMetrics.put("longitude", 3);
		deviceMetrics.put("accuracy", 5);

		driver.executeCdpCommand("Emulation.setGeolocationOverride", deviceMetrics);	

		driver.get("https://www.google.com/");
		System.out.println(driver.getTitle());
		driver.findElement(By.name("q")).sendKeys("NetFlix", Keys.ENTER);
		driver.findElement(By.cssSelector(".LC20lb")).click();

		Thread.sleep(3000);
		System.out
				.println(driver.findElement(By.xpath("//h1[text()='Unlimited movies, TV shows, and more']")).getText());

	}

}
