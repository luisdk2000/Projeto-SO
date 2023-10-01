import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Nome do .txt: ");
            String fileName = reader.readLine();
            reader.close();

            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
            String algoritmo = fileReader.readLine();
            fileReader.close();

            if ("FCFS".equals(algoritmo)) {
                FCFS.executarFCFS(fileName, "saida_fcfs.txt");
            } else if ("SJF".equals(algoritmo)) {
                System.out.println("Ainda não feito");
            } else if ("RR".equals(algoritmo)) {
                System.out.println("Ainda não feito");
            } else if ("Priority".equals(algoritmo)) {
                System.out.println("Ainda não feito");
            } else {
                System.out.println("Algoritmo não existente!.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}