import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CatsFactsService service = new CatsFactsService();

        try {
            List<CatFact> factsWithUpvotes = service.getFactsWithUpvotes();

            System.out.println("Найдено фактов с проставленными upvotes: " + factsWithUpvotes.size());
            System.out.println();

            factsWithUpvotes.forEach(System.out::println);

        } catch (IOException e) {
            System.err.println("Ошибка при получении данных: " + e.getMessage());
        }
    }
}