import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Calculadora  implements ICalculadora { //define a implementação da interface remota ICalculadora no servidor
	
	private static int chamadassoma = 1;
	private static int chamadassub = 1;
	private static int chamadasdiv = 1;
	private static int chamadasmulti = 1;

	public double soma(double a, double b) throws RemoteException { //realiza a operação de soma e retorna o resultado
		System.out.println("Método soma. Número chamado: " + chamadassoma++);//imprime o número de chamada do método soma
		return a + b;
	}

	public double sub(double a, double b) throws RemoteException { //realiza a operação de subtração e retorna o resultado
		System.out.println("Método subtração. Número chamado: " + chamadassub++);//imprime o número de chamada do método subtração
		return a - b;
	}
	
	public double div(double a, double b) throws RemoteException { //realiza a operação de divisão e retorna o resultado
		System.out.println("Método divisão. Número chamado: " + chamadasdiv++);//imprime o número de chamada do método divisão
		return a / b;
	}
	
	public double multi(double a, double b) throws RemoteException {//realiza a operação de multiplicação e retorna o resultado
		System.out.println("Método multiplicação. Número chamado: " + chamadasmulti++);//imprime o número de chamada do método multiplicação
		return a * b;
	}
	
	public static void main(String[] args) throws AccessException, RemoteException, AlreadyBoundException  {
		Calculadora calculadora = new Calculadora(); //cria o objeto calculadora que implementa a interface remota		
		Registry reg = null; 
		ICalculadora stub = (ICalculadora) UnicastRemoteObject.
				exportObject(calculadora, 1100); //implementa um stub através do UnicastRemoteObject e exportObject(), que exporta a calculadora para a porta 1100
		try { //tenta criar o registro
			System.out.println("Creating registry..."); //imprime que o registry está sendo criado
			reg = LocateRegistry.createRegistry(1099); //criação do registry e o colocando na porta 1099
			System.out.println("Registry criado!"); //avisa que o registry foi criado
		} catch (Exception e) {
			try {
				reg = LocateRegistry.getRegistry(1099); //usado para obter o registry da porta 1099 caso ele já tenha sido criado
			} catch (Exception e1) {
				System.exit(0);
			}
		}
		reg.rebind("calculadora", stub); //liga o nome calculadora ao stub criado
	}
}
