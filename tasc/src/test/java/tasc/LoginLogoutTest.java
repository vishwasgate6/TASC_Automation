package tasc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginLogoutTest {

//	String s = System.setProperty("webdriver.gecko.driver", "E:\\Seleniume\\geckodriver.exe");
//	WebDriver driver = new FirefoxDriver();
	//-------------------FireFox-----------------
	
	String s = System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	public WebDriverWait wait = new WebDriverWait(driver, 60);
	//--------------ChromeDr4iver--------------------------

	@BeforeTest
	public void Open_browser() throws InterruptedException {

		driver.get("https://stage1.tascportal.org");
		//driver.get("https://tascportal.org");
		driver.manage().window().maximize();
	}

	@Test(priority = 1)
	public void verifybuilddateandcopyrightmessage() throws InterruptedException {

		WebElement builddate = wait.until(ExpectedConditions.elementToBeClickable(By.className("build-number")));
		String st = builddate.getText();

		Assert.assertEquals(st, "3.13.07172017 - Firefox 56 (56.0) 1920 x 1080");
		// Assert.assertEquals(st, "3.11.05102017 - Firefox 54 (54.0) 1920 x
		// 1080");
		Thread.sleep(2000);
		System.out.println(st);

		WebElement copyright = wait.until(ExpectedConditions.elementToBeClickable(By.className("copyright")));
		String copyrightmessage = copyright.getText();
		Assert.assertEquals(copyrightmessage, "Copyright 2017. TASC, A private, non-profit, 501 © (3) corporation.");
		// Assert.assertEquals(copyrightmessage,"Copyright 2017. TASC, A
		// private, non-profit, 501(c)(3) corporation.");

		Thread.sleep(2000);
		System.out.println(copyrightmessage);
	}

	@Test(priority = 2)
	public void loginlogout() throws InterruptedException {

		// validation 1 (Blank user-name and password)

		WebElement blankusername = wait.until(ExpectedConditions.elementToBeClickable(By.className("k-textbox")));
		blankusername.sendKeys("");
		Thread.sleep(2000);

		WebElement blankpassword = wait.until(ExpectedConditions.elementToBeClickable(By.id("Password")));
		blankpassword.sendKeys("");
		Thread.sleep(2000);

		WebElement login = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='btn btn-large btn-fullwidth m-t40']")));
		login.click();
		Thread.sleep(2000);

		WebElement alertmessage = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='field-validation-error']")));
		String usernamealertmessage = alertmessage.getText();
		Assert.assertEquals(usernamealertmessage, "The Username field is required.");

		WebElement alertmessage2 = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='frmDefault']/div[2]/span/span")));
		String passwordalertmessage = alertmessage2.getText();
		Assert.assertEquals(passwordalertmessage, "The Password field is required.");
		Thread.sleep(2000);

		// validation 2 (Blank user-name and entered password)

		WebElement emptyusername = wait.until(ExpectedConditions.elementToBeClickable(By.className("k-textbox")));
		emptyusername.sendKeys("");
		Thread.sleep(2000);

		WebElement enteredpassword = wait.until(ExpectedConditions.elementToBeClickable(By.id("Password")));
		enteredpassword.sendKeys("tags1234");
		Thread.sleep(2000);

		WebElement loginvalidationclick = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='btn btn-large btn-fullwidth m-t40']")));
		loginvalidationclick.click();
		Thread.sleep(2000);

		WebElement usernamealertmessage1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='field-validation-error']")));
		String usernamealertmessage2 = usernamealertmessage1.getText();
		Assert.assertEquals(usernamealertmessage2, "The Username field is required.");
		Thread.sleep(2000);

		// validation 3 (Entered user-name and Blank password)

		WebElement enteredusername = wait.until(ExpectedConditions.elementToBeClickable(By.className("k-textbox")));
		enteredusername.sendKeys("gate6admin@gate6.com");
		Thread.sleep(2000);

		WebElement blankdpassword = wait.until(ExpectedConditions.elementToBeClickable(By.id("Password")));
		blankdpassword.clear();

		WebElement loginvalidationclick1 = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='btn btn-large btn-fullwidth m-t40']")));
		loginvalidationclick1.click();
		Thread.sleep(2000);

		WebElement passwordalertmessage1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='frmDefault']/div[2]/span/span")));
		String passwordalertmessage2 = passwordalertmessage1.getText();
		Assert.assertEquals(passwordalertmessage2, "The Password field is required.");
		Thread.sleep(2000);

		// validation 4 (Both user name & password are incorrect)

		driver.navigate().refresh();
		Thread.sleep(2000);

		WebElement usernameincorrect = wait.until(ExpectedConditions.elementToBeClickable(By.className("k-textbox")));
		usernameincorrect.sendKeys("6546456");
		Thread.sleep(2000);

		WebElement passwordincorrect = wait.until(ExpectedConditions.elementToBeClickable(By.id("Password")));
		passwordincorrect.sendKeys("fgdfgdf");
		Thread.sleep(2000);

		WebElement loginvalidationincorrect = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='btn btn-large btn-fullwidth m-t40']")));
		loginvalidationincorrect.click();
		Thread.sleep(2000);

		WebElement incorrectalertmessage = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='validation-summary-errors']")));
		String incorrectalertmessage2 = incorrectalertmessage.getText();
		Assert.assertEquals(incorrectalertmessage2, "The user name or password provided is incorrect.");
		Thread.sleep(2000);

		WebElement UserName = wait.until(ExpectedConditions.elementToBeClickable(By.className("k-textbox")));
		UserName.sendKeys("gate6admin@gate6.com");
		Thread.sleep(2000);

		WebElement Pass = wait.until(ExpectedConditions.elementToBeClickable(By.id("Password")));
		Pass.sendKeys("tags1234");
		Thread.sleep(2000);

		// for login button click
		WebElement clickLogin = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='btn btn-large btn-fullwidth m-t40']")));
		clickLogin.click();
		Thread.sleep(3000);

		String url = driver.getCurrentUrl();

		Assert.assertEquals(url, "https://stage1.tascportal.org/Admin/UserManagement");

		System.out.println("User login Successfully");

		WebElement clickmyaccount = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dvSlidetop']/a")));
		clickmyaccount.click();
		Thread.sleep(4000);

		driver.findElement(By.xpath("//*[@id='frmLogout']/a")).click();

		driver.get("https://stage1.tascportal.org/Home/Default");

		Assert.assertEquals("https://stage1.tascportal.org/Home/Default", driver.getCurrentUrl());

		System.out.println("User logout Successfully");
		Thread.sleep(2000);
	}

	@Test(priority = 3)

	public void verifyremembermecheckbox() throws InterruptedException {

		WebElement UserName = wait.until(ExpectedConditions.elementToBeClickable(By.className("k-textbox")));
		UserName.sendKeys("gate6admin@gate6.com");
		Thread.sleep(3000);

		WebElement Pass = wait.until(ExpectedConditions.elementToBeClickable(By.id("Password")));
		Pass.sendKeys("tags1234");
		Thread.sleep(3000);

		WebElement chkremembermecheckbox = wait
				.until(ExpectedConditions.elementToBeClickable(By.className("label_check")));
		chkremembermecheckbox.click();
		Thread.sleep(3000);

		System.out.println("test");

		WebElement clickLogin = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='frmDefault']/div[5]/input")));
		clickLogin.click();
		Thread.sleep(3000);

		WebElement clickmyaccount = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dvSlidetop']/a")));
		clickmyaccount.click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//*[@id='frmLogout']/a")).click();

		WebElement UserName1 = wait.until(ExpectedConditions.elementToBeClickable(By.className("k-textbox")));
		Thread.sleep(2000);
		UserName1.clear();
		Thread.sleep(2000);
	}

	@Test(priority = 4)

	public void testforgetpassword() throws InterruptedException {

		WebElement forgotpassword = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='frmDefault']/div[3]/strong/a")));
		forgotpassword.click();
		Thread.sleep(2000);

		String url = driver.getCurrentUrl();
		Assert.assertEquals(url, "https://stage1.tascportal.org/Home/RecoverPassword?Length=4");
		System.out.println("Reset password window successfully appeared");

		WebElement UserName = wait.until(ExpectedConditions.elementToBeClickable(By.className("k-textbox")));
		UserName.sendKeys("vishwas.iitm09@gmail.com");
		Thread.sleep(2000);

		WebElement recoverpassword = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='frmRecoverPassword']/div[3]/input")));
		recoverpassword.click();
		Thread.sleep(2000);

		WebElement alertmessage = wait.until(ExpectedConditions.elementToBeClickable(By.className("Success")));
		String alertmessage1 = alertmessage.getText();
		Assert.assertEquals(alertmessage1, "Password Send on your email. Please check your email.");
		System.out.println("alertmessage1 is :" + alertmessage1);
		Thread.sleep(2000);

		WebElement backtologin = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='frmRecoverPassword']/div[4]/a")));
		backtologin.click();
		Thread.sleep(2000);
	}
	
	@AfterTest
	public void CloseBrowser() {

		driver.close();

	}

}
