package pages.selenide.ru.market.yandex;

import com.codeborne.selenide.Configuration;
import helpers.CustomWaits;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;
import static helpers.CustomWaits.waitInvisibleIfLocated;

/**
 * Класс MarketYandexSetFilters описывает алгоритм выбора в фильтре интересующий бренд и
 * дальнейшую загрузку страницы с интересующими элементами
 */

public class MarketYandexSetFilters extends BasePage {

    /**
     * Переменная String endOfSelect, которая содержит окончание XPath-select
     */
    private String endOfSelect = "']";

    /**
     * Переменная String selectBrandName, которая содержит ХPath-select на наименование производителя
     */
    private String selectBrandName = "//div[@data-apiary-widget-name='@search/Filters']//div[@data-zone-name='Filter']//span[text()='";

    /**
     * @param String loadPage - селектор xpath элемента прогрузки изменений
     */
    private static final String loadPage = "//div[@data-auto='preloader']";

    /**
     * Метод, который описывает алгоритм:
     * 1) выбор в фильтрах бренда модели
     * 2) ожидаем загрузку элементов после выбора
     * @param brands - модели искомых элементов
     * @param typeNextPage - следующая страница
     * @return - возвращает следующую страницу
     * @param <T> - параметр следующей страницы
     */
    @Step("Выбираем бренд в фильтрах подраздела")
    public <T extends BasePage> T chooseBrands(List<String> brands, Class<T> typeNextPage) {
        for (String brand : brands) {
            String brandName = selectBrandName + brand + endOfSelect;
            $x(brandName).click();
            waitInvisibleIfLocated(loadPage,7,12);
        }
        return typeNextPage.cast(page(typeNextPage));
    }
}
