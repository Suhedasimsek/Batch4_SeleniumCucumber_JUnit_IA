package com.kraftTech.step_defs;

import com.kraftTech.pages.DashboardPage;
import com.kraftTech.pages.LoginPage;
import com.kraftTech.utilities.ConfigurationReader;
import com.kraftTech.utilities.Driver;
import com.kraftTech.utilities.ExcelUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class Login_StepDefs {
    LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();

    @Given("The user is on the login page")
    public void the_user_is_on_the_login_page() {
        Driver.get().get(ConfigurationReader.get("url"));
    }

    @When("The user logs in with Melih's credentials")
    public void the_user_logs_in_with_melih_s_credentials() {
        loginPage.login();
    }

    @Then("The user should be able to login")
    public void the_user_should_be_able_to_login() {
        String currentUrl = Driver.get().getCurrentUrl();
        String expectedUrl = "https://www.krafttechexlab.com/index";
        Assert.assertEquals(expectedUrl, currentUrl);
    }

    @When("The user logs in with Semih's credentials")
    public void theUserLogsInWithSemihSCredentials() {
        System.out.println("Login with Semih credentials");
    }

    @When("The user logs in with Mehmet's credentials")
    public void theUserLogsInWithMehmetSCredentials() {
        System.out.println("Login with Mehmet credentials");
    }

    @When("The user logs in with Rosa's credentials")
    public void theUserLogsInWithRosaSCredentials() {
        loginPage.login("rosa@test.com", "Test123456");
    }

    @When("The user logs in with valid credentials {string} and {string}")
    public void the_user_logs_in_with_valid_credentials_and(String userEmail, String password) {
        loginPage.login(userEmail, password);
    }

    @When("The user logins with following list")
    public void the_user_logins_with_following_list(List<String> userInfo) {
        loginPage.login(userInfo.get(0), userInfo.get(1));

        String actualUsername = dashboardPage.userName.getText();
        Assert.assertEquals(userInfo.get(2), actualUsername);
    }

    @When("The user logins with following map")
    public void the_user_logins_with_following_map(Map<String ,String> userInfo) {
      loginPage.login(userInfo.get("userEmail"),userInfo.get("password"));
        String actualUsername = dashboardPage.userName.getText();
        Assert.assertEquals(userInfo.get("username"), actualUsername);
    }

    @When("The user logins with valid credentials {string} and {string} from excel file {string}, {string},{int}")
    public void the_user_logins_with_valid_credentials_and_from_excel_file(String yourEmail, String password, String path, String sheetName, Integer row) {
//        ExcelUtil excelUtil=new ExcelUtil(path,sheetName);
//
//        List<Map<String, String>> dataList = excelUtil.getDataList();

        List<Map<String, String>> dataList = loginPage.getExcelData(path, sheetName);
        String email=dataList.get(row).get(yourEmail);
        String pass=dataList.get(row).get(password);

        loginPage.login(email,pass);
    }


}
