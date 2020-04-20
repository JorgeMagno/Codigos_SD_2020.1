import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculadoraServerSocket {

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket welcomeSocket; //variável que irá guardar socker do servidor
		DataOutputStream socketOutput; //variável que irá guardar o método que envia dados para o cliente
	    BufferedReader socketEntrada; //variável que irá guardar o método que recebe dados do cliente
	    Calculadora calc = new Calculadora(); //cria o objeto calculadora

		try {
			welcomeSocket = new ServerSocket(9090); //criação do socket servidor indicando a porta
		  int i=0; //número de clientes
	  
	      System.out.println ("Servidor no ar"); //aviso de que o servidor está funcionandp
	      while(true) { 
	  
	           Socket connectionSocket = welcomeSocket.accept(); //conexão aceita entre cliente e servidor
	           i++; //número de cliente acrescido
	           System.out.println ("Nova conexão cliente de número: " + i); //imprime um texto avisando da nova conexão feita
	           
	           //Interpretando dados do servidor
	           socketEntrada = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream())); //recebe os dados vindos do cleinte
               String operacao = socketEntrada.readLine(); //lê o número identificador da operação que a calcuadora deve realizar
               String oper1 = socketEntrada.readLine(); // lê o primeiro número
               String oper2 = socketEntrada.readLine(); //lê o segundo número
               String result = "";//criando string resultado
               int operation = Integer.parseInt(operacao); //convertando os bytes da variável "operacao" para um inteiro
               
               
               if (operation == 1) {
               result = "" + calc.soma(Double.parseDouble(oper1),Double.parseDouble(oper2)); //Chamando a calculadora para soma e armazenando o resultado da soma      
               }
             
               if (operation == 2) {
                   result = "" + calc.sub(Double.parseDouble(oper1),Double.parseDouble(oper2)); //Chamando a calculadora para subtração e armazenando o resultado da subtração    
                   }
             
               if (operation == 3) {
                   result = "" + calc.div(Double.parseDouble(oper1),Double.parseDouble(oper2)); //Chamando a calculadora para divisão e armazenando o resultado da divisão  
                   }
             
               if (operation == 4) {
                   result = "" + calc.multi(Double.parseDouble(oper1),Double.parseDouble(oper2)); //Chamando a calculadora para multiplicação e armazenando o resultado da multiplicação  
                   }
               
             //Enviando dados para o cliente
               socketOutput= new DataOutputStream(connectionSocket.getOutputStream()); //cria o canal para enviar os dados  	
	           socketOutput.writeBytes(result+ '\n'); //representação do resultado obtido
	           System.out.println (result);	//imprime o resultado no servidor           
	           socketOutput.flush(); //envia o resultado para o cliente
	           socketOutput.close(); //fecha o socket
	      }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    
	}

}
