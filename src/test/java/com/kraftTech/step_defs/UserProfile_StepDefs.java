package com.kraftTech.step_defs;

import com.kraftTech.pages.UserProfilePage;
import com.kraftTech.utilities.ExcelUtil;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class UserProfile_StepDefs {
    UserProfilePage userProfilePage=new UserProfilePage();
    @When("The user should be able navigate User Profile Page Tabs with {string}")
    public void the_user_should_be_able_navigate_user_profile_page_tabs_with(String tabName) {
        userProfilePage.navigateEditProfileTabs(tabName);
    }

    @Then("The user should be able to see last added record with {string}")
    public void the_user_should_be_able_to_see_last_added_record_with(String school) {
        String actualRecordName = userProfilePage.addedEducation(school);
        Assert.assertEquals(school,actualRecordName);
    }

    @Then("The user should be able to delete last added record with {string}")
    public void the_user_should_be_able_to_delete_last_added_record_with(String school) {
        userProfilePage.deleteEducationRecord(school);
    }

    @Then("The user should be able to see last added record with {string} via excel file {string}, {string}, {int}")
    public void the_user_should_be_able_to_see_last_added_record_with_via_excel_file(String schoolName, String path, String sheetName, Integer row) {
//        ExcelUtil excelUtil=new ExcelUtil(path,sheetName);
//        List<Map<String, String>> dataList = excelUtil.getDataList();
        List<Map<String, String>> dataList = userProfilePage.getExcelData(path, sheetName);

        String actualRecord = userProfilePage.addedEducation(dataList.get(row).get(schoolName));
        String expectedRecord=dataList.get(row).get(schoolName);

        Assert.assertEquals(actualRecord,expectedRecord);
    }
    @Then("The user should be able to delete last added record with {string} via excel file {string}, {string}, {int}")
    public void the_user_should_be_able_to_delete_last_added_record_with_via_excel_file(String schoolName, String path, String sheetName, Integer row) {
//        ExcelUtil excelUtil=new ExcelUtil(path,sheetName);
//        List<Map<String, String>> dataList = excelUtil.getDataList();
        List<Map<String, String>> dataList = userProfilePage.getExcelData(path, sheetName);

        String schoolNameRecord=dataList.get(row).get(schoolName);

        userProfilePage.deleteEducationRecord(schoolNameRecord);

    }
}
