package com.testngTest1;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.nadsoft.ekart.base.Base;
import com.nadsoft.interview.utils.Utility;
import com.nadsoft.pages.AccSuccessPage;
import com.nadsoft.pages.RegisterPage;

public class VerifyRegisterFunctionalityTest extends Base {
	public WebDriver driver;
	// jada chize ni rkhte hum properties ke andar 2-4 chize jo common h
	// i.e=browsername,baseUrl,idPass like that
	public VerifyRegisterFunctionalityTest() {
		super();// ye base class se fetch krr rha he jo property humne propertyfiles se load ki
				// hai usme cnstrctrchaining
	}

	

	@BeforeMethod
	public void setup() {

		driver = initilizeBrowser(prop.getProperty("browserName"));// Base class se aaya ye ek line me ho gya kaam
		driver.get("https://tutorialsninja.com/demo/index.php?route=account/register");

	}

	@Test(priority = 2)
	public void verifyregisteringanaccwithmandatoryfeild() {
		RegisterPage register = new RegisterPage(driver);
		register.enterFirstName(dataprop.getProperty("FirstName"));
		register.enterLastName(dataprop.getProperty("LastName"));
		register.enterEmail(Utility.emailtimestamp());
		register.enterTelephone(dataprop.getProperty("Telephone"));
		register.enterPassword(prop.getProperty("ValidPass"));
		register.confirmPassword(prop.getProperty("ValidPass"));
		register.clickOnCheckbox();
		register.clickOnContinue();
		AccSuccessPage account = new AccSuccessPage(driver);
		String welcomemsg = account.getAccSuccesfullyCreatedMsg();
		String Expectedmsg = dataprop.getProperty("AccSuccessfullyCreatedMsg");
		Assert.assertEquals(welcomemsg, Expectedmsg, "Unable To Proceed");
	}

	@Test(priority = 3)
	public void verifyregisteraccbyprovidingallfeild() {
		RegisterPage register = new RegisterPage(driver);
		register.enterFirstName(dataprop.getProperty("FirstName"));
		register.enterLastName(dataprop.getProperty("LastName"));
		register.enterEmail(Utility.emailtimestamp());
		register.enterTelephone(dataprop.getProperty("Telephone"));
		register.enterPassword(prop.getProperty("ValidPass"));
		register.confirmPassword(prop.getProperty("ValidPass"));
		register.newsletteryesNo();
		register.clickOnCheckbox();
		register.clickOnContinue();
		AccSuccessPage account = new AccSuccessPage(driver);
		String welcomemsg = account.getAccSuccesfullyCreatedMsg();
		String Expectedmsg = dataprop.getProperty("AccSuccessfullyCreatedMsg");
		Assert.assertEquals(welcomemsg, Expectedmsg, "Unable To Proceed");

	}
//This method verifies with same email.
	@Test(priority = 1)
	public void verifyregisteringwithsameemail() {
		RegisterPage register = new RegisterPage(driver);
		register.enterFirstName(dataprop.getProperty("FirstName"));
		register.enterLastName(dataprop.getProperty("LastName"));
		register.enterEmail(prop.getProperty("ValidEmail"));
		register.enterTelephone(dataprop.getProperty("MyNoByMistakeIaddedThisOriginalPhoneNo"));
		register.enterPassword(prop.getProperty("ValidPass"));
		register.confirmPassword(prop.getProperty("ValidPass"));
		register.clickOnCheckbox();
		register.clickOnContinue();
		String warningmsg = register.warningMsgEmailAlreadyReg();
		String Expectedwarningmsg = dataprop.getProperty("EmailAddressIsAlreadyRegisteredWarningMsg");
		Assert.assertEquals(warningmsg, Expectedwarningmsg, "No Result");
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

}
