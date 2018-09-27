package problems.multithreading;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class Account {
    private int balance = 600;
    private String AccountHolder;

    private int getBal() {
        return balance;
    }

    Account(String accountHolder) {
        this.AccountHolder = accountHolder;
    }

    public synchronized boolean withdraw(String person, int bal) {
        try {
            if (balance >= bal) {
                System.out.println(person + " " + "is trying to withdraw " + bal);
                Thread.sleep(500);
                balance = balance - bal;
                System.out.println(person + " " + "has completed the withdrawal of " + bal + " remaining amount " + balance + " in " + AccountHolder + " account");
                return true;
            } else {
                System.out.println(AccountHolder + "'s account " + "doesn't have enough money for withdrawal for " + person);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void amain(String args[]) throws InterruptedException {
        Account account = new Account("Umesh");

        Thread T1 = new Thread(() -> {
            int amount = 0;
            while (account.withdraw("Akansha", 100)) {
                amount += 100;
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Akansha got " + amount + " in total");
        });
        Thread T2 = new Thread(() -> {
            int amount = 0;
            while (account.withdraw("Umesh", 100)) {
                amount += 100;
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Umesh got " + amount + " in total");
        });

        T1.start();
        T2.start();

        T2.join();
        T1.join();
        System.out.println("Final balance " + account.getBal());
    }

    public static void main(String args[]) {
        Account account = new Account("Umesh");
        Observable<String> akansha$ = Observable.interval(100, TimeUnit.MILLISECONDS)
                .map(item -> "Akansha");
        Observable<String> umesh$ = Observable.interval(100, TimeUnit.MILLISECONDS)
                .map(item -> "Umesh").doOnSubscribe(name -> System.out.println("Umesh sub"));
        /*
        PublishSubject<Boolean> bool$ = PublishSubject.create();
        bool$
                .switchMap(bool -> bool ? umesh$ : akansha$)
                .takeWhile(name -> account.withdraw(name, 100))
                .subscribe(name -> {
                    if (name.equals("Akansha"))
                        bool$.onNext(true);
                    else bool$.onNext(false);
                });
        bool$.onNext(false);
        */
        akansha$
                .mergeWith(umesh$)
                .map(name -> {
                    Thread.sleep(1000);
                    return name;
                })
                .subscribe(
                        name -> System.out.println(name)
                );
        Observable.never().blockingFirst();
    }
}