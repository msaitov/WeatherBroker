package ru.msaitov.sender;

/**
 * Отправка названия города в очередь
 */
public interface SenderImpl {
    /**
     * Принимаем в аргементе txt название города и отправляем в очередь
     * @param txt
     */
    void sendCity(String txt);
}
