package entities;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class OrderDetail {
	
	@ManyToOne
	@JoinColumn(name = "bookID")
	private Book book;
	
	private int quantity;
	private double price;
	
	/**
	 * @param book
	 * @param quantity
	 * @param price
	 */
	public OrderDetail(Book book, int quantity, double price) {
		this.book = book;
		this.quantity = quantity;
		this.price = price;
	}

	public OrderDetail() {
		this(new Book(), 0, 0.0);
	}

	/**
	 * @return the book
	 */
	public Book getBook() {
		return book;
	}

	/**
	 * @param book the book to set
	 */
	public void setBook(Book book) {
		this.book = book;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "OrderDetail [book=" + book + ", quantity=" + quantity + ", price=" + price + "]";
	}
	
	/**
	 * Tính thành tiền
	 * @return double the total line item
	 */
	public double getTotalLine() {
		return quantity * price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetail other = (OrderDetail) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		return true;
	}
	
}
