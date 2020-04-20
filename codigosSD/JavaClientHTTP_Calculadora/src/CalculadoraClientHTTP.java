import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class CalculadoraClientHTTP {

	public static void main(String[] args) {
		
	String result="";
	double oper1 = 0,oper2 = 0;
	int operacao = 0; //escolher operações :1-somar 2-subtrair 3-dividir 4-multiplicar
	Scanner scan = new Scanner(System.in); //objeto que será usado para guardar as informações digitadas pelo o usuário
    //menu de opções de operação
	System.out.println ("Escolha a operação que deseja fazer:");
	System.out.println ("1 - soma");
	System.out.println ("2 - subtração");
	System.out.println ("3 - multiplicação");
	System.out.println ("4 - divisão");
	
    while (operacao < 1 || operacao > 4) {//laço para garantir que a opção digitada seja de 1 a 4
    	operacao = scan.nextInt();//guarda a opção escolhida pelo o usuário
    	if (operacao < 1 || operacao > 4) System.out.println ("Opção inválida!"); //exibe mensagem caso escolha uma opção incorreta
    }
    
    System.out.println ("Digite o primeiro número: ");
    oper1 = scan.nextDouble(); //guarda o primeiro operador digitado no console
    System.out.println ("Digite o segundo número: ");
    oper2 = scan.nextDouble(); //guarda o segundo operador digitado no console
    
    try {

       URL url = new URL("https://double-nirvana-273602.appspot.com/?hl=pt-BR"); //chama o endereço do servidor
       HttpsURLConnection conn = (HttpsURLConnection) url.openConnection(); // abre uma conexão https
       //seta alguns parâmetros da conexão  https como timeout e tipo de método 
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true) ;

        //ENVIO DOS PARAMETROS
        OutputStream os = conn.getOutputStream(); //pega a saída do https
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8")); //diz que vai enviar os dados com codificação UTF-8
        writer.write("oper1="+oper1+"&oper2="+oper2+"&operacao="+operacao); //estruturação da mensagem dizendo quais os 2 operadores e qual operação será realizada: 1-somar 2-subtrair 3-multiplicar 4-dividir
        writer.flush(); //envia os dados
        writer.close(); //termina a escrita dos dados
        os.close(); //fecha a saída https

        int responseCode=conn.getResponseCode(); //pega a mensagem de status do https
        if (responseCode == HttpsURLConnection.HTTP_OK) { //condição para caso a conexão do servidor tenha acontecido

            //RECBIMENTO DOS PARAMETROS
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"));//diz que vai receber os dados com codificação UTF-8
            StringBuilder response = new StringBuilder(); // cria uma string que pode ser modificada dinamicamente
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) { //laço para ler as linhas da resposta e obter o resultado da operação
                response.append(responseLine.trim()); //
            }
            result = response.toString(); //converte o resultado e guarda
            System.out.println("Resposta do Servidor PHP = "+result); //imprime a o resultado da operação
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
	}
}
