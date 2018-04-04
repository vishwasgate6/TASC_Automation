package ManageTeam;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ManageTeamTest {
	
	String s = System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	public WebDriverWait wait = new WebDriverWait(driver, 60);

	@BeforeClass
	public void OpenBrowseAndLogin() throws InterruptedException {
		
		driver.get("https://stage1.tascportal.org");
		//driver.get("https://tascportal.org");
		driver.manage().window().maximize();
		Thread.sleep(4000);

		WebElement UserName = wait.until(ExpectedConditions.elementToBeClickable(By.id("UserName")));
		//UserName.sendKeys("gate6qaadmin@gate6.com");
		UserName.sendKeys("gate6admin@gate6.com");
		Thread.sleep(2000);

		WebElement Pass = wait.until(ExpectedConditions.elementToBeClickable(By.id("Password")));
		//Pass.sendKeys("g6stg@adm");
		Pass.sendKeys("tags1234");
		Thread.sleep(2000);

		WebElement clickLogin = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='btn btn-large btn-fullwidth m-t40']")));
		clickLogin.click();
		Thread.sleep(4000);
	}
	
	@Test(priority = 1)//open the manage team section 
	public void OpenManageTeamSection() throws InterruptedException {
		
		WebElement adminclick = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dvSlidetop2']/a")));
		adminclick.click();
		Thread.sleep(2000);
		
		WebElement manageteam = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dvSlidetop2']/div/ul/li[7]/a")));
		manageteam.click();
		Thread.sleep(2000);
		 
	}
	
	@Test(priority = 2)//select the agency from manage team section 
	public void selecttheagency() throws InterruptedException {
		
		WebElement selectagency = wait.
				until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dvCreate']/div[1]/div[1]/div/span/span/span[2]/span")));
		selectagency.click();
		Thread.sleep(2000);
		
		String searchText = "GATE6TESTQA";
		List<WebElement> options = driver.findElements(By.xpath("//ul[@id='Agency_listbox']/li"));
		// Loop through the options and select the one that matches
		for (WebElement option : options) {
			if (option.getText().toLowerCase().equals(searchText.toLowerCase())) {
				option.click();
				Thread.sleep(2000);
			}
		}
		Thread.sleep(2000);
	}

	@Test(priority = 3)//click on the add new team button 
	public void clickonaddnewteam() throws InterruptedException{
		
		WebElement addnewteambutton = wait.
				until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnSelectExistingTeam']")));
		addnewteambutton.click();
		Thread.sleep(2000);
	}
	
	@Test(priority = 4)//cancel button click from add new team pop-up 
	public void cancelbutton() throws InterruptedException{
		
		WebElement cancelbutton = wait.
				until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnNewWinClose']")));
		cancelbutton.click();
		Thread.sleep(2000);
				
	}
			
	@Test(priority = 5)//add new team section 
	public void addnewteam() throws InterruptedException{
		
		WebElement addnewteambutton = wait.
				until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnSelectExistingTeam']")));
		addnewteambutton.click();
		Thread.sleep(2000);
		
		WebElement addnewteamname = wait.
				until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='TeamNameTxt']")));
		addnewteamname.sendKeys("Test Team");
		Thread.sleep(2000);
		
		WebElement saveteamname = wait.
				until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnNewSavePopUp']")));
		saveteamname.click();
		Thread.sleep(3000);
		
		WebElement alertmessage2 = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id='dvMSG']/div")));
		String Alertmessage3 = alertmessage2.getText();
		Assert.assertEquals(Alertmessage3, "Team added successfully.");
		System.out.println(Alertmessage3);
		Thread.sleep(2000);
		
	}
	
	@Test(priority = 6)//duplicate team case 
	public void duplicatenewteam() throws InterruptedException{
		
		WebElement addnewteambutton = wait.
				until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnSelectExistingTeam']")));
		addnewteambutton.click();
		Thread.sleep(2000);
		
		WebElement addnewteamname = wait.
				until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='TeamNameTxt']")));
		addnewteamname.sendKeys("Test Team");
		Thread.sleep(2000);
		
		WebElement saveteamname = wait.
				until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnNewSavePopUp']")));
		saveteamname.click();
		Thread.sleep(3000);
		
		WebElement alertpopup = wait.
				until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='tasc-alert']")));
		String alertmessage = alertpopup.getText();
		Assert.assertEquals(alertmessage, "Team name is already exist for this agency.");
		System.out.println(alertmessage);
		Thread.sleep(2000);
		
		driver.findElement(By.id("btnOk")).click(); //click on ok button
		Thread.sleep(2000);
		
		WebElement cancelbutton = wait.
				until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnNewWinClose']")));
		cancelbutton.click();
		Thread.sleep(2000);
		
	}
	
	@Test(priority = 7)//verify tool-tip data 
	public void verifytooltip() throws InterruptedException{


//		 Actions builder = new Actions(driver);
//		 WebElement element = driver.findElement(By.linkText("TEST TEAM"));
//		 builder.moveToElement(element).build().perform();	
			
		String actualTooltipTxt = "TEST TEAM";
		String tooltipTxt = driver.findElement(By.xpath("//*[text()='TEST TEAM']")).getAttribute("title");
		Thread.sleep(4000);
	    Assert.assertEquals(actualTooltipTxt, tooltipTxt);
	    
	    System.out.println("The actual tooltip is:" +tooltipTxt );
		Thread.sleep(2000); 
		
	}
	
	@Test(priority = 8)//change the team name
	public void ChanegTeamName() throws InterruptedException{
		
		WebElement changeteamname = wait.
				until(ExpectedConditions.elementToBeClickable(By.linkText("TEST TEAM")));
		changeteamname.click();
		Thread.sleep(5000);
		((JavascriptExecutor) driver)
	     .executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(3000);
		
		WebElement clickonchangeteamname = wait.
				until(ExpectedConditions.elementToBeClickable(By.linkText("CHANGE TEAM NAME")));
		clickonchangeteamname.click();
		Thread.sleep(2000);
		
		WebElement editteamname = wait.
				until(ExpectedConditions.elementToBeClickable(By.id("txtEditTeam")));
		editteamname.clear();
		Thread.sleep(2000);
		
		WebElement editnewteamname = wait.
				until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"txtEditTeam\"]")));
		editnewteamname.sendKeys("Test Team Edit");
		Thread.sleep(2000);
		
		driver.findElement(By.id("btnSavePopUp")).click(); //click on save button
		Thread.sleep(2000);
		
		WebElement updatealertmessage = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id=\"dvMSG\"]")));
		String Alertmessage3 = updatealertmessage.getText();
		Assert.assertEquals(Alertmessage3, "Team Name has been updated successfully.");
		System.out.println(Alertmessage3);
		Thread.sleep(2000);
	}
	
	
	@Test(priority = 9)//change the team without selecting user
	public void ChanegTeamwithoutuser() throws InterruptedException{
		
		WebElement changeteam = wait.
				until(ExpectedConditions.elementToBeClickable(By.linkText("TEST")));
		changeteam.click();
		Thread.sleep(3000);
		
		WebElement changeteamnamebutton = wait.
				until(ExpectedConditions.elementToBeClickable(By.id("btnChangeTeam")));
		changeteamnamebutton.click(); // without selecting user case
		Thread.sleep(2000);
		
		WebElement alertpopup = wait.until(ExpectedConditions
				.elementToBeClickable(By.id("tasc-popup-content")));
		String Alertmessage = alertpopup.getText();
		Assert.assertEquals(Alertmessage, "Please select user first.");
		System.out.println(Alertmessage);
		Thread.sleep(2000);
		
		driver.findElement(By.id("btnOk")).click(); //click on ok button
		Thread.sleep(2000);
		
	}
	
	@Test(priority = 10)//change the team with selecting user
	public void ChanegTeamwithuser() throws InterruptedException {
		
		if(!driver.findElement(By.xpath("//label[@class='label_check' and @for='18722']")).isSelected()) 
		{
		  driver.findElement(By.xpath("//label[@class='label_check' and @for='18722']")).click();
		}
				
		Thread.sleep(2000);
		WebElement changeteamnamebutton = wait. 
				until(ExpectedConditions.elementToBeClickable(By.id("btnChangeTeam")));
		changeteamnamebutton.click(); // with selecting user case
		Thread.sleep(2000); 
		
		WebElement changeteam = wait.
				until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"lightPopUp\"]/div/div/div/div[2]/div/span/span/span[2]/span")));
		changeteam.click();
		Thread.sleep(2000);
		
		String searchrole = "TEST323"; 
		List<WebElement> searchteam = driver.findElements(By.xpath("//ul[@id='TeamNamePopUp_listbox']/li"));
		// Loop through the options and select the one that matches
		for (WebElement changeteam1 : searchteam) {
			if (changeteam1.getText().toLowerCase().equals(searchrole.toLowerCase())) {
				changeteam1.click();
				// click the desired option
				Thread.sleep(2000);
			}
		}
		driver.findElement(By.id("btnSavePopUp2")).click(); //click on save button
		Thread.sleep(2000);
		
		WebElement alertpopup = wait.until(ExpectedConditions
				.elementToBeClickable(By.id("dvMSG")));
		String Alertmessage = alertpopup.getText();
		Assert.assertEquals(Alertmessage, "Teams has been updated successfully.");
		System.out.println(Alertmessage);
		Thread.sleep(4000);
		
	}
	
	@Test(priority = 11)//Delete the team with user
	public void DeleteTeamwithuser() throws InterruptedException{
		
		driver.findElement(By.xpath("//a[@class='icon circle delete white' and @onclick='DeleteTeam(732);']")).click();
		Thread.sleep(3000);
		
		WebElement deletedalertpopup = wait.
				until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dvMSG']/div")));
		String alertmessage = deletedalertpopup.getText();
		Assert.assertEquals(alertmessage, "All users of this team needs to be assigned to another team before deleting this team.");
		System.out.println(alertmessage);
		Thread.sleep(2000);
		
	}
			
	@Test(priority = 12)//Delete the team without user
	public void DeleteTeamwithoutuser() throws InterruptedException{
		
		WebElement deletebutton = wait.
				until(ExpectedConditions.elementToBeClickable
						(By.xpath("/html/body/div[4]/section/form/div[1]/div/div[1]/div/div[3]/div/div/div[2]/div/div[1]/div[1]/table/tbody/tr[1]/td[3]/a")));
		deletebutton.click();
		Thread.sleep(2000); 
				
		WebElement deletedalertpopup = wait.
				until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='tasc-alert']")));
		String alertmessage = deletedalertpopup.getText();
		Assert.assertEquals(alertmessage, "Team deleted successfully.");
		System.out.println(alertmessage);
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[@id='btnOk']")).click();//click on the OK button 
		Thread.sleep(2000);

	}
	 
	@AfterClass
	public void closeBrowser() throws InterruptedException {

		driver.close();
		driver.quit();
		
		

	}
}
