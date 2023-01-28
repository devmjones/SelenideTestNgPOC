package com.selenidetestng.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.selenidetestng.pages.MainPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.*;

import static org.testng.Assert.*;
import static com.codeborne.selenide.Selenide.*;


public class MainPageTest {
    MainPage mainPage = new MainPage();

    @DataProvider(name = "search-terms")
    public Object[][] dataProvFunc() {
        return new Object[][]{{"Selenium"}, {"TestNg"}};
    }

    @BeforeClass(alwaysRun = true)
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        open("https://www.jetbrains.com/");
    }


    @Test(dataProvider = "search-terms", groups = {"search"})
    public void search(String searchTerm) {
        mainPage.searchForText(searchTerm);
        assertTrue(mainPage.searchResultPanelShowingCorrectTerm(searchTerm), "Search results panel should show " + searchTerm);
    }

    @Test(groups = {"navigation"})
    public void navigationToAllTools() {
        mainPage.navigateToAllTools();
        assertTrue(mainPage.onAllToolsPage(), "Should be on the All Tools page");
    }
}
