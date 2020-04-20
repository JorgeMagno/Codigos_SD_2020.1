import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class CalculadoraClientSocket {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		double oper1 = 0,oper2 = 0;
		int operacao = 0; //escolher operações :1-somar 2-subtrair 3-dividir 4-multiplicar
		String result = ""; // criação da variável que vai armazenar o resultado do cálculo quando vier a resposta do servidor
		Calculadora ola = new Calculadora(); //criando um objeto ola
	    Scanner scan = new Scanner(System.in); //objeto que será usado para guardar as informações digitadas pelo o usuário
	    //menu de opções de operações
    	System.out.println ("Escolha a operação que deseja fazer:");
    	System.out.println ("1 - soma");
    	System.out.println ("2 - subtração");
    	System.out.println ("3 - divisão");
    	System.out.println ("4 - multiplicação");
    	
	    while (operacao < 1 || operacao > 4 ) { //laço para garantir que a opção digitada seja de 1 a 4
	    	operacao = scan.nextInt(); //guarda a opção escolhida pelo o usuário
	    	if (operacao < 1 || operacao > 4) System.out.println ("Opção inválida! "); //exibe mensagem caso escolha uma opção incorreta
	    }
	    
	    System.out.println ("Digite o primeiro número: "); //
	    oper1 = scan.nextDouble(); //guarda o primeiro operador digitado no console
	    System.out.println ("Digite o segundo número: ");
	    oper2 = scan.nextDouble(); //guarda o segundo operador digitado no console
	    
        try {

        	//Conexão com o Servidor
            Socket clientSocket = new Socket("192.168.1.8", 9090); //criação do socket do lado cliente informando o ip do servidor e a porta que vai se comunicar
            DataOutputStream socketSaidaServer = new DataOutputStream(clientSocket.getOutputStream()); //cria o canal para enviar os dados
            System.out.println ("Nova conexão com o servidor: " + ola.sayHello("Jorge", "Magno")); //imprindo uma mensagem para mostrar que a conexão com servidor foi feita
            
            //Enviando os dados
            socketSaidaServer.writeBytes(operacao+"\n"); //representação do parâmetro identificando a operação a ser realizada
            socketSaidaServer.writeBytes(oper1+ "\n"); //representação do primeiro número
            socketSaidaServer.writeBytes( oper2+ "\n"); //representação do segundo número
            socketSaidaServer.flush(); //método que envia os dados escritos (operacao, oper1, oper2) para o servidor

            //Recebendo a resposta
            BufferedReader messageFromServer = new BufferedReader
                    (new InputStreamReader(clientSocket.getInputStream())); //canal para receber a resposta vinda do servidor
            result = messageFromServer.readLine(); //espera e interpreta o resultado que veio do servidor
            
            System.out.println("resultado = "+result); //imprime o resultado
            clientSocket.close(); //fecha o socket

        } catch (IOException e) {
            e.printStackTrace();
        }


	}

}
