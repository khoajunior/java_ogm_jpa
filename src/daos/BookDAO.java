package daos;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.bson.Document;

import com.google.gson.Gson;

import entities.Book;
import inter.IBookDao;

public class BookDAO extends UnicastRemoteObject implements IBookDao{
	
	private EntityManager em;
	
	public BookDAO() throws RemoteException {
		em = MyEntityManager.getInstance().getEntityManager();
	}
	
	/**
	 * 2. Tìm tất cả các cuốn sách của một tác giả nào đó khi biết tên tác giả (Author) (tìm tuyệt đối)
	 * + getBooksByAuthor(author: String) : List<Book>
	 * Cách 1: Dùng $unwind
	 * db.books.aggregate([{'$unwind':'$authors'},{'$match':{'authors':'Lasse Koskela'}}])
	 * @param author
	 * @return List<Book>
	 */
	public List<Book> getBooksByAuthor(String author) throws RemoteException {
		List<Book> books = new ArrayList<>();
		String sqlString = "db.books.aggregate([{'$unwind':'$authors'},{'$match':{'authors':'" + author + "'}}])";
		List<?> temp = em.createNativeQuery(sqlString).getResultList();
		temp.forEach(x -> {
			Object[] o = (Object[]) x;
			books.add(em.find(Book.class, o[0]));
		});	
		return books;
	}
	
	/**
	 * 2. Tìm tất cả các cuốn sách của một tác giả nào đó khi biết tên tác giả (Author) (tìm tuyệt đối)
	 * + getBooksByAuthor(author: String) : List<Book>
	 * Cách 2: Dùng $in
	 * db.books.find({'authors':{'$in':['Michael J. Barlotta']}})
	 * @param author
	 * @return List<Book>
	 */
	public List<Book> getBooksByAuthor2(String author) throws RemoteException {
		List<Book> books = new ArrayList<>();
		String sqlString = "db.books.find({'authors':{'$in':['" + author + "']}})";
		List<?> temp = em.createNativeQuery(sqlString, Book.class).getResultList();
		temp.forEach(x -> {
			books.add((Book) x);
		});	
		return books;
	}
	
	/** 
	 * 3.	Tìm các cuốn sách khi biết tựa sách (tìm tương đối): Yêu cầu dùng Text search trên cột tựa sách (title).
	 * + getBooksByTitle(String aValue): List<Book>
	 * Bước 1: Tạo text index trên cột title --> db.books.createIndex('title':'text')
	 * Bước 2: db.books.find({'$text':{'$search':'java core'}}) --> title có chứa từ java hoặc từ core
	 * 		   db.books.find({'$text':{'$search':'\"java core\"'}}) --> title chứa cả cụm "java core"
	 * @param aValue
	 * @return List<Book>
	 */
	public List<Book> getBooksByTitle(String aValue) throws RemoteException {
		List<Book> books = new ArrayList<>();
		String sqlString = "db.books.find({'$text':{'$search':'" + aValue + "'}})";
		List<?> temp = em.createNativeQuery(sqlString, Book.class).getResultList();
		temp.forEach(x -> {
			books.add((Book) x);
		});	
		return books;
	}
	
	/**
	 * 4.	Thống kê tổng số lượng từng cuốn sách đã bán. Thông tin bao gồm: mã sách, tựa sách, isbn và tổng số lượng.
	 * + getBookStatistics(): List<Document>
	 * db.orders.aggregate([{'$project':{'listItem':1,'_id':0}},{'$unwind':'$listItem'},{'$group':{'_id':'$listItem.bookID', 'total':{'$sum':'$listItem.quantity'}}},{'$lookup':{from:'books',localField:'_id',foreignField:'_id', as:'book_sold'}}])
	 * @return List<Document>
	 */
	public List<Document> getBookStatistics() throws RemoteException {
		List<Document> docs = new ArrayList<>();
		
		String listItemQuery = "{'$project':{'listItem':1,'_id':0}},{'$unwind':'$listItem'},{'$group':{'_id':'$listItem.bookID', 'total':{'$sum':'$listItem.quantity'}}}";
		String query = "db.orders.aggregate([" + listItemQuery +"])";
		List<?> temp = em.createNativeQuery(query).getResultList();
		temp.forEach(x -> {
//			System.out.println(new Gson().toJson(x));
			Object[] o = (Object[]) x;
			Book b;
			try {
				b = getBook((long) o[0]);
				Document d = Document.parse(new Gson().toJson(b)).append("total", o[1]);
				docs.add(d);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
		return docs;
	}
	
	public Book getBook(Long id) throws RemoteException{
		return em.find(Book.class, id);
	}
	
	
	
}
