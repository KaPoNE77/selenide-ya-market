package pages.selenide.ru.market.yandex;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.*;
import static helpers.CustomWaits.waitInvisibleIfLocated;

/**
 * @author Сергей Канаев
 * Класс MarketYandexCheckSetParameters - описывает алгоритм проверки результатов вывода страницей элементов
 * после установки интересующих нас фильтров
 */
public class MarketYandexCheckSetParameters extends BasePage {

    /**
     * Переменная String titleOfElement с селектом для определения названия интересующего элемента
     */
    private String titleOfElement = "//div[@data-auto-themename='listDetailed']//span[@role='link']";

    /**
     * Метод checkModels - описывает проверку результатов на поиск интересующего элемента и переход на следующую
     * страницу, при условии её наличия.
     * @param models - список моделей для сравнения на странице
     */
    @Step("Проверка результатов поиска на наличие интересующего товара: {models}")

    public void checkModels(List<String> models) {
        List<String> titleSet = new ArrayList<>();
        boolean newElementsLoaded;

        do {
            newElementsLoaded = false;
            executeJavaScript("window.scrollTo(0,document.body.scrollHeight);");
            sleep(4000);
            List<SelenideElement> elementsOnPage = $$x(titleOfElement);
            for (SelenideElement elementOn : elementsOnPage) {
                 String title = elementOn.getText();
                 if (newElementsLoaded) {
                     titleSet.add(title);
                 }
            }
        } while (newElementsLoaded);

        for (String title :titleSet) {
            Assertions.assertTrue(title.contains(models.toString()),
                    "На текущей странице название модели не соответствует ожидаемой: " + models);
        }
        System.out.println("Тест окончен, на текущих страницах названия моделей соответствуют ожидаемой: " + models);
    }
}
