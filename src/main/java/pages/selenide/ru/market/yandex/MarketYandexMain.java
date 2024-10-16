package pages.selenide.ru.market.yandex;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;
import static helpers.CustomWaits.waitInvisibleIfLocated;

/**
 * @author Сергей Канаев
 * Класс MarketYandexMain описывает алгоритм перехода с главной страницы сайта в раздел "Электроника",
 * а из раздела Электроника в подраздел "Смартфоны"
 */
public class MarketYandexMain extends BasePage{

    /**
     * Переменная searchButtonCatalog, которая содержит xpath-select на кнопку Каталог
     */
    private String searchButtonCatalog = "//noindex/div[@data-apiary-widget-name='@light/NavigationMenu']//ancestor::button";

    /**
     * Переменная searchButtonElectronics, которая содержит xpath-select на кнопку подраздела Электроника
     */
    private String searchButtonElement = "//div[@data-zone-name='catalog-content']//span[text()='";

    /**
     * Переменная String endOfSelect, которая содержит окончание XPath-select
     */
    private String endOfSelect = "']";

    /**
     * Переменная buttonSearchLapTop, которая содержит xpath-select на кнопку подраздела Смартфоны
     * сам раздел "Смартфоны" убран для более хорошей параметризации теста
     */
    private String searchButtonSubElement = "//div[@role='dialog']//div[@role='heading']//a[text()='";

    /**
     * Переменная lapTopTitle, которая содержит xpath-select на заголовок подраздела Ноутбука
     */
    private String subElementTitle = "//div[@id='/content/page/fancyPage']//div[@data-zone-name='searchTitle']/h1[text()='Смартфоны']";

    /**
     * @param String loadPage - селектор xpath элемента прогрузки изменений
     */
    private static final String loadPage = "//div[@data-auto='preloader']";

    /**
     * Метод открывает каталог нажатием на соответствующую кнопку
     * @return - возвращает текущую страницу
     */
    @Step("Нажимаем на кнопку Каталог")
    public MarketYandexMain openCatalog() {
        Configuration.timeout = 10000;
        waitInvisibleIfLocated(loadPage,2,5);

        $x(searchButtonCatalog).shouldBe(visible, Duration.ofSeconds(8)).click();
        return this;
    }

    /**
     * Метод наводит курсор на раздел Электроника в сплывающем окне
     * @param section - интересующий раздел в меню "Каталог"
     * @return - возвращает текущую страницу
     */
    @Step("Наведение курсора на: {section}")
    public MarketYandexMain electronicsSection(String section) {
        String elementOfSection = searchButtonElement + section + endOfSelect;
        $x(elementOfSection).shouldBe(visible, Duration.ofSeconds(8)).hover();
        return this;
    }

    /**
     * Метод наводит курсор на указанный элемент и затем кликает на него
     * @param subSection - интересующий подраздел в меню "Каталог"
     * @return - возвращает текущую страницу
     */
    @Step("Выбираем необходимый подраздел: {subSection}")
    public MarketYandexMain openSubsection(String subSection) {
        String elementOfSubSection = searchButtonSubElement + subSection + endOfSelect;
        $x(elementOfSubSection).shouldBe(visible, Duration.ofSeconds(8)).hover().click();
        return this;
    }

    /**
     * Метод проверяет заголовок страницы и переходит на следующую страницу.
     * @param subSection - ожидаемая страница, на которую осуществляется переход
     * @param typeNextPage - класс следующей страницы
     * @return - следующая страница
     * @param <T> - тип следующей страницы
     */
    @Step("Проверяем, что перешли на страницу: {subSection}")
    public <T extends BasePage> T checkPage(String subSection, Class<T> typeNextPage) {
        $x(subElementTitle).shouldBe(visible, Duration.ofSeconds(10)).shouldHave(text(subSection));
        String subTitle = $x(subElementTitle).getText();
        System.out.println("Тестируемый раздел товаров: " + subTitle);

        Assertions.assertEquals(subSection, subTitle,
                "Заголовок искомой страницы не соответствует ожидаемому разделу");

        return typeNextPage.cast(page(typeNextPage));
    }
}
