import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
public class FCFS {
    public static void executarFCFS(String arquivoEntrada, String arquivoSaida) {
        List<Processo> processos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivoEntrada))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals("FCFS")) {
                    continue;
                }

                String[] dados = line.split(" ");
                int id = Integer.parseInt(dados[0]);
                int tempoChegada = Integer.parseInt(dados[1]);
                int tempoCPUNecessario = Integer.parseInt(dados[2]);
                processos.add(new Processo(id, tempoChegada, tempoCPUNecessario));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(processos, Comparator.comparingInt(p -> p.At));

        int tempoTotal = 0;
        for (Processo processo : processos) {
            if (tempoTotal < processo.At) {
                tempoTotal = processo.At;
            }

            processo.Ct = tempoTotal + processo.Bt;
            processo.Tat = processo.Ct - processo.At;
            processo.Wt = processo.Tat - processo.Bt;
            tempoTotal = processo.Ct;
        }

        double tempoMedioS = processos.stream().mapToDouble(p -> p.Tat).average().orElse(0);
        double tempoMedioE = processos.stream().mapToDouble(p -> p.Wt).average().orElse(0);

        try (PrintWriter pw = new PrintWriter(new FileWriter(arquivoSaida))) {
            pw.println("Pid\tAt\tBt\tCt\tTat\tWt");
            for (Processo processo : processos) {
                pw.println(
                        processo.id + "\t" +
                                processo.At + "\t" +
                                processo.Bt+ "\t" +
                                processo.Ct + "\t" +
                                processo.Tat + "\t" +
                                processo.Wt
                );
            }
            pw.println("\nTempo Médio no Sistema: " + tempoMedioS);
            pw.println("Tempo Médio de Espera: " + tempoMedioE);

            pw.println("\nGráfico de Gantt:");
            tempoTotal = 0;
            for (Processo processo : processos) {
                pw.printf("| P%d(%d-%d) ", processo.id, tempoTotal, processo.Ct);
                tempoTotal = processo.Ct;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nGráfico de Gantt:");
        tempoTotal = 0;
        for (Processo processo : processos) {
            System.out.printf("| P%d(%d-%d) ", processo.id, tempoTotal, processo.Ct);
            tempoTotal = processo.Ct;
        }

        System.out.println("\nTempo Médio no Sistema: " + tempoMedioS);
        System.out.println("Tempo Médio de Espera: " + tempoMedioE);
    }
}