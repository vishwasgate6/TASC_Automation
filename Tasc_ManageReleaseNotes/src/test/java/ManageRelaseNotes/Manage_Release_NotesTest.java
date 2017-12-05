package ManageRelaseNotes;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Manage_Release_NotesTest {

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

	@Test(priority = 0)
	public void ManageReleaseNotes() throws InterruptedException {

		WebElement adminclick = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dvSlidetop2']/a")));
		adminclick.click();
		Thread.sleep(2000);

		WebElement managereleasenotes = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dvSlidetop2']/div/ul/li[5]/a")));
		managereleasenotes.click();
		Thread.sleep(2000);
		System.out.println("Manage Release Notes Section Open Successfully");
		Thread.sleep(1000);
	}

	@Test(priority = 1)
	public void CreateReleaseNotes() throws InterruptedException {

		// Case 1: check the alert messages for each and every fields
		WebElement releasedate1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("ReleaseDate")));
		releasedate1.clear();
		Thread.sleep(2000);

		WebElement clickSave = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSubmit")));
		clickSave.click();
		Thread.sleep(2000);

		WebElement getalertmessage = wait
				.until(ExpectedConditions.elementToBeClickable(By.className("field-validation-error")));
		String Alertmessage = getalertmessage.getText();
		Assert.assertEquals(Alertmessage, "ReleaseDate is required field");
		System.out.println(Alertmessage);
		Thread.sleep(2000);

		WebElement GetText = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("/html/body/div[4]/section/div/div/div/div/div/form/section/div[2]/div[3]/span[2]/span")));
		String Alertmessage2 = GetText.getText();
		Assert.assertEquals(Alertmessage2, "Description is required field");
		System.out.println(Alertmessage2);
		Thread.sleep(2000);

		//Code for select the date from date picker

		WebElement releasedate = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".k-icon.k-i-calendar")));
		releasedate.click();
		Thread.sleep(2000);

		driver.findElement(By.cssSelector("span.k-icon.k-i-arrow-e")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("20")).click();
		Thread.sleep(2000);
		WebElement expirationdate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//*[@id='frmManageReleaseNotes']/section/div[2]/div[1]/div/div/div[2]/div/span[1]/span/span/span")));
		expirationdate.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='ExpirationDate_dateview']/div/div[1]/a[3]/span")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("12")).click();
		Thread.sleep(2000);

		WebElement DetailedDescription = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Description']")));
		DetailedDescription.sendKeys("Hello this is testing notes");
		Thread.sleep(2000);

		WebElement Save = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSubmit")));
		Save.click();
		Thread.sleep(2000);

		WebElement successmessange = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dvMsg']")));
		String Alertmessage3 = successmessange.getText();
		Assert.assertEquals(Alertmessage3, "Release Notes added successfully.");
		System.out.println(Alertmessage3);
		Thread.sleep(2000);
	}

	@Test(priority = 2)
	public void EditReleaseNotesbydescription() throws InterruptedException {
		
		WebElement clickmyaccount = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dvSlidetop']/a")));
		clickmyaccount.click();
		Thread.sleep(3000);
		
		WebElement ReleaseNotes = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dvSlidetop']/div/ul/li[4]/a")));
		ReleaseNotes.click();
		Thread.sleep(2000);
		
		WebElement editreleasenotes = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='mCSB_1_container']/div[1]/table/tbody/tr[1]/td[5]/div/a[1]")));
		editreleasenotes.click();
		Thread.sleep(2000);
		
		WebElement DetailedDescription = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Description']")));
		DetailedDescription.clear();
		Thread.sleep(2000);
		DetailedDescription.sendKeys("Hello Text is editable now");
		Thread.sleep(2000);
		
		WebElement Save = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSubmit")));
		Save.click();
		Thread.sleep(2000);	
		
		WebElement successmessange = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dvMsg']")));
		String Alertmessage3 = successmessange.getText();
		Assert.assertEquals(Alertmessage3, "Release Notes updated successfully.");
		System.out.println(Alertmessage3);
		Thread.sleep(2000);
	}
	
	@Test(priority = 3)
	public void EditReleaseNotesbydate() throws InterruptedException {
		
		WebElement releasedate = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".k-icon.k-i-calendar")));
		releasedate.click();
		Thread.sleep(2000);

		driver.findElement(By.cssSelector("span.k-icon.k-i-arrow-e")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("22")).click();
		Thread.sleep(2000);
		WebElement expirationdate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//*[@id='frmManageReleaseNotes']/section/div[2]/div[1]/div/div/div[2]/div/span[1]/span/span/span")));
		expirationdate.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='ExpirationDate_dateview']/div/div[1]/a[3]/span")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("15")).click();
		Thread.sleep(2000);
		
		WebElement DetailedDescription = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Description']")));
		DetailedDescription.clear();
		Thread.sleep(2000);
		DetailedDescription.sendKeys("Editable text now...!!!!");
		Thread.sleep(2000);
		
		WebElement  LinkIfapplicable = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Link']")));
		
		LinkIfapplicable.sendKeys("http://wwww.google.com");
		Thread.sleep(2000);
		
		WebElement Save = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSubmit")));
		Save.click();
		Thread.sleep(2000);
	}
	
	@Test(priority = 4)
	public void DeleteReleaseNotes() throws InterruptedException {
		
		WebElement clickmyaccount = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dvSlidetop']/a")));
		clickmyaccount.click();
		Thread.sleep(2000);
		
		WebElement ReleaseNotes = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dvSlidetop']/div/ul/li[4]/a")));
		ReleaseNotes.click();
		Thread.sleep(2000);		
		
		WebElement clickondeleteicon = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='mCSB_1_container']/div[1]/table/tbody/tr[1]/td[5]/div/a[2]")));
		clickondeleteicon.click();
		Thread.sleep(2000);
		
		String alertmessage = "Are you sure you want to delete the release note?";
		Assert.assertEquals(alertmessage, "Are you sure you want to delete the release note?");
		System.out.println(alertmessage);
		Thread.sleep(2000);
		
		WebElement btncancel = driver.findElement(By.id("btnClosePopup"));
		btncancel.click();
		Thread.sleep(2000);
		
		WebElement clickondeleteicon1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='mCSB_1_container']/div[1]/table/tbody/tr[1]/td[5]/div/a[2]")));
		clickondeleteicon1.click();
		Thread.sleep(2000);
		
		WebElement btnconfirm = driver.findElement(By.id("btnConfirm"));
		btnconfirm.click();
		Thread.sleep(2000);
		
		String alertmessage1 = "Release Note deleted Successfully.";
		Assert.assertEquals(alertmessage1, "Release Note deleted Successfully.");
		System.out.println(alertmessage1);
		Thread.sleep(2000);
		
		WebElement btnok = driver.findElement(By.id("btnOk"));
		btnok.click();
		Thread.sleep(2000);
	}

	@AfterClass
	public void closeBrowser() throws InterruptedException {

		driver.close();
		driver.quit();

	}
}
