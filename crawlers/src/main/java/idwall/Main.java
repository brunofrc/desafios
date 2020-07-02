package idwall;

import idwall.application.IApplicationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;


@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    IApplicationController applicationController;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    public void run(String... args) {
        int option;

        Scanner scanner = new Scanner(System.in);

        System.out.printf("Choose the desired option:\n");
        System.out.printf("1- Print top threads\n");
        System.out.printf("2- Run Telegram Bot\n");

        option = scanner.nextInt();

        if(option == 1) {
            Scanner threadScanner = new Scanner(System.in);
            System.out.printf("Provide your threads using ';' as separator \n");

            String input = threadScanner.next();

            System.out.printf("Getting results...\n");

            applicationController.printSubRedditsDetail(input);

        }else{
            applicationController.startTelegramBot();
        }
    }
}
