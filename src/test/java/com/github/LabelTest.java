package com.github;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;


public class LabelTest {
    @BeforeAll
    static void beforAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    @Feature("Issue в репозитории")
    @Story("Создание Issue")
    @Owner("Nikita")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Github", url = "https://github.com/allure-framework/allure2/releases/tag/2.32.0")
    @DisplayName("Создание Issue для авторизованного пользователя")


    public void testIssueName() {
        SelenideLogger.addListener("allure", new AllureSelenide());


        open("https://github.com/");
        $(".search-input-container").click();
        $("#query-builder-test").sendKeys("eroshenkoam/allure-example");
        $("#query-builder-test").submit();

        $(linkText("eroshenkoam/allure-example")).click();
        $("#issues-tab").click();
        $(withText("#95")).should(Condition.exist);

        
        



    }
}
