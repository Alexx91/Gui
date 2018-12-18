package threads;

public class BankTest {
    public static void main(String[] args) {
        new BankTest().run();
    }

    public void run() {
        BankAccount account = new BankAccount(100);

        Thread ryan = new Thread(new Runnable() {
            @Override
            public void run() {
                account.withdraw(50);
            }
        });
        ryan.setName("Ryan");

        Thread monica = new Thread(new Runnable() {
            @Override
            public void run() {
                account.withdraw(100);
            }
        });
        monica.setName("Monica");

        ryan.start();
        monica.start();
    }
}
