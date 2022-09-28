package chat_blocking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient2 extends Thread {

    private final String SERVER_ADDRESS = "127.0.0.1";
    private Socket clientSocket2;
    private Scanner scanner;
    private BufferedWriter out;
    private BufferedReader in;

    public ChatClient2() {
        scanner = new Scanner(System.in);

    }

    public void run() {
        try {
            clientSocket2 = new Socket(SERVER_ADDRESS, ChatServer.PORTA);
            this.out = new BufferedWriter(new OutputStreamWriter(clientSocket2.getOutputStream()));
            this.in = new BufferedReader(new InputStreamReader(clientSocket2.getInputStream()));
            System.out.println("Cliente conectado no servidor em: " + SERVER_ADDRESS + ":" + ChatServer.PORTA);

            numEnviado();

        } catch (Exception e) {
            System.out.println("Erro na execução do programa: " + e);
        }
    }

    private void numEnviado() throws IOException {
        int num;
        System.out.println("Informe um valor a ser enviado para o servidor ou digite 0 para finalizar o processo : ");
        num = scanner.nextInt();
        out.write(num);
        out.newLine();
        out.flush();

        int dobroDoNumero = in.read();

        System.out.println(
                "Valor enviado para o servidor foi:  " + num
                + "\nValor recebido pelo servidor foi: :  " + dobroDoNumero);
        
        
        clientSocket2.close();
        System.out.println("Cliente finalizado");
        
    }

    public static void main(String[] args) {
        ChatClient2 cliente2 = new ChatClient2();
        cliente2.start();
        System.out.println("Numero enviado com sucesso");

    }

}
