package helpers;

import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * @author Кирилл Филенков
 * Метод ожидания, описывающий алгоритм ожидания прогрузки страницы новыми элементами
 */

public class CustomWaits {

    /**
     *
     * @param elementXpath - XPath-селектор на появляющийся элемент загрузки
     * @param timeWaitLocated - время появления загрузки
     * @param timeWaitInvisible - время исчезновения загрузки
     */
    public static void waitInvisibleIfLocated(String elementXpath, int timeWaitLocated, int timeWaitInvisible){
        for(int i=0;i<timeWaitLocated;++i){
            if($$x(elementXpath).size()!=0){
                for(int j=0;;++j){
                    if($$x(elementXpath).size()==0)
                        break;
                    if(j+1==timeWaitInvisible)
                        Assertions.fail("Элемент "+elementXpath+" не исчез за "+ timeWaitInvisible + "секунд");
                    sleep(1000);
                }
            }
            sleep(1000);
        }
    }
}
