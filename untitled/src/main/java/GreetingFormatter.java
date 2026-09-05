public class GreetingFormatter {

    public static String buildGreeting(String name, int port) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        if (port <= 0 || port > 65535) {
            throw new IllegalArgumentException("Некорректный номер порта: " + port);
        }
        return String.format("Hi %s, your port is %d", name, port);
    }
}
