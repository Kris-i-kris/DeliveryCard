package ru.netology.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;



public class DeliveryCardTest {

    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void test1() {
        open("http://localhost:9999/");
        //sleep(5000);
        $("[data-test-id='city'] input").setValue("Севастополь");
        String planningDate = generateDate(3, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.DELETE));
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Иван Петров-Сидоров");
        $("[data-test-id='phone'] input").setValue("+71231231212");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='notification']").shouldBe(Condition.visible, Duration.ofSeconds(15)).shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate));

    }
}
