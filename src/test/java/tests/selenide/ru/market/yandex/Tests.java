package tests.selenide.ru.market.yandex;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import helpers.CustomWaits;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import pages.selenide.ru.market.yandex.*;
//import pages.selenide.GoogleMain;
//import pages.selenide.MarketYandexOpen;
//import pages.selenide.OpenExchangeResult;
//import pages.selenide.OpenMain;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static helpers.CustomWaits.waitInvisibleIfLocated;

/**
 *  @autor Сергей Канаев
 *  Данный класс Tests включает тесты сайта Яндекс Маркет на работу по поиску необходимых,
 *  по условиям фильтров,  элементов.
 * */
public class Tests extends BaseTests {

    /**
     * Объект для взаимодействия с сайтом
     */
    private MarketYandexOpen marketYandexOpen = new MarketYandexOpen();

    /**
     * Параметризированный метод testMarketYandexStepsAll - описывает алгоритм открытия сайта, переход в необходимый раздел, затем в подраздел,
     * выбор фильтра и проверку результатов вывода элементов, относительно выбранных фильтров
     * @param url - сайт страницы, на которую осуществляется переход
     * @param query - запрашиваемый тайтл страницы
     * @param section - инетересуемый раздел в каталоге
     * @param subSection - интересуемый подраздел раздела в каталоге
     * @param brands - переменная в фильтре на сайте, указывающая на список брендов, по которым осуществляем выбор элементов
     * @param models - модель для сравнения результатов страницы после выбора брендов
     */
    @Feature("Проверка сайта Яндекс Маркет")
    @DisplayName("Проверка сайта Яндекс Маркет - в степах")
    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("helpers.DataProvider#providerCheckingSmartphones")
    public void testMarketYandexStepsAll(String url, String query, String section, String subSection, List<String> brands, List<String> models) {
        marketYandexOpen.openSite(url, query, MarketYandexMain.class)
                .openCatalog()
                .electronicsSection(section)
                .openSubsection(subSection)
                .checkPage(subSection, MarketYandexSetFilters.class)
                .chooseBrands(brands, MarketYandexCheckSetParameters.class)
                .checkModels(models);
    }
}





