package ManageUser.ManageUser;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import static org.testng.Assert.assertEquals;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.openqa.selenium.JavascriptExecutor;

public class ManageUserTest {

	// String s = System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
	// WebDriver driver = new FirefoxDriver();
	// public WebDriverWait wait = new WebDriverWait(driver, 60);

	String s = System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	public WebDriverWait wait = new WebDriverWait(driver, 60);

	@BeforeClass
	public void OpenBrowseAndLogin() throws InterruptedException {

		driver.get("https://stage1.tascportal.org");
		//driver.get("https://tascportal.org");
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

	@Test(priority = 0)
	public void Addnewuser() throws InterruptedException {

		WebElement ClickAddNewUser = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnAddNewUser")));
		ClickAddNewUser.click();
		((JavascriptExecutor) driver).executeScript("scroll(0,400)");
		Thread.sleep(2000);

		WebElement FirstName = wait.until(ExpectedConditions.elementToBeClickable(By.id("FirstName")));
		FirstName.sendKeys("hello1");
		Thread.sleep(2000);

		WebElement MiddleName = wait.until(ExpectedConditions.elementToBeClickable(By.id("MiddleName")));
		MiddleName.sendKeys("test1");
		Thread.sleep(2000);

		WebElement LastName = wait.until(ExpectedConditions.elementToBeClickable(By.id("LastName")));
		LastName.sendKeys("gate61");
		Thread.sleep(2000);

		WebElement Email = wait.until(ExpectedConditions.elementToBeClickable(By.id("Email")));
		Email.sendKeys("hello2@gate6.com");
		Thread.sleep(3000);

		WebElement selectagency = driver.findElement((By.xpath(
				"//*[@id=\"frmUserManagement\"]/div[1]/div[2]/div[2]/div/div[1]/div/div[2]/span/span/span[1]")));
		selectagency.click(); 
		Thread.sleep(2000);  

		// select the agency from drop-down

		String searchText = "GATE6TESTQA";
		List<WebElement> options = driver.findElements(By.xpath("//ul[@id='Agency_listbox']/li"));
		// Loop through the options and select the one that matches
		for (WebElement option : options) {
			if (option.getText().toLowerCase().equals(searchText.toLowerCase())) {
				option.click();
			}
		}
		Thread.sleep(4000);

		WebElement Role = driver.findElement((By.xpath(
				"//*[@id=\"frmUserManagement\"]/div[1]/div[2]/div[2]/div/div[3]/div/div/span[1]/span/span[1]"))); 
		Role.click();

		// select the Roles from drop-down
		String searchroletext = "Case Manager";
		List<WebElement> roles = driver.findElements(By.xpath("//ul[@id='Role_listbox']/li"));   
		for (WebElement searchroles : roles) {
			if (searchroles.getText().toLowerCase().equals(searchroletext.toLowerCase())) {
				searchroles.click();
				}

		}
			Thread.sleep(2000);
			WebElement Phonenumber = wait.until(ExpectedConditions.elementToBeClickable(By.id("CellNo")));
			Phonenumber.sendKeys("9713108197");
			Thread.sleep(2000);

			WebElement clickSave = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSave")));
			clickSave.click();
			Thread.sleep(2000);

			WebElement GetText = wait.until(ExpectedConditions.elementToBeClickable(By.className("Success")));
			String Alertmessage = GetText.getText();
			AssertJUnit.assertEquals(Alertmessage, "User created successfully!");
			System.out.println(Alertmessage);
		}
	

	@Test(priority = 1)
	public void Edituser() throws InterruptedException {

		WebElement filtertextbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("FilterKeywords")));
		filtertextbox.sendKeys("hello2@gate6.com");
		filtertextbox.sendKeys(Keys.ENTER);
		((JavascriptExecutor) driver).executeScript("scroll(0,400)");
		Thread.sleep(2000);

