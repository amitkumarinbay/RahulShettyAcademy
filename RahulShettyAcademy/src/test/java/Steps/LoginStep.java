package Steps;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javaMethod.LoginMethod;

public class LoginStep {
	
	
	private WebDriver driver ;
	
	public LoginStep() {
		driver = Hooks.driver;
	}
	
	
	
	
	@Given("The user navigates to the login page of RahulShettyAcademy at {string}")
	public void the_user_navigates_to_the_login_page_of_rahul_shetty_academy_at(String string) {
		LoginMethod.launchBrowser(driver, string);
		
	}
	

	@When("The user enters a valid email ID {string}")
	public void the_user_enters_a_valid_email_id(String string) {
		LoginMethod.enterEmailId(driver, string);
	   
	}

	@And("The user enters the correct password {string}")
	public void the_user_enters_the_correct_password(String string) {
		LoginMethod.enterPassword(driver, string);
	    
	}

	@And("The user clicks on the login button")
	public void the_user_clicks_on_the_login_button() {
		LoginMethod.clickLogin(driver);
	   
	}

	@Then("The user should be successfully logged in and redirected to the homepage")
	public void the_user_should_be_successfully_logged_in_and_redirected_to_the_homepage() {
		LoginMethod.verifyUrl(driver);
	    
	}

}
