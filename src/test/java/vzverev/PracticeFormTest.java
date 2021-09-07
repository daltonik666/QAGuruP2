package vzverev;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import java.io.File;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTest {

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.startMaximized = true;
    }

    @Test
    void positiveFillTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("Vladislav");
        $("#lastName").setValue("Zverev");
        $("#userEmail").setValue("zverev@mail.ru");
        $(byText("Male")).click();
        $("#userNumber").setValue("1234567890");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue("6");
        $(".react-datepicker__year-select").selectOptionByValue("1992");
        $(".react-datepicker__day--023").click();
        $("#subjectsInput").setValue("Computer Science").pressEnter();
        $(byText("Reading")).click();
        $("#uploadPicture").uploadFile(new File("src/test/java/resources/black.jpg"));
        $("#currentAddress").setValue("Address");
        $("#state").scrollIntoView(true).click();
        $(byText("Haryana")).click();
        $("#react-select-4-input").setValue("Panipat").pressEnter();
        $("#submit").click();

//        Проверки
//        $("tbody tr", 0).shouldBe("Student Name".)
        $x("//td[text()='Student Name']/following::td[1]").shouldHave(Condition.text("Vladislav Zverev"));
        $x("//td[text()='Student Email']/following::td[1]").shouldHave(Condition.text("zverev@mail.ru"));
        $x("//td[text()='Gender']/following::td[1]").shouldHave(Condition.text("Male"));
        $x("//td[text()='Mobile']/following::td[1]").shouldHave(Condition.text("1234567890"));
        $x("//td[text()='Date of Birth']/following::td[1]").shouldHave(Condition.text("23 July,1992"));
        $x("//td[text()='Subjects']/following::td[1]").shouldHave(Condition.text("Computer Science"));
        $x("//td[text()='Hobbies']/following::td[1]").shouldHave(Condition.text("Reading"));
        $x("//td[text()='Picture']/following::td[1]").shouldHave(Condition.text("black.jpg"));
        $x("//td[text()='Address']/following::td[1]").shouldHave(Condition.text("Address"));
        $x("//td[text()='State and City']/following::td[1]").shouldHave(Condition.text("Haryana Panipat"));
    }
    @AfterEach
    void closeBrowser() {
        closeWebDriver();
    }
}
