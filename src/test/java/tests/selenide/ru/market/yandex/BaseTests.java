package tests.selenide.ru.market.yandex;

import allure.selenide.CustomAllureSelenide;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

/**
 * @author Сергей Канаев
 * Класс BaseTests - класс для всех тестов, который содержит общие настройки, которые выполняются перед тестами.
 */
public class BaseTests {

    /**
     * Метод setup - описывает алгоритм действий один раз перед всеми тестами
     */
    @BeforeAll
    public static void setup() {
        Configuration.pageLoadStrategy="none";

        SelenideLogger.addListener("AllureSelenide",new CustomAllureSelenide().screenshots(true).savePageSource(true));
    }
}
