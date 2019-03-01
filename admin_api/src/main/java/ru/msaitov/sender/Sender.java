package ru.msaitov.sender;

/**
 * Отправка названия города в очередь
 */
public interface Sender {
    /**
     * Принимаем в аргементе txt название города и отправляем в очередь
     * @param txt
     */
    void sendCity(String txt);
}
