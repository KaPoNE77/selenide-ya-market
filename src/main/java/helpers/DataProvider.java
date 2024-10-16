package helpers;

import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author Сергей Канаев
 * Класс DataProvider - содержит поток аргументов, необходимых для параметризации теста
 */
public class DataProvider {

    /**
     * @return - возвращаем поток аргументов, необходимых для проведения тестов
     */
    public static Stream<Arguments> providerCheckingSmartphones() {
        return Stream.of(
                Arguments.of("https://market.yandex.ru/",
                        "Интернет-магазин Яндекс Маркет — покупки с быстрой доставкой",
                        "Электроника",
                        "Смартфоны",
                        List.of("Apple"),
                        List.of("iPhone"))
        );
    }
}
