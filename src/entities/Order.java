package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	private Long orderID;
	private String employeeName;
	private LocalDate orderDate;
	private LocalDate shippedDate;
	private double total;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<OrderDetail> listItem;

	/**
	 * @param orderID
	 * @param employeeName
	 * @param orderDate
	 * @param shippedDate
	 */
	public Order(Long orderID, String employeeName, LocalDate shippedDate) {
		this.orderID = orderID;
		this.employeeName = employeeName;
		this.orderDate = LocalDate.now();
		this.shippedDate = shippedDate;
		
		listItem = new ArrayList<OrderDetail>();
		total = 0.0;
	}

	public Order(Long orderID) {
		this(orderID, "no-name", LocalDate.now());
	}

	public Order() {
		this(0l);
	}

	/**
	 * @return the orderID
	 */
	public Long getOrderID() {
		return orderID;
	}

	/**
	 * @param orderID the orderID to set
	 */
	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}

	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}

	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	/**
	 * @return the orderDate
	 */
	public LocalDate getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return the shippedDate
	 */
	public LocalDate getShippedDate() {
		return shippedDate;
	}

	/**
	 * @param shippedDate the shippedDate to set
	 */
	public void setShippedDate(LocalDate shippedDate) {
		this.shippedDate = shippedDate;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(double total) {
		this.total = total;
	}

	/**
	 * Tính tổng tiền hóa đơn
	 * @param total the total to set
	 */
	public double getTotal() {
		double total = 0.0;
		for(OrderDetail odd : listItem) {
			total += odd.getTotalLine();
		}
		return total;
	}
	
	/**
	 * Câu 1 - 2 Thêm 1 đối tượng chi tiết hóa đơn
	 * + addOrderDetail(odd: OrderDetail): boolean
	 * @param book
	 * @param quantity
	 * @param price
	 * @return true nếu chưa có cuốn sách trong danh sách
	 * @return false nếu ngược lại
	 */
	public boolean addOrderDetail(Book book, int quantity, double price) {
		OrderDetail odd = new OrderDetail(book, quantity, price);
		if(!listItem.contains(odd)) {
			listItem.add(odd);
			this.total += odd.getTotalLine();
			return true;
		}
		return false;
	}

	/**
	 * @return the listItem
	 */
	public List<OrderDetail> getListItem() {
		return listItem;
	}

	/**
	 * @param listItem the listItem to set
	 */
	public void setListItem(List<OrderDetail> listItem) {
		this.listItem = listItem;
		this.setTotal(this.getTotal());
	}

	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", employeeName=" + employeeName + ", orderDate=" + orderDate
				+ ", shippedDate=" + shippedDate + ", total=" + total + ", listItem=" + listItem + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderID == null) ? 0 : orderID.hashCode());
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
		Order other = (Order) obj;
		if (orderID == null) {
			if (other.orderID != null)
				return false;
		} else if (!orderID.equals(other.orderID))
			return false;
		return true;
	}

}
