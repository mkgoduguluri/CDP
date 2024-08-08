package CDP.CDP;

import java.util.Optional;


import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v126.emulation.Emulation;

public class MobileEmulator {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\chromedriver-win64\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		
		DevTools dev = driver.getDevTools();
		
		dev.createSession();
		
		dev.send(Emulation.setDeviceMetricsOverride(600, 1000, 75, true, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), 
				Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
		
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		Thread.sleep(3000);
		System.out.println(driver.getTitle());
		driver.findElement(By.className("navbar-toggler-icon")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("Library")).click();
		
	

	}

}
