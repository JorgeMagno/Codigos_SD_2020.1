import java.rmi.Remote;
import java.rmi.RemoteException;
//definição interface remota de onde métodos das operações são invocados
public interface ICalculadora extends Remote{

	public double soma(double a, double b) throws RemoteException; //método para soma
	
	public double sub(double a, double b) throws RemoteException;//método para subtração
	
	public double div(double a, double b) throws RemoteException;//método para divisão
	
	public double multi(double a, double b) throws RemoteException;//método para multiplicação
}