		WebElement edituserclick = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".grid-link")));
		edituserclick.click();
		Thread.sleep(2000);

		String actualTitle = driver.findElement(By.id("UserViewTitle")).getText();
		String expectedTitle = "EDIT USER > GATE61, HELLO1 TEST1";
		AssertJUnit.assertEquals(actualTitle, expectedTitle);
		Thread.sleep(2000);

		String Emailcheck = "hello2@gate6.com";
		AssertJUnit.assertEquals(Emailcheck, "hello2@gate6.com");
		System.out.println(Emailcheck);
		Thread.sleep(2000);

		// Edit middle name
		WebElement Middlename = wait.until(ExpectedConditions.elementToBeClickable(By.id("MiddleName")));
		Middlename.clear();
		Middlename.sendKeys("Tasc");
		Thread.sleep(2000);

		WebElement clickSave = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSave")));
		clickSave.click();
		Thread.sleep(2000);

		String Success = "User updated successfully!";
		AssertJUnit.assertEquals(Success, "User updated successfully!");
		System.out.println(Success);
	}

	@Test(priority = 2)
	public void emailalreadyexist() throws InterruptedException {

		WebElement filtertextbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("FilterKeywords")));
		filtertextbox.sendKeys("hello2@gate6.com");
		filtertextbox.sendKeys(Keys.ENTER);
		((JavascriptExecutor) driver).executeScript("scroll(0,400)");
		Thread.sleep(2000);

		WebElement edituserclick = wait.until(ExpectedConditions.elementToBeClickable(By.className("grid-link")));
		edituserclick.click();
		Thread.sleep(2000);

		// Edit Email_id
		WebElement emailedit = wait.until(ExpectedConditions.elementToBeClickable(By.id("Email")));
		emailedit.clear();
		emailedit.sendKeys("vishwas9011@gmail.com");
		Thread.sleep(2000);

		WebElement clickSave = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSave")));
		clickSave.click();
		Thread.sleep(2000);

		String alertmessage = "Email already Exist.";
		AssertJUnit.assertEquals(alertmessage, "Email already Exist.");
		System.out.println(alertmessage);
		Thread.sleep(2000);

	}

	@Test(priority = 3)
	public void deleteuserwithoutdonor() throws InterruptedException {

		WebElement selectagency = driver.findElement((By.className("k-select")));
		selectagency.click();
		Thread.sleep(2000);

		String searchText = "GATE6TESTQA";
		List<WebElement> options = driver.findElements(By.xpath("//ul[@id='AgencyFilter_listbox']/li"));

		// Loop through the options and select the one that matches
		for (WebElement option : options) {

			if (option.getText().toLowerCase().equals(searchText.toLowerCase())) {
				option.click(); // click the desired option
				Thread.sleep(3000);
			}
		}

		WebElement Role = driver.findElement((By.xpath(
				"//*[@id='body']/section/div/div[1]/div[1]/div/div/div/div[2]/div/div[2]/div/span/span/span[2]/span")));
		Role.click();
		Thread.sleep(2000);

		// select the Roles from drop-down

		String searchrole = "Case Manager";
		List<WebElement> roles = driver.findElements(By.xpath("//ul[@id='RoleFilter_listbox']/li"));
		for (WebElement searchroles : roles) {
			if (searchroles.getText().toLowerCase().equals(searchrole.toLowerCase())) {
				searchroles.click(); // click the desired option
				Thread.sleep(3000);
			}
		}

		WebElement filtertextbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("FilterKeywords")));
		Thread.sleep(2000);
		filtertextbox.sendKeys("hello2@gate6.com");
		filtertextbox.sendKeys(Keys.ENTER);
		((JavascriptExecutor) driver).executeScript("scroll(0,400)");
		Thread.sleep(2000);

		WebElement clickuser = driver.findElement(By.cssSelector(".grid-link"));
		clickuser.click();
		Thread.sleep(2000);

		WebElement deletebuttonclick = driver.findElement(By.id("btnDelete"));
		deletebuttonclick.click();
		Thread.sleep(2000);

		String alertmessage = "Are you sure you want to delete?";
		AssertJUnit.assertEquals(alertmessage, "Are you sure you want to delete?");
		System.out.println(alertmessage);
		Thread.sleep(2000);

		WebElement btncancel = driver.findElement(By.id("btnClosePopup"));
		btncancel.click();
		Thread.sleep(2000);

		WebElement deletebutton = driver.findElement(By.id("btnDelete"));
		deletebutton.click();
		Thread.sleep(2000);

		WebElement confirmbuttonclick = driver.findElement(By.xpath("//*[@id='btnConfirm']"));
		confirmbuttonclick.click();
		Thread.sleep(2000);

		String Success = "User deleted successfully!";
		AssertJUnit.assertEquals(Success, "User deleted successfully!");
		System.out.println(Success);
		Thread.sleep(2000);
		
		WebElement filtertextboxclear = wait.until(ExpectedConditions.elementToBeClickable(By.id("FilterKeywords")));
		filtertextboxclear.clear();
		Thread.sleep(2000);

	}

	@Test(priority = 4)
	public void deleteuserwithdonor() throws InterruptedException {

		WebElement selectagency = driver.findElement((By.className("k-select")));
		selectagency.click();
		Thread.sleep(2000);

		String searchText = "YUMA COUNTY ADULT PROBATION";
		List<WebElement> options = driver.findElements(By.xpath("//ul[@id='AgencyFilter_listbox']/li"));

		for (WebElement option : options) {
			if (option.getText().toLowerCase().equals(searchText.toLowerCase())) {
				option.click(); // click the desired option
				Thread.sleep(3000);
			}
		}

		WebElement Role = driver.findElement((By.xpath(
				"//*[@id='body']/section/div/div[1]/div[1]/div/div/div/div[2]/div/div[2]/div/span/span/span[2]/span")));
		Role.click();
		Thread.sleep(2000);

		// select the Roles from drop-down
		String searchrole = "Case Manager";
		List<WebElement> roles = driver.findElements(By.xpath("//ul[@id='RoleFilter_listbox']/li"));
		for (WebElement searchroles : roles) {
			if (searchroles.getText().toLowerCase().equals(searchrole.toLowerCase())) {
				searchroles.click(); // click the desired option
				Thread.sleep(3000);
				}
			}

			WebElement status = driver.findElement((By.xpath(
					"//*[@id='body']/section/div/div[1]/div[1]/div/div/div/div[2]/div/div[3]/div/span/span/span[2]/span")));
			status.click();

			Thread.sleep(2000);

			// select the Roles from drop-down
			String statusfilter1 = "Active";
			List<WebElement> filterstatus = driver.findElements(By.xpath("//ul[@id='StatusFilter_listbox']/li"));

			// Loop through the options and select the one that matches
			for (WebElement status1 : filterstatus) {
				if (status1.getText().toLowerCase().equals(statusfilter1.toLowerCase())) {
					status1.click(); // click the desired option
					Thread.sleep(2000);
				}
			}

			WebElement filtertextbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("FilterKeywords")));
			filtertextbox.clear();
			Thread.sleep(2000);
			filtertextbox.sendKeys("vishwas9011@gmail.com");
			filtertextbox.sendKeys(Keys.ENTER);
			((JavascriptExecutor) driver).executeScript("scroll(0,400)");
			Thread.sleep(3000);

			WebElement clickuser = driver.findElement(By.className("grid-link"));
			clickuser.click();
			Thread.sleep(3000);

			WebElement deletebutton = driver.findElement(By.id("btnDelete"));
			deletebutton.click();
			Thread.sleep(2000);

			System.out.println(driver.findElement(By.xpath("//*[@id='tasc-popup-content']")).getText());
			driver.findElement(By.xpath(".//*[@id='btnOk']")).click();
			Thread.sleep(2000);
			
			WebElement filtertextboxclear = wait.until(ExpectedConditions.elementToBeClickable(By.id("FilterKeywords")));
			filtertextboxclear.clear();
			filtertextbox.sendKeys(Keys.ENTER);
			Thread.sleep(4000);

		}

	@Test(priority = 5)
	public void actiondropdown() throws InterruptedException {
		
		WebElement cancelbtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnCancel")));
		cancelbtn.click();
		Thread.sleep(3000);
		
		// Case 1 : Deactivate user from actions column
		WebElement selectagency = driver.findElement((By.className("k-select")));
		selectagency.click();
		Thread.sleep(2000);

		String searchText = "GATE6TESTQA";
		List<WebElement> options = driver.findElements(By.xpath("//ul[@id='AgencyFilter_listbox']/li"));

		// Loop through the options and select the one that matches
		for (WebElement option : options) {
			if (option.getText().toLowerCase().equals(searchText.toLowerCase())) {
				option.click();
				// click the desired option
				Thread.sleep(3000);
			}

		}

		WebElement Role = driver.findElement((By.xpath(
				"//*[@id='body']/section/div/div[1]/div[1]/div/div/div/div[2]/div/div[2]/div/span/span/span[2]/span")));
		Role.click();
		Thread.sleep(2000);

		// select the Roles from drop-down
		String searchrole = "CASE MANAGER";
		List<WebElement> roles = driver.findElements(By.xpath("//ul[@id='RoleFilter_listbox']/li"));
		// Loop through the options and select the one that matches
		for (WebElement searchroles : roles) {
			if (searchroles.getText().toLowerCase().equals(searchrole.toLowerCase())) {
				searchroles.click();
				// click the desired option
				Thread.sleep(3000);
			}
		}

		WebElement FindText2 = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'ADMIN, TASC')]")));
		
		WebElement SelectCheckBox3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td/label")));
		SelectCheckBox3.click();
		Thread.sleep(2000);

		WebElement ClickActionBUtton = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[@id=\"body\"]/section/div/div[1]/div[3]/div/div/div/span/span/span[2]/span")));
		ClickActionBUtton.click();
		Thread.sleep(2000);

		WebElement DeactivateSelected = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@id=\"Action_listbox\"]/li[3]")));
		DeactivateSelected.click();
		Thread.sleep(1000);

		WebElement Confirm = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnConfirm")));
		Confirm.click();
		Thread.sleep(2000);

		WebElement GetText = wait.until(ExpectedConditions.elementToBeClickable(By.className("Success")));
		String alertmessage = GetText.getText();

		System.out.println(alertmessage);
		Thread.sleep(6000);

		/* Case 2 : Activated user from actions column*/

		WebElement filtertextbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("FilterKeywords")));
		filtertextbox.sendKeys("tascadmin@gate6.com");
		filtertextbox.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		
		WebElement status = driver.findElement((By.xpath(
				"//*[@id='body']/section/div/div[1]/div[1]/div/div/div/div[2]/div/div[3]/div/span/span/span[2]/span")));
		status.click(); 

		Thread.sleep(2000);
		
		WebElement status1 = driver.findElement((By.xpath(
				"//*[@id=\"StatusFilter_listbox\"]/li[3]")));
		status1.click();
		Thread.sleep(3000);

		WebElement FindText = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'ADMIN, TASC')]")));

		WebElement SelectCheckBox2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td/label")));
		SelectCheckBox2.click();
		Thread.sleep(2000);

		WebElement ClickActionBUtton2 = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[@id=\"body\"]/section/div/div[1]/div[3]/div/div/div/span/span/span[2]/span")));
		ClickActionBUtton2.click();
		Thread.sleep(2000);

		WebElement activateSelected = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@id=\"Action_listbox\"]/li[1]")));
		activateSelected.click();
		Thread.sleep(1000);

		WebElement Confirm2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnConfirm")));
		Confirm2.click();
		Thread.sleep(2000);

		WebElement GetText2 = wait.until(ExpectedConditions.elementToBeClickable(By.className("Success")));
		String alertmessage2 = GetText2.getText();

		System.out.println(alertmessage2);
		Thread.sleep(3000);

	}

	@AfterClass
	public void closeBrowser() throws InterruptedException {

		driver.close();
		driver.quit();
		
	}

}
