


class Tariff {
    private String name;
    private double subscriptionFee;
    private int numberOfCustomers;

    public Tariff(String name, double subscriptionFee, int numberOfCustomers) {
        this.name = name;
        this.subscriptionFee = subscriptionFee;
        this.numberOfCustomers = numberOfCustomers;
    }

    public String getName() {
        return name;
    }

    public double getSubscriptionFee() {
        return subscriptionFee;
    }

    public int getNumberOfCustomers() {
        return numberOfCustomers;
    }

    @Override
    public String toString() {
        return "Tariff{name='" + name + "', subscriptionFee=" + subscriptionFee +
                ", numberOfCustomers=" + numberOfCustomers + '}';
    }
}