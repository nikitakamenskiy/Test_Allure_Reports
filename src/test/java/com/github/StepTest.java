package com.github;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;


public class StepTest extends TestBase {
    private static String REPOSITORY = "eroshenkoam/allure-example";
    private static int ISSUE = 95;



    @Test
    public void testLambdaStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step ("Открываем главную страницу", () -> {
            open("https://github.com/");
        });
        step ("Ищем репозиторий " + REPOSITORY , () -> {
            $(".search-input-container").click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $("#query-builder-test").submit();
        });
        step ("Кликаем по ссылке репозитория " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Открываем  таб issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем наличие issues c номером " + ISSUE, () -> {
            $(withText("#" + ISSUE)).should(Condition.exist);
        });



        
        



    }
    @Test
    public void testAnnotatedStep() {
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepository(REPOSITORY);
        steps.openIssueRepository();
        steps.shouldIssueWithNumber(ISSUE);

    }
}
