package hello;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriver;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

@WebAppConfiguration
@ContextConfiguration(classes = Application.class)
public class Stepdefs {
	@Autowired private WebApplicationContext context;
	MockMvcHtmlUnitDriver driver;

	@Before
	public void setup() throws IOException {
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		driver = new MockMvcHtmlUnitDriver(mockMvc, true);
	}

	@After
	public void destroy() {
		if(driver != null) {
			driver.close();
		}
	}

	@When("^I go to the hello page$")
	public void i_go_to_the_hello_page() throws Throwable {
		driver.get("http://localhost/greeting/hello");
	}

	@When("^I click \"(.*?)\"$")
	public void i_click(String elementName) throws Throwable {
		driver.findElementByName(elementName).click();
	}

	@When("^I follow \"(.*?)\"$")
	public void i_follow(String linkText) throws Throwable {
		driver.findElementByLinkText(linkText).click();
	}

	@Then("^I expect to see \"(.*?)\"$")
	public void i_expect_to_see(String text) throws Throwable {
		assertTrue(driver.findElementById("message").getText().equals(text));
	}
}
