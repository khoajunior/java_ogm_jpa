package ui;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import daos.BookDAO;
import daos.OrderDAO;
import entities.Book;
import entities.Order;
import entities.OrderDetail;

public class MainTest {
	public static void main(String[] args) throws RemoteException {
		
		BookDAO bookDAO = new BookDAO();
		OrderDAO orderDAO = new OrderDAO();


//		System.out.println("Câu 1 - Thêm hóa đơn 1"); 
//		Order order = new Order(1l, "Trần Thị Hồng Hoa", LocalDate.now().plusDays(5));
//		order.addOrderDetail(bookDAO.getBook(178l), 10, 100);
//		order.addOrderDetail(bookDAO.getBook(126l), 5, 200);
//
//		boolean result = orderDAO.addOrder(order); 
//		if(result)
//			System.out.println("Thêm thành công!"); 
//		else
//			System.out.println("Thêm thất bại!");
//		
//		
//		System.out.println("======================================");
//		System.out.println("Câu 1 - Thêm hóa đơn 2"); 
//		Order order2 = new Order(2l, "Trần Thị Hồng Loan", LocalDate.now().plusDays(10));
//		order2.addOrderDetail(bookDAO.getBook(178l), 5, 100);
//		order2.addOrderDetail(bookDAO.getBook(126l), 2, 200);
//		order2.addOrderDetail(bookDAO.getBook(124l), 3, 120);
//		
//
//		boolean result2 = orderDAO.addOrder(order2); 
//		if(result2)
//			System.out.println("Thêm thành công!"); 
//		else
//			System.out.println("Thêm thất bại!");
		 
		
		
//		System.out.println("======================================");
//
//		Order order = orderDAO.getOrder(1l);
//
//		System.out.println("======================================");
//		List<OrderDetail> listItem = new ArrayList<OrderDetail>(); 
//		listItem.add(new OrderDetail(bookDAO.getBook(178l), 15, 100)); 
//		listItem.add(new OrderDetail(bookDAO.getBook(126l), 15, 200));
//
//		order.setListItem(listItem);
//
//		orderDAO.updateOrder(order);
		 

		
//		System.out.println("======================================");
//		System.out.println("Câu 2 - Cách 1"); 
//		List<Book> books =  bookDAO.getBooksByAuthor("Kalen Delaney"); 
//		if(!books.isEmpty())
//			books.forEach(x -> System.out.println(x)); 
//		else
//			System.out.println("Không tìm thấy!");
//
//		System.out.println("======================================");
//		System.out.println("Câu 2 - Cách 2"); 
//		List<Book> books2 =  bookDAO.getBooksByAuthor2("Kalen Delaney"); 
//		if(!books2.isEmpty())
//			books2.forEach(x -> System.out.println(x)); 
//		else
//			System.out.println("Không tìm thấy!");
		 
		

//		System.out.println("======================================");
//		System.out.println("Câu 3 - tìm theo từng từ"); 
//		List<Book> books3 =  bookDAO.getBooksByTitle("java core"); 
//		if(!books3.isEmpty()) books3.forEach(x -> System.out.println(x)); 
//		else 
//			System.out.println("Không tìm thấy!");
//
//		System.out.println("======================================");
//		System.out.println("Câu 3 - tìm cả cụm"); 
//		List<Book> books4 = bookDAO.getBooksByTitle("\"java core\""); 
//		if(!books4.isEmpty())
//			books4.forEach(x -> System.out.println(x)); 
//		else
//			System.out.println("Không tìm thấy!");
		 
		
		System.out.println("======================================");
		System.out.println("Câu 4 - Thống kê tổng số lượng từng cuốn sách đã bán");

		List<Document> book5;
		try {
			book5 = bookDAO.getBookStatistics();
			book5.forEach(x ->  System.out.println(x));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
		 
//		Thêm từ console của mongodb 1 hóa đơn như sau:
//		db.orders.insert({ "_id" : NumberLong(4), "employeeName" : "Trần Hoàng Hùng", "total" : 123456, "orderDate" : "2019-10-25", "shippedDate" : "2019-11-04", "listItem" : [ { "quantity" : NumberInt(25), "price" : 100, "bookID" : NumberLong(178) }, { "quantity" : NumberInt(100), "price" : 200, "bookID" : NumberLong(126) }, { "quantity" : NumberInt(1), "price" : 120, "bookID" : NumberLong(124) } ] })
//		Ta thấy total = 123456 là giá trị sai
//		Thực thi phương thức updateOrdersByTotal, và xem lại kết quả total = 22620
		 
//		System.out.println("======================================");
//		System.out.println("Câu 5 - Cập nhật tổng tiền hóa đơn (total)");
//		orderDAO.updateOrdersByTotal();
		 
	}
}
