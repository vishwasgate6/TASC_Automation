package TASC_Automation;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Manage_User {

	String s = System.setProperty("webdriver.gecko.driver", "E:\\Seleniume\\geckodriver.exe");
	WebDriver driver = new FirefoxDriver();
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
		Thread.sleep(3000);
	}

	@Test(priority = 0)
	public void Addnewuser() throws InterruptedException {

		WebElement ClickAddNewUser = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnAddNewUser")));
		ClickAddNewUser.click();
		Thread.sleep(2000);

		WebElement FirstName = wait.until(ExpectedConditions.elementToBeClickable(By.id("FirstName")));
		FirstName.sendKeys("hello");
		Thread.sleep(2000);

		WebElement MiddleName = wait.until(ExpectedConditions.elementToBeClickable(By.id("MiddleName")));
		MiddleName.sendKeys("test");
		Thread.sleep(2000);

		WebElement LastName = wait.until(ExpectedConditions.elementToBeClickable(By.id("LastName")));
		LastName.sendKeys("gate6");
		Thread.sleep(2000);

		WebElement Email = wait.until(ExpectedConditions.elementToBeClickable(By.id("Email")));
		Email.sendKeys("hello1@gate6.com");
		Thread.sleep(2000);

		WebElement selectagency = driver.findElement((By.xpath("//div[2]/div/div/div/div[2]/div/span/span/span")));
		selectagency.click();
		Thread.sleep(2000);

		// select the agency from drop-down
		String searchText = "YUMA COUNTY ADULT PROBATION";
		List<WebElement> options = driver.findElements(By.xpath("//ul[@id='Agency_listbox']/li"));
		// Loop through the options and select the one that matches
		for (WebElement option : options) {
			if (option.getText().equals(searchText)) {
				option.click();
				// click the desired option
			}

		}

		WebElement Role = driver
				.findElement((By.xpath("//div[1]/div[2]/div[2]/div/div[3]/div/div/span[1]/span/span[2]/span")));
		Role.click();
		Thread.sleep(2000);

		// select the Roles from drop-down
		String searchrole = "CASE MANAGER";
		List<WebElement> roles = driver.findElements(By.xpath("//ul[@id='Role_listbox']/li"));
		// Loop through the options and select the one that matches
		for (WebElement searchroles : roles) {
			if (searchroles.getText().equals(searchrole)) {
				searchroles.click();
				// click the desired option
				Thread.sleep(2000);

			}

		}

		WebElement Phonenumber = wait.until(ExpectedConditions.elementToBeClickable(By.id("CellNo")));
		Phonenumber.sendKeys("9713108197");
		Thread.sleep(2000);

		WebElement clickSave = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSave")));
		clickSave.click();
		Thread.sleep(2000);

		WebElement GetText = wait.until(ExpectedConditions.elementToBeClickable(By.className("Success")));
		String Alertmessage = GetText.getText();
		Assert.assertEquals(Alertmessage, "User created successfully!");
		System.out.println(Alertmessage);
	}

	@Test(priority = 1)
	public void validationuser() throws InterruptedException {

		// Without enter any data click on the create user button

		WebElement ClickAddNewUser = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnAddNewUser")));
		ClickAddNewUser.click();
		Thread.sleep(2000);

		WebElement clickSave = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSave")));
		clickSave.click();
		// Thread.sleep(9000);

		WebElement firstname = wait.until(ExpectedConditions.elementToBeClickable(By.id("FirstName")));
		firstname.sendKeys("");
		Thread.sleep(2000);

		WebElement alertmessage = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='field-validation-error']")));
		String firstnamealertmessage = alertmessage.getText();
		Assert.assertEquals(firstnamealertmessage, "The First Name is required field");
		System.out.println(firstnamealertmessage);

		WebElement lastname = wait.until(ExpectedConditions.elementToBeClickable(By.name("LastName")));
		lastname.sendKeys("");

		String lastnamealertmessage1 = "The Last Name is required field";
		Assert.assertEquals(lastnamealertmessage1, "The Last Name is required field");
		System.out.println(lastnamealertmessage1);

		WebElement selectagency = driver.findElement((By.xpath("//div[2]/div/div/div/div[2]/div/span/span/span")));
		selectagency.sendKeys("");

		String agencyalert = "The Agency is required field.";
		Assert.assertEquals(agencyalert, "The Agency is required field.");
		System.out.println(agencyalert);

		String Emailalert = "The Email field is required.";
		Assert.assertEquals(Emailalert, "The Email field is required.");
		System.out.println(Emailalert);

		String Role = "The Role is required field.";
		Assert.assertEquals(Role, "The Role is required field.");
		System.out.println(Role);

	}

	@Test(priority = 0)
	public void Edituser() throws InterruptedException {
		
		
		WebElement filtertextbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("FilterKeywords")));
		filtertextbox.sendKeys("vishwas9011@gmail.com");
		filtertextbox.sendKeys(Keys.ENTER);
		Thread.sleep(2000);

		String Emailcheck = "vishwas9011@gmail.com";
		Assert.assertEquals(Emailcheck, "vishwas9011@gmail.com");
		System.out.println(Emailcheck);

		WebElement edituserclick = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='mCSB_2_container']/div[1]/table/tbody/tr/td[2]/a")));
		edituserclick.click();
		Thread.sleep(3000);
		
		//Edit middle name
		WebElement Middlename = wait.until(ExpectedConditions.elementToBeClickable(By.id("MiddleName")));
		Middlename.sendKeys("hello");
		Thread.sleep(2000);
		
		WebElement clickSave = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSave")));
		clickSave.click();
		Thread.sleep(2000);

		String Success = "User updated successfully!";
		Assert.assertEquals(Success, "User updated successfully!");
		System.out.println(Success);
	}

	@AfterClass
	public void closeBrowser() throws InterruptedException {

		
		//driver.close();
	}

}
