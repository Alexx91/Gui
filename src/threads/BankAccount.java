package threads;

public class BankAccount {
    int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            try {
                if (Thread.currentThread().getName().equalsIgnoreCase("ryan")) {
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance -= amount;
        }

        System.out.println("Balance: " + balance);

    }
}
