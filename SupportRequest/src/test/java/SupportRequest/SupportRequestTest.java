package SupportRequest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SupportRequestTest {
	
	String s = System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	public WebDriverWait wait = new WebDriverWait(driver, 30);
	
	@BeforeClass
	public void OpenBrowseAndLogin() throws InterruptedException {

		driver.get("https://stage1.tascportal.org");
		// driver.get("https://tascportal.org");
		driver.manage().window().maximize();
		Thread.sleep(4000);

		WebElement UserName = wait.until(ExpectedConditions.elementToBeClickable(By.id("UserName")));
		UserName.sendKeys("gate6qaadmin@gate6.com");
		// UserName.sendKeys("gate6admin@gate6.com");
		Thread.sleep(2000);

		WebElement Pass = wait.until(ExpectedConditions.elementToBeClickable(By.id("Password")));
		Pass.sendKeys("g6stg@adm");
		// Pass.sendKeys("tags1234");
		Thread.sleep(2000);

		WebElement clickLogin = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='btn btn-large btn-fullwidth m-t40']")));
		clickLogin.click();
		Thread.sleep(3000);
	}
	
	@Test(priority = 1) // open the Support Request page
	public void OpenSupportRequestSection() throws InterruptedException {

		WebElement clickmyaccount = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dvSlidetop']/a")));
		clickmyaccount.click();
		Thread.sleep(3000);
		
		WebElement clicksupportrequest = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dvSlidetop']/div/ul/li[2]/a")));
		clicksupportrequest.click();
		Thread.sleep(3000); 
	}
	
	@Test(priority = 2) // Support Request Validation
	public void SupportRequestValidation() throws InterruptedException {
		
		WebElement clicksubmitrequest = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnSaveRequest']")));
		clicksubmitrequest.click();
		Thread.sleep(2000);

		WebElement Phonetextbox = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/section/form/div/div/div[1]/div/div[3]/div[1]/div/span[2]/span"))); 
		String Phonealert = Phonetextbox.getText();
		Assert.assertEquals(Phonealert, "The Phone field is required.");
		System.out.println(Phonealert);
		Thread.sleep(1000);
		
		WebElement Subjecttextbox = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/section/form/div/div/div[1]/div/div[3]/div[2]/div/span[2]/span")));  
		String Subjectalert = Subjecttextbox.getText();
		Assert.assertEquals(Subjectalert, "The Subject field is required.");
		System.out.println(Subjectalert);
		Thread.sleep(1000);
		
		WebElement IssueQuestiontextfield = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/section/form/div/div/div[1]/div/div[4]/div/div/span[2]/span")));  
		String IssueQuestionalert = IssueQuestiontextfield.getText();
		Assert.assertEquals(IssueQuestionalert, "The Issue/Question field is required.");
		System.out.println(IssueQuestionalert);
		Thread.sleep(1000);
	}
	
	@Test(priority = 3) // Support Request Validation
	public void SupportRequestFunctionality() throws InterruptedException {
		
		WebElement phone = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"Phone\"]"))); 
		phone.sendKeys("4561327895");
		Thread.sleep(2000);
		
		WebElement subject = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"Subject\"]")));  
		subject.sendKeys("test");
		Thread.sleep(2000);
		
		WebElement issuequestion = wait 
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"Description\"]"))); 
		issuequestion.sendKeys("This is test automation scripts.");
		Thread.sleep(3000);
		
		WebElement clicksubmitrequest = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnSaveRequest']")));
		clicksubmitrequest.click();
		Thread.sleep(3000);
		
		WebElement Success = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/section/form/div/div/div[1]/div/div[1]")));  
		String Successmessage = Success.getText();
		Assert.assertEquals(Successmessage, "Thanks for contacting us...");
		System.out.println(Successmessage);
		Thread.sleep(2000);
		
		
		
	}
	
		
	@AfterClass
	public void closeBrowser() throws InterruptedException {

		driver.close();
		driver.quit();
	}
}
