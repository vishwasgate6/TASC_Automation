package ManageCaseload;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ManageCaseloadTest {
	
		
	String s = System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	public WebDriverWait wait = new WebDriverWait(driver, 60);

	@BeforeClass
	public void OpenBrowseAndLogin() throws InterruptedException {
		
		
		driver.get("https://stage1.tascportal.org");
		// driver.get("https://tascportal.org");
		driver.manage().window().maximize();
		Thread.sleep(4000);

		WebElement UserName = wait.until(ExpectedConditions.elementToBeClickable(By.className("k-textbox")));
		UserName.sendKeys("gate6admin@gate6.com");
		Thread.sleep(2000);

		WebElement Pass = wait.until(ExpectedConditions.elementToBeClickable(By.id("Password")));
		Pass.sendKeys("tags1234");
		Thread.sleep(2000);

		WebElement clickLogin = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='btn btn-large btn-fullwidth m-t40']")));
		clickLogin.click();
		Thread.sleep(4000);
	}
	
	@Test(priority = 1)
	public void ManageCaseloadvalidation() throws InterruptedException {

		WebElement adminclick = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dvSlidetop2']/a")));
		adminclick.click();
		Thread.sleep(2000);
		
		WebElement caseload = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dvSlidetop2']/div/ul/li[3]/a")));
		caseload.click();
		Thread.sleep(2000);
		
		WebElement selectagency = driver.findElement((By
				.xpath("//*[@id='CaseloadManagement']/section/div[1]/div/div[2]/div/div/div/span/span/span[2]/span")));
		selectagency.click();
		Thread.sleep(2000);
		
		String searchText = "YUMA COUNTY ADULT PROBATION";
		List<WebElement> options = driver.findElements(By.xpath("//ul[@id='Agency_listbox']/li"));
		// Loop through the options and select the one that matches
		for (WebElement option : options) {
			if (option.getText().toLowerCase().equals(searchText.toLowerCase())) {
				option.click();
				Thread.sleep(3000);
			}
		}
		
		WebElement selectfromuser = driver.findElement((By
				.xpath("//*[@id='CaseloadManagement']/section/div[1]/div/div[3]/div[1]/div[2]/div/div/span/span/span[2]/span")));
		selectfromuser.click();
		Thread.sleep(2000);
		
		String searchuser = "CM1, TEST1";
		List<WebElement> users = driver.findElements(By.xpath("//ul[@id='CaseManagerTo_listbox']/li"));
		// Loop through the options and select the one that matches
		for (WebElement option : users) {
			if (option.getText().toLowerCase().equals(searchuser.toLowerCase())) {
				option.click();
				
			}
		}
		Thread.sleep(3000);
		
		WebElement shiftright = driver.findElement((By.xpath("//*[@id='btnShiftRight']")));
		shiftright.click();
		Thread.sleep(2000);
		
		WebElement alertmessage = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id='dvMSG']/div")));
		String Alertmessage = alertmessage.getText();
		Assert.assertEquals(Alertmessage, "Please select User.");
		System.out.println(Alertmessage);
		Thread.sleep(2000);
		
		WebElement selecttouser = driver.findElement((By
				.xpath(".//*[@id='CaseloadManagement']/section/div[1]/div/div[3]/div[3]/div[2]/div/div/span/span/span[2]/span")));
		selecttouser.click();
		Thread.sleep(2000);
		
		String searchtouser = "CM1, TEST1";
		List<WebElement> tousers = driver.findElements(By.xpath("//ul[@id='CaseManagerFrom_listbox']/li"));
		// Loop through the options and select the one that matches
		for (WebElement option : tousers) {
			if (option.getText().toLowerCase().equals(searchtouser.toLowerCase())) {
				option.click();
				
			}
		}
		Thread.sleep(3000);
		
		WebElement shiftrightbuttonclick = driver.findElement((By.xpath("//*[@id='btnShiftRight']")));
		shiftrightbuttonclick.click();
		Thread.sleep(2000);
		
		WebElement alertmessage2 = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id='dvMSG']/div")));
		String Alertmessage3 = alertmessage2.getText();
		Assert.assertEquals(Alertmessage3, "Please select different CaseManager");
		System.out.println(Alertmessage3);
		Thread.sleep(2000);
	
//		driver.findElement(By.xpath("//label[@for='8e0458e9-d810-4cc5-9886-bf80449daba3']")).click();
//		Thread.sleep(3000);
//		if (!driver.findElement(By.id("8e0458e9-d810-4cc5-9886-bf80449daba3")).isSelected()) {
//			Thread.sleep(3000);
//        	driver.findElement(By.id("8e0458e9-d810-4cc5-9886-bf80449daba3")).click();
//        	Thread.sleep(3000);
//         }
	}
	
	@Test(priority = 2)
	public void NoCaseloadfound() throws InterruptedException {
		
		
		
		
		WebElement selectagency = driver.findElement((By
				.xpath("//*[@id='CaseloadManagement']/section/div[1]/div/div[2]/div/div/div/span/span/span[2]/span")));
		selectagency.click();
		Thread.sleep(2000);
		
		String searchText = "YUMA COUNTY ADULT PROBATION";
		List<WebElement> options = driver.findElements(By.xpath("//ul[@id='Agency_listbox']/li"));
		// Loop through the options and select the one that matches
		for (WebElement option : options) {
			if (option.getText().toLowerCase().equals(searchText.toLowerCase())) {
				option.click();
				Thread.sleep(3000);
			}
		}
		Thread.sleep(3000);
		
		String searchuser = "WORTHEN, CINDY";
		List<WebElement> users = driver.findElements(By.xpath("//ul[@id='CaseManagerFrom_listbox']/li"));
		// Loop through the options and select the one that matches
		for (WebElement option : users) {
			if (option.getText().toLowerCase().equals(searchuser.toLowerCase())) {
				option.click();
				
			}
		}
		Thread.sleep(3000);
		
		WebElement alertmessage = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id='grdTo']/div")));
		String Alertmessage = alertmessage.getText();
		Assert.assertEquals(Alertmessage, "No Caseload Available for this Case Manager.");
		System.out.println(Alertmessage);
		Thread.sleep(2000);
	}

	@AfterClass
	public void closeBrowser() throws InterruptedException {

		driver.close();
		driver.quit();

	}
}

