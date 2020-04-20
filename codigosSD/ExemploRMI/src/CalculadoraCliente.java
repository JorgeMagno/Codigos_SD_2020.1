import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;


public class CalculadoraCliente {
	
	public static void main(String[] args) {
		Registry reg = null; 
		ICalculadora calc;	//cliente do tipo Icalculadora
		double oper1 = 0,oper2 = 0;
		int operacao = -1;
		Scanner scan = new Scanner(System.in); //objeto que será usado para guardar as informações digitadas pelo o usuário
	    //menu de opções
    	System.out.println ("Escolha a operação que deseja fazer:");
    	System.out.println ("1 - soma");
    	System.out.println ("2 - subtração");
    	System.out.println ("3 - divisão");
    	System.out.println ("4 - multiplicação");
    	
	    while (operacao < 1 || operacao > 4) { //laço exibindo para garantir que a opção digitada seja de 1 a 4
	    	operacao = scan.nextInt(); //guarda a opção escolhida pelo o usuário
	    	if (operacao < 1 || operacao > 4) System.out.println ("Opção inválida, digite novamente: "); //exibe mensagem caso escolha uma opção incorreta
	    }
	    
	    System.out.println ("Digite o primeiro número: "); //
	    oper1 = scan.nextDouble(); //guarda o primeiro operador digitado no console
	    System.out.println ("Digite o segundo número: ");
	    oper2 = scan.nextDouble(); //guarda o segundo operador digitado no console
	    
		try {
			reg = LocateRegistry.getRegistry(1099); //obtém o registry que está rodando na porta 1099
			calc = (ICalculadora) reg.lookup("calculadora"); //busca no registry a referência para o objeto com o nome calculadora
			if (operacao == 1) System.out.println("A resposta é: " + calc.soma(oper1,oper2)); //chama o objeto e imprime o resultado da soma
			if (operacao == 2) System.out.println("A resposta é: " + calc.sub(oper1,oper2)); //chama o objeto e imprime o resultado da subtração
			if (operacao == 3) System.out.println("A resposta é: " + calc.div(oper1,oper2)); //chama o objeto e imprime o resultado da divisão
			if (operacao == 4) System.out.println("A resposta é: " + calc.multi(oper1,oper2)); //chama o objeto e imprime o resultado da multiplicação
		} catch (RemoteException | NotBoundException e) {
				System.out.println(e);
				System.exit(0);
		}
	}		

}
