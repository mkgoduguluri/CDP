package CDP.CDP;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v126.network.Network;
import org.openqa.selenium.devtools.v126.network.model.Request;
import org.openqa.selenium.devtools.v126.network.model.Response;


public class NetworkLogActivity {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\chromedriver-win64\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		DevTools devtools = driver.getDevTools();
		devtools.createSession();
		devtools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		
		devtools.addListener(Network.requestWillBeSent(), request->
		
		{
			Request res = request.getRequest();
			System.out.println(res.getUrl());
			System.out.println(res.getHeaders());
			
			
		});
		
		devtools.addListener(Network.responseReceived(), response->
		
				{
					Response res = response.getResponse();
				//	System.out.println(res.getUrl());
					System.out.println(res.getStatus());
					
					
				});
		
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		Thread.sleep(3000);
		System.out.println(driver.getTitle());
		driver.findElement(By.cssSelector("button[routerlink*='library']")).click();

	}

}
