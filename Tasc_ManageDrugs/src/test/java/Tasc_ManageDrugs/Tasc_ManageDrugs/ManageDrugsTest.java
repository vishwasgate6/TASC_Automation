package ManageDrugs;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ManageDrugsTest {
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
	public void ManageDrugs() throws InterruptedException {

		WebElement adminclick = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dvSlidetop2']/a")));
		adminclick.click();
		Thread.sleep(2000);

		WebElement managedrugs = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dvSlidetop2']/div/ul/li[4]/a")));
		managedrugs.click();
		Thread.sleep(2000);
		System.out.println("TASC Drugs Section Open Successfully");
		Thread.sleep(1000);
	}

	@Test(priority = 2)
	public void validationmessages() throws InterruptedException {

		// Case 1: For printing the error messages

		WebElement adddrusbutton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnSaveDrug']")));
		adddrusbutton.click();
		Thread.sleep(1000);

		WebElement getalertmessagefordrugname = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id='frmAddDrug']/div/section/div[2]/div[1]/div/span/span")));
		String Alertmessage = getalertmessagefordrugname.getText();
		Assert.assertEquals(Alertmessage, "Drug name is required");
		System.out.println(Alertmessage);

		WebElement getalertmessagefordrugcode = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id='frmAddDrug']/div/section/div[2]/div[2]/div/span/span")));
		String Alertmessage2 = getalertmessagefordrugcode.getText();
		Assert.assertEquals(Alertmessage2, "Drug code is required");
		System.out.println(Alertmessage2);

		WebElement getalertmessagefordrugabbr = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id='frmAddDrug']/div/section/div[2]/div[3]/div/span/span")));
		String Alertmessage3 = getalertmessagefordrugabbr.getText();
		Assert.assertEquals(Alertmessage3, "Drug abbr is required");
		System.out.println(Alertmessage3);

	}

	@Test(priority = 3)
	public void adddrugvalidationmessages() throws InterruptedException {

		driver.get("https://stage1.tascportal.org/Admin/AddDrug");
		driver.navigate().refresh();
		Thread.sleep(2000);

		WebElement AddDrugName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Name']")));
		AddDrugName.sendKeys("DR");
		AddDrugName.sendKeys(Keys.TAB);
		Thread.sleep(1000);

		WebElement getalertmessagefordrugname = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id='frmAddDrug']/div/section/div[2]/div[1]/div/span/span")));
		String Alertmessage = getalertmessagefordrugname.getText();
		Assert.assertEquals(Alertmessage, "The Drug name must be at least 3 characters long.");
		System.out.println(Alertmessage);

		String press = Keys.chord(Keys.SHIFT, Keys.ENTER);
		WebElement AddDrugName1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Name']")));
		Thread.sleep(2000);
		AddDrugName1.sendKeys(press);
		AddDrugName.clear();
		Thread.sleep(1000);
		WebElement DrugName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Name']")));
		DrugName.sendKeys("TEST");
		Thread.sleep(2000);
	}

	@Test(priority = 4)
	public void DrugCodevalidation() throws InterruptedException {

		WebElement Drugcode = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='DrugCode']")));
		Drugcode.sendKeys("D");

		WebElement getalertmessagefordrugcode = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id='frmAddDrug']/div/section/div[2]/div[2]/div/span/span")));
		String Alertmessage = getalertmessagefordrugcode.getText();
		Assert.assertEquals(Alertmessage, "The field DrugCode must be a number.");
		System.out.println(Alertmessage);
		Thread.sleep(2000);

		WebElement Drugcodeenter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='DrugCode']")));
		Drugcode.clear();
		Drugcodeenter.sendKeys("2222");
		Thread.sleep(2000);
	}

	@Test(priority = 5)
	public void DrugAbbrvalidation() throws InterruptedException {

		WebElement Drugabbr = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='DrugAbbr']")));
		Drugabbr.sendKeys("VK");

		WebElement getalertmessagefordrugabbr = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id='frmAddDrug']/div/section/div[2]/div[3]/div/span/span")));
		String Alertmessage = getalertmessagefordrugabbr.getText();
		Assert.assertEquals(Alertmessage, "The Drug abbr must be at least 3 characters long.");
		System.out.println(Alertmessage);
		Thread.sleep(2000);

		WebElement Drugabbrenter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='DrugAbbr']")));
		Drugabbr.clear();
		Drugabbrenter.sendKeys("VK34");
		Thread.sleep(2000);

	}

	@Test(priority = 6)
	public void Mediatypeselection() throws InterruptedException {

		driver.get("https://stage1.tascportal.org/Admin/AddDrug");
		driver.navigate().refresh();
		Thread.sleep(2000);
		
		WebElement DrugName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Name']")));
		DrugName.sendKeys("TEST");
		Thread.sleep(2000);
		
		WebElement Drugcodeenter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='DrugCode']")));
		Drugcodeenter.sendKeys("2222");
		Thread.sleep(2000);
		
		WebElement Drugabbrenter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='DrugAbbr']")));
		Drugabbrenter.sendKeys("VK34");
		Thread.sleep(2000);

		WebElement selectmediadrug = driver.findElement((By
				.xpath("//*[@id='frmAddDrug']/div/section/div[2]/div[4]/div/div/span/span/span[2]/span")));
		selectmediadrug.click();
		Thread.sleep(2000);
		
		String searchText = "Hair";
		List<WebElement> options = driver.findElements(By.xpath("//ul[@id='DrugMedia_listbox']/li"));
		// Loop through the options and select the one that matches
		for (WebElement option : options) {
			if (option.getText().toLowerCase().equals(searchText.toLowerCase())) {
				option.click();
			}
		}
		
		WebElement AddDrug = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnSaveDrug']")));
		AddDrug.click();
		Thread.sleep(2000);
		
		WebElement successfullmessage = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id='dvMSG']/div")));
		String Alertmessage = successfullmessage.getText();
		Assert.assertEquals(Alertmessage, "Drug added successfully.");
		System.out.println(Alertmessage);
		Thread.sleep(2000);
		
	}
	
	@Test(priority = 7)
	public void DuplicateDrugAdd() throws InterruptedException {
		
		driver.get("https://stage1.tascportal.org/Admin/AddDrug");
		driver.navigate().refresh();
		Thread.sleep(2000);
		
		WebElement DrugName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Name']")));
		DrugName.sendKeys("TEST");
		Thread.sleep(2000);
		
		WebElement Drugcodeenter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='DrugCode']")));
		Drugcodeenter.sendKeys("2222");
		Thread.sleep(2000);
		
		WebElement Drugabbrenter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='DrugAbbr']")));
		Drugabbrenter.sendKeys("VK34");
		Thread.sleep(2000);

		WebElement selectmediadrug = driver.findElement((By
				.xpath("//*[@id='frmAddDrug']/div/section/div[2]/div[4]/div/div/span/span/span[2]/span")));
		selectmediadrug.click();
		Thread.sleep(2000);
		
		String searchText = "Hair";
		List<WebElement> options = driver.findElements(By.xpath("//ul[@id='DrugMedia_listbox']/li"));
		// Loop through the options and select the one that matches
		for (WebElement option : options) {
			if (option.getText().toLowerCase().equals(searchText.toLowerCase())) {
				option.click();
			}
		}
		
		WebElement AddDrug = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnSaveDrug']")));
		AddDrug.click();
		Thread.sleep(2000);
		
		WebElement successfullmessage = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id='dvMSG']/div")));
		String Alertmessage = successfullmessage.getText();
		Assert.assertEquals(Alertmessage, "Combination of drug code and drug abbr already exist.");
		System.out.println(Alertmessage);
		Thread.sleep(2000);
		
	}
	
	@Test(priority = 8)
	public void Editdrugcase() throws InterruptedException {
		
		driver.get("https://stage1.tascportal.org/Admin/AddDrug");
		driver.navigate().refresh();
		Thread.sleep(2000);
		
		WebElement editdrugs = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@id='dvDrugList']/ul[1]/li[7]/span[1]")));
		
		 Actions action = new Actions(driver); // Mouse hover code
         action.moveToElement(editdrugs).perform();
		
		//editdrugs.click();
		Thread.sleep(3000);
		
		WebElement ClickEdit = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@id='dvDrugList']/ul[1]/li[7]/span[2]")));
		ClickEdit.click();
		Thread.sleep(3000);
		
		WebElement successfullmessage = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id='headerPopup']")));
		String Alertmessage = successfullmessage.getText();
		Assert.assertEquals(Alertmessage, "EDIT DRUG");
		System.out.println(Alertmessage);
		Thread.sleep(2000);
		
		WebElement EditDrugname = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id='DrugName']")));
		EditDrugname.clear();
		EditDrugname.sendKeys("TEST SALIVA");
		Thread.sleep(3000);
		
		WebElement selectmediadrug = driver.findElement((By
				.xpath("//*[@id='add-edit-drug']/div/div[2]/div[4]/div/div/span/span/span[2]/span")));
		selectmediadrug.click();
		Thread.sleep(2000);
		
		String searchText = "Saliva";
		List<WebElement> options = driver.findElements(By.xpath("//ul[@id='EditDrugMedia_listbox']/li"));
		// Loop through the options and select the one that matches
		for (WebElement option : options) {
			if (option.getText().toLowerCase().equals(searchText.toLowerCase())) {
				option.click();
				Thread.sleep(2000);
			}
		}
		
		WebElement EditDrug = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnEditDrug']")));
		EditDrug.click();
		Thread.sleep(6000);
		
	}
	
	@Test(priority = 9)
	public void Deletedrugs() throws InterruptedException {
		
		WebElement deletedrugs = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id='dvDrugList']/ul[2]/li[12]")));
		
		Actions action = new Actions(driver); // Mouse hover code
        action.moveToElement(deletedrugs).perform();
        Thread.sleep(2000);
         
        WebElement clickdelete = wait.until(ExpectedConditions
 				.elementToBeClickable(By.xpath("//*[contains(text(), 'TEST SALIVA')]")));
        WebElement delete = driver.findElement(By.xpath("//*[@id='dvDrugList']/ul[2]/li[12]/span[3]"));
        delete.click();
 		Thread.sleep(2000);
 		
 		WebElement deletedrugalertmessage = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id='tasc-popup-content']")));
		String Alertmessage = deletedrugalertmessage.getText();
		Assert.assertEquals(Alertmessage, "Deleted drug will also get removed from all associated Agencies. Are you sure you want to delete drug?");
		System.out.println(Alertmessage);
		Thread.sleep(2000);
		
		WebElement Confirmbutton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnConfirm']")));
		Confirmbutton.click();
		Thread.sleep(2000);
		
		WebElement drugsuccessfullmessage = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id='dvMSG']/div")));
		String deletedrugAlertmessage = drugsuccessfullmessage.getText();
		Assert.assertEquals(deletedrugAlertmessage, "Drug deleted Successfully.");
		System.out.println(deletedrugAlertmessage);
		Thread.sleep(2000);
				
	}
	
	
	@AfterClass
	public void closeBrowser() throws InterruptedException {

		driver.close();
		driver.quit();

	}

}
