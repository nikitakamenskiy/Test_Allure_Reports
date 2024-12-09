package cloud.autotests;

import com.codeborne.selenide.Configuration;
import models.CreateTestCaseBody;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.issue;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;
import static org.openqa.selenium.bidi.script.LocalValue.setValue;



public class AllureTestAuthorization {
    String username = "allure8";
    String password = "allure8";
    String authCookieKey = "ALLURE_TESTOPS_SESSION";
    Integer projectId = 4512;

    @Test
    void AllureTestAuthorizationForm() {

        Configuration.baseUrl = "https://allure.autotests.cloud";


        step("Authorization", () -> {
            open("/login");
            $(byName("username")).setValue(username);
            $(byName("password")).setValue(password);
            $("[data-testid=\"button__login-submit\"]").click();
        });
        step("Create New Project", () -> {
            $("[data-testid=\"button__new_project\"]").click();
            $(byName("name")).setValue("edwedewdw");
            $(byName("abbr")).setValue("ee");
            $(".MarkdownEditorBox__textarea").setValue("edwedewdw");
            $("[data-testid='button__form_submit']").click();
            sleep(800000);


        });
    }





@Test
void AllureApiTestAuthorizationForm () {


    String username = "allure8";
    String password = "allure8";
    String authCookieKey = "ALLURE_TESTOPS_SESSION";
    Integer projectId = 4512;
    Configuration.baseUrl = "https://allure.autotests.cloud";


    step("Authorization", () -> {
        String authorizationCookie = given()

                .contentType("application/x-www-form-urlencoded")
                .formParam("username", username)
                .formParam("password", password)
                .when()
                .post("https://allure.autotests.cloud/api/login/system")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .cookie(authCookieKey);

//open("/api/login/props");
//              Cookie  authCookie = new Cookie(authCookieKey, authorizationCookie);
//              getWebDriver().manage().addCookie(authCookie);
//open("/");
    });
    step("Create New Project", () -> {
                CreateTestCaseBody createTestCaseBody = new CreateTestCaseBody();
                createTestCaseBody.setName("erwrewwer");

                given()
                        .cookie(authCookieKey)
                        .contentType("application/json")
                        .body(createTestCaseBody)
                        .when()
                        .queryParam("projectId", projectId)
                        .post("https://allure.autotests.cloud/api/testcasetree/leaf")
                        .then()
                        .body("name", is("erwrewwer"));

            });
} }

//@Test
//void AllureCreateNewTestCase() {
//
//    Configuration.baseUrl = "https://allure.autotests.cloud";
//
//    step("Allure Create New TestCase", () -> {
//        open("/project/4511/test-cases/35044?treeId=0");
//        $("[data-testid='input__create_test_case']").setValue("Название тест-кейса");
//        $(".TreeNodeName").shouldHave(text("Название тест-кейса"));
//    });
//    step("Create New Project", () -> {
//        $("[data-testid=\"button__new_project\"]").click();
//        $(byName("name")).setValue("edwedewdw");
//        $(byName("abbr")).setValue("ee");
//        $(".MarkdownEditorBox__textarea").setValue("edwedewdw");
//        $("[data-testid='button__form_submit']").click();
//        sleep(800000);
//
//
//    });
//}

