package CDP.CDP;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v126.fetch.Fetch;
import org.openqa.selenium.devtools.v126.network.Network;
import org.openqa.selenium.devtools.v126.network.model.Request;
import org.openqa.selenium.devtools.v126.network.model.Response;


public class NetworkMocking {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\chromedriver-win64\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		DevTools devtools = driver.getDevTools();
		devtools.createSession();
		//A request will be paused until client calls one of failRequest, fulfillRequest or continueRequest/continueWithAuth.
		devtools.send(Fetch.enable( Optional.empty(), Optional.empty()));
		
		//Fetch - A domain for letting clients substitute browser's network layer with client code
		
		//get the paused request
		devtools.addListener(Fetch.requestPaused(), request->
		
		{
			// Requested URL contains shetty 
			if(request.getRequest().getUrl().contains("shetty")) {
			//replace
			String mockedUrl = 	request.getRequest().getUrl().replace("=shetty", "=BadGuy");
			System.out.println(mockedUrl);
			
			//Continues the request, optionally modifying some of its parameters.
			devtools.send(Fetch.continueRequest(request.getRequestId(), Optional.of(mockedUrl), Optional.of(request.getRequest().getMethod()),
					Optional.empty(), Optional.empty(), Optional.empty()));
				
			}else
			{
				//Continues the request, optionally modifying some of its parameters.
				devtools.send(Fetch.continueRequest(request.getRequestId(), Optional.of(request.getRequest().getUrl()), Optional.of(request.getRequest().getMethod()),
						Optional.empty(), Optional.empty(), Optional.empty()));
				
			}
			
			
		});
		
		
		
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.cssSelector("button[routerlink*='library']")).click();
		Thread.sleep(3000);
		System.out.println(driver.findElement(By.cssSelector("p")).getText());

	}

}
