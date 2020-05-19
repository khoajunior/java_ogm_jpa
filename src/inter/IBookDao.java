package inter;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import org.bson.Document;

import entities.Book;

public interface IBookDao extends Remote{
	public List<Book> getBooksByAuthor(String author) throws RemoteException;
	
	public List<Book> getBooksByAuthor2(String author) throws RemoteException;
	
	public List<Book> getBooksByTitle(String aValue) throws RemoteException;
	
	public Book getBook(Long id) throws RemoteException;
	
	public List<Document> getBookStatistics() throws RemoteException;
}
