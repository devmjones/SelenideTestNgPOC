package com.selenidetestng.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    public SelenideElement seeDeveloperToolsButton = $x("//*[@data-test-marker='Developer Tools']");
    public SelenideElement findYourToolsButton = $x("//*[@data-test='suggestion-action']");
    public SelenideElement searchButton = $("[data-test='site-header-search-action']");
    public SelenideElement searchInput = $("input[data-test='search-input']");
    public SelenideElement searchBarShowingResultsForTitle = $("div.quick-search__results-title");
    public SelenideElement searchBarResultsPanel = $("span.quick-search__results-query");


    public void searchForText(String text) {
        this.searchButton.click();
        this.searchInput.sendKeys(text);
        this.searchInput.shouldHave(attribute("value", text));
    }

    public boolean searchResultPanelShowingCorrectTerm(String searchTerm) {
        this.searchBarShowingResultsForTitle.shouldHave(text(searchTerm));
        return this.searchBarResultsPanel.getText().equals(searchTerm);

    }

    public void navigateToAllTools() {
        this.seeDeveloperToolsButton.click();
        this.findYourToolsButton.click();
        $("#products-page").shouldBe(visible);
    }

    public boolean onAllToolsPage() {
        return Selenide.title().equals("All Developer Tools and Products by JetBrains");
    }
}
