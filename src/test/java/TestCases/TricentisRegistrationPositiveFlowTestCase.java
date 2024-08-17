package TestCases;

import org.testng.annotations.Test;

import BaseTest.BaseTestTricentis;

public class TricentisRegistrationPositiveFlowTestCase extends BaseTestTricentis{
	@Test
	public void testRegistrationPositiveFlow() throws InterruptedException {

		landingPageTricentisPage.nav_automobileButtonWebElementClick();

		System.out.println("SELENIUM AUTOMATION entered testRegistrationPositiveFlow..");
	}
}
