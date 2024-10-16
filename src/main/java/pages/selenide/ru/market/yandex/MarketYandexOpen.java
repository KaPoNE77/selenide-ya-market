package pages.selenide.ru.market.yandex;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

/**
 * @author Сергей Канаев
 * Класс MarketYandexOpen - описывает открытие необходимого сайта и проверка его тайтла
 */
public class MarketYandexOpen {

    /**
     * Метод openSite - описывает открытие необходимого сайта и проверка его тайтла
     * @param url - адрес сайта
     * @param title - заголовок сайта
     * @param typeNextPage - страница, на которую осуществляется переход по завершению метода
     * @return - возвращаем класс возвращаемого значения
     * @param <T> - возвращаемый элемент из списка
     */
    @Step("Переходим по ссылке с текстом: {linkName}")
    public <T extends BasePage> T openSite(String url, String title, Class<T> typeNextPage) {
        open(url);
        Selenide.Wait().withTimeout(Duration.ofSeconds(15)).until(driver -> driver.getTitle().equals(title));

        return typeNextPage.cast(page(typeNextPage));
    }
}
