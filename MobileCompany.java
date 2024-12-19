import java.util.Scanner;

public class MobileCompany {
    private TariffSet<Tariff> tariffs;

    public MobileCompany() {
        tariffs = new TariffSet<>();
        tariffs.add(new PrepaidTariff("Basic Tariff Prepaid", 5.0, 100));
        tariffs.add(new PostpaidTariff("Standard Tariff Postpaid", 15.0, 200));
        tariffs.add(new PrepaidTariff("Premium Tariff Prepaid", 18.0, 150));
        tariffs.add(new PostpaidTariff("Ultra Tariff Postpaid", 50.0, 50));
    }

    public void findTariff(double minCost, double maxCost) {
        System.out.println("Tariffs in the cost range " + minCost + " to " + maxCost + ":");
        tariffs.stream()
               .filter(t -> t.getSubscriptionFee() >= minCost && t.getSubscriptionFee() <= maxCost)
               .forEach(System.out::println);
    }

    public static void main(String[] args) {
        MobileCompany company = new MobileCompany();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter minimum cost:");
        double minCost = scanner.nextDouble();
        System.out.println("Enter maximum cost:");
        double maxCost = scanner.nextDouble();

        company.findTariff(minCost, maxCost);
        scanner.close();
    }
}
