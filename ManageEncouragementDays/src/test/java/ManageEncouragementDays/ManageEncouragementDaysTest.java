package ManageEncouragementDays;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ManageEncouragementDaysTest {

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

	@Test(priority = 1) // open the Manage Encouragement Days section
	public void OpenManageEncouragementDaysSection() throws InterruptedException {

		WebElement adminclick = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dvSlidetop2']/a")));
		adminclick.click();
		Thread.sleep(2000);

		WebElement ManageEncouragementDays = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dvSlidetop2']/div/ul/li[13]/a")));
		ManageEncouragementDays.click();
		Thread.sleep(3000);
	}

	@Test(priority = 2) // Open & Close Encouragement Days pop-up
	public void OpenandCloseEncouragementDayspopup() throws InterruptedException {

		WebElement addnewbutton = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnAddNew")));
		addnewbutton.click();
		Thread.sleep(2000);

		WebElement popupmessage = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='headingEncouragementDays']")));
		String popmessage2 = popupmessage.getText();
		Assert.assertEquals(popmessage2, "ADD NEW ENCOURAGEMENT DAYS");
		System.out.println(popmessage2);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@class='mfp-close']")).click(); // Click on the close(X)icon for close the
																				// pop-up
		Thread.sleep(3000);

	}

	@Test(priority = 3) // Add Encouragement Days
	public void AddEncouragementDays() throws InterruptedException {

		WebElement addnewbutton = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnAddNew")));
		addnewbutton.click();
		Thread.sleep(2000);

		WebElement addencouragementday = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"txtTitle\"]")));
		addencouragementday.sendKeys("Test leave Day");
		Thread.sleep(3000);

		driver.findElement(By.cssSelector("span.k-icon.k-i-arrow-e")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("15")).click();
		Thread.sleep(2000);

		driver.findElement(By.id("btnSave")).click(); // click on save button
		Thread.sleep(2000);

		WebElement popupmessage = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='tasc-popup-content']")));
		String popmessage2 = popupmessage.getText();
		Assert.assertEquals(popmessage2, "Encouragement days saved successfully.");
		System.out.println(popmessage2);
		Thread.sleep(2000);

		driver.findElement(By.id("btnOk")).click(); // click on save button
		Thread.sleep(2000);

	}

	@Test(priority = 4) // Add Multiple Encouragement Days
	public void AddMultipleEncouragementDays() throws InterruptedException {

		WebElement addnewbutton = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnAddNew")));
		addnewbutton.click();
		Thread.sleep(2000);

		WebElement addencouragementday = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"txtTitle\"]")));
		addencouragementday.sendKeys("Test Multiple leave Day");
		Thread.sleep(3000);

		driver.findElement(By.cssSelector("span.k-icon.k-i-arrow-e")).click();
		Thread.sleep(3000);

		String day = "25";

		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("k-content")));
		WebElement table = driver.findElement(By.className("k-content"));

		// System.out.println("Kendo Calendar");
		List<WebElement> tableRows = table.findElements(By.xpath("//tr"));
		for (WebElement row : tableRows) {
			List<WebElement> cells = row.findElements(By.xpath("td"));

			for (WebElement cell : cells) {
				if (cell.getText().equals(day)) {
					driver.findElement(By.linkText(day)).click();
				}
			}
		}

		Thread.sleep(2000);

		WebElement addnewdate = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//a[@class='calendar manage-icon icon-calendar' and @onclick='_fnAddNewDate();']")));
		addnewdate.click();
		Thread.sleep(2000);

		String day2 = "11";

		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("k-content")));
		WebElement table1 = driver.findElement(By.className("k-content"));

		// System.out.println("Kendo Calendar");
		List<WebElement> tableRows1 = table1.findElements(By.xpath("//tr"));
		for (WebElement row : tableRows1) {
			List<WebElement> cells = row.findElements(By.xpath("td"));

			for (WebElement cell : cells) {
				if (cell.getText().equals(day2)) {
					driver.findElement(By.linkText(day2)).click();
				}
			}
		}
		Thread.sleep(2000);

		driver.findElement(By.id("btnSave")).click(); // click on save button
		Thread.sleep(2000);

		WebElement popupmessage = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='tasc-popup-content']")));
		String popmessage2 = popupmessage.getText();
		Assert.assertEquals(popmessage2, "Encouragement days saved successfully.");
		System.out.println(popmessage2);
		Thread.sleep(2000);

		driver.findElement(By.id("btnOk")).click(); // click on save button

	}

	@Test(priority = 5) // Edit Encouragement Days
	public void EditEncouragementDays() throws InterruptedException {

		driver.get("https://stage1.tascportal.org/Admin/ManageEncouragementDays");

		// click on the edit image link

		Thread.sleep(4000);
		WebElement editimage = driver.findElement(By.xpath(
				"//*[@id=\"mCSB_1_container\"]/div/table/tbody/tr[7]/td[3]/a/img[@src='/images/icon-edit-disable.png']"));
		editimage.click();
		Thread.sleep(2000);

		WebElement popupmessage = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='headingEncouragementDays']")));
		String popmessage2 = popupmessage.getText();
		Assert.assertEquals(popmessage2, "EDIT ENCOURAGEMENT DAYS");
		Thread.sleep(2000);

		WebElement addencouragementday = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"txtTitle\"]")));
		addencouragementday.clear();
		Thread.sleep(1000);
		addencouragementday.sendKeys("Edit leave Day 1 ");
		Thread.sleep(4000);

		WebElement addnewdate = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("/html/body/div[2]/div/div[1]/div/div[2]/div[1]/div/div[4]/div/div/div[1]/div/div[2]/a[1]")));
		addnewdate.click(); 
		Thread.sleep(2000);

		driver.findElement(By.cssSelector("span.k-icon.k-i-arrow-e")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("24")).click();
		Thread.sleep(2000);

		driver.findElement(By.id("btnSave")).click(); // click on save button
		Thread.sleep(2000);

		WebElement popupmessage2 = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='tasc-popup-content']")));
		String popmessage3 = popupmessage2.getText();
		Assert.assertEquals(popmessage3, "Encouragement days saved successfully.");
		System.out.println(popmessage3);
		Thread.sleep(2000);

		driver.findElement(By.id("btnOk")).click(); // click on save button
		Thread.sleep(2000);
	}

	@Test(priority = 6) // RemoveEncouragementdaysFrom EditEncouragementdays pop-up
	public void RemoveEncouragementdaysFromEditEncouragementdayspopup() throws InterruptedException {

		
		driver.get("https://stage1.tascportal.org/Admin/ManageEncouragementDays");
		Thread.sleep(2000);
		
		WebElement editimage = driver.findElement(By.xpath(
				"//*[@id=\"mCSB_1_container\"]/div/table/tbody/tr[6]/td[3]/a/img[@src='/images/icon-edit-disable.png']"));
		editimage.click();
		Thread.sleep(2000);

		if (driver
				.findElement(
						By.xpath("//a[@class='close manage-icon icon-delete' and @onclick='_fnRemoveDate(this);']"))
				.isDisplayed()) {
			WebElement remove = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//a[@class='close manage-icon icon-delete' and @onclick='_fnRemoveDate(this);']")));
			remove.click();
			Thread.sleep(2000);

		}

		else

		{
			WebElement Calender = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//a[@class='calendar manage-icon icon-calendar' and @onclick='_fnAddNewDate();']")));
			Calender.click();
			Thread.sleep(2000);
			WebElement remove = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//a[@class='close manage-icon icon-delete' and @onclick='_fnRemoveDate(this);']")));
			remove.click();
			Thread.sleep(2000);
			
			driver.findElement(By.cssSelector("span.k-icon.k-i-arrow-e")).click();
			Thread.sleep(3000);
			driver.findElement(By.linkText("20")).click();
			Thread.sleep(3000);

		}
		driver.findElement(By.id("btnSave")).click(); // click on save button
		Thread.sleep(2000);
		
		WebElement popupmessage2 = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='tasc-popup-content']")));
		String popmessage3 = popupmessage2.getText();
		Assert.assertEquals(popmessage3, "Encouragement days saved successfully.");
		System.out.println(popmessage3);
		Thread.sleep(2000);

		driver.findElement(By.id("btnOk")).click(); // click on save button
		Thread.sleep(2000);

	}
	
	@Test(priority = 7) // Encouragement days From agency Integration
	public void EncouragementDaysFromAgency() throws InterruptedException {
		
		WebElement adminclick = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dvSlidetop2']/a")));
		adminclick.click();
		Thread.sleep(2000);
		
		WebElement ManageAgency = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dvSlidetop2']/div/ul/li[2]/a")));
		ManageAgency.click();
		Thread.sleep(3000);
		
		WebElement searchagency = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"FilterKeywords\"]")));
		searchagency.sendKeys("GATE6TESTQA");
		Thread.sleep(1000);
		searchagency.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		
		WebElement ClickAgency = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//a[@class='p-16x bold' and @href='/agency/index/5e822a4a-3859-498d-bef7-3985bf43284e']")));
		ClickAgency.click();
		Thread.sleep(4000);
		
		WebElement ClickAgencyDetails = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("/html/body/div[4]/section/div/div/div/ul/li[2]/a/span")));
		ClickAgencyDetails.click();
		Thread.sleep(4000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver; /* Code For Scrolling the Screen */
		js.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(4000);
		
		if(!driver.findElement(By.xpath("//*[@id=\"ulEncouragementDays\"]/li[2]/label/span")).isSelected()) 
		{
		  driver.findElement(By.xpath("//*[@id=\"ulEncouragementDays\"]/li[2]/label/span")).click();
		} 
		
		Thread.sleep(3000);
		
		if(!driver.findElement(By.xpath("//*[@id=\"ulEncouragementDays\"]/li[7]/label/span")).isSelected()) 
		{
		  driver.findElement(By.xpath("//*[@id=\"ulEncouragementDays\"]/li[7]/label/span")).click();
		} 
		
		Thread.sleep(2000);
		
		JavascriptExecutor js2 = (JavascriptExecutor) driver; /* Code For Scrolling the Screen */
		js2.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(3000);
		
		driver.findElement(By.id("btnStep2Save")).click(); // click on save button
		Thread.sleep(2000);
		
		WebElement successfullydaved = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='tasc-alert']")));
		String popmessage3 = successfullydaved.getText();
		Assert.assertEquals(popmessage3, "Saved Successfully.");
		System.out.println(popmessage3);
		Thread.sleep(2000);
		
		driver.findElement(By.id("btnOk")).click(); // click on save button
		Thread.sleep(2000);
	}

	
	@Test(priority = 8) //Remove the Encouragement Day from Grid
	public void RemovetheEncouragementDayfromGrid() throws InterruptedException {
		
		WebElement adminclick = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dvSlidetop2']/a")));
		adminclick.click();
		Thread.sleep(2000);
		
		WebElement ManageEncouragementDays = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dvSlidetop2']/div/ul/li[13]/a")));
		ManageEncouragementDays.click();
		Thread.sleep(3000);
		
		driver.get("https://stage1.tascportal.org/Admin/ManageEncouragementDays");
		Thread.sleep(2000);
				
		WebElement deleteimage = driver.findElement(By.xpath(
				"/html/body/div[4]/section/div[1]/div/div[3]/div/div/div/div[2]/div/div[1]/div[1]/table/tbody/tr[7]/td[3]/a[2]/img"));
		deleteimage.click();
		Thread.sleep(2000);
		
		WebElement deleteEncouragementDays = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='tasc-popup-content']")));
		String popmessage3 = deleteEncouragementDays.getText();
		Assert.assertEquals(popmessage3, "Are you sure that you want to permanently delete the selected day(s)?");
		System.out.println(popmessage3);
		Thread.sleep(2000);

		driver.findElement(By.id("btnConfirm")).click(); // click on save button
		Thread.sleep(2000);
		
		WebElement deleteEncouragementDaysmessage = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='tasc-popup-content']")));
		String popmessage4 = deleteEncouragementDaysmessage.getText();
		Assert.assertEquals(popmessage4, "Encouragement days deleted successfully.");
		System.out.println(popmessage4);
		Thread.sleep(2000);
		
		driver.findElement(By.id("btnOk")).click(); // click on save button
		Thread.sleep(4000);
		
		WebElement deleteimage2 = driver.findElement(By.xpath(
				"/html/body/div[4]/section/div[1]/div/div[3]/div/div/div/div[2]/div/div[1]/div[1]/table/tbody/tr[6]/td[3]/a[2]/img"));
		deleteimage2.click();
		Thread.sleep(2000);
		
		WebElement deleteEncouragementDays2 = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='tasc-popup-content']")));
		String popmessageedit = deleteEncouragementDays2.getText();
		Assert.assertEquals(popmessageedit, "Are you sure that you want to permanently delete the selected day(s)?");
		System.out.println(popmessageedit);
		Thread.sleep(2000);

		driver.findElement(By.id("btnConfirm")).click(); // click on save button
		Thread.sleep(2000);
		
		WebElement deleteEncouragementDaysmessage2 = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='tasc-popup-content']")));
		String popmessage5 = deleteEncouragementDaysmessage2.getText();
		Assert.assertEquals(popmessage5, "Encouragement days deleted successfully.");
		System.out.println(popmessage5);
		Thread.sleep(2000);
		
		driver.findElement(By.id("btnOk")).click(); // click on save button
		Thread.sleep(2000);
		
	}

	@AfterClass
	public void closeBrowser() throws InterruptedException {

		driver.close();
		driver.quit();
	}
}
