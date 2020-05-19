package daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Order;

public class OrderDAO {

	private EntityManager em;

	public OrderDAO() {
		em = MyEntityManager.getInstance().getEntityManager();
	}

	/**
	 * 1.	Thêm một số đối tượng hóa đơn (Order) và chi tiết hóa đơn (OrderDetail) vào cơ sở dữ liệu.
	 *+ addOrder(od:Order): boolean
	 *+ addOrderDetail(odd: OrderDetail): boolean
	 * @param order
	 * @return true nếu không trùng mã hóa đơn
	 * @return false nếu ngược lại
	 */
	public boolean addOrder(Order order) {
		boolean result = false;
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(order);
			tr.commit();
			result = true;
		}catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 5.	Cập nhật tổng tiền hóa đơn (total):
	 * + updateOrdersByTotal(): void
	 */
	public void updateOrdersByTotal() {
		List<?> temp = em.createNativeQuery("{}", Order.class).getResultList();
		temp.forEach(x -> {
			Order od = (Order) x;
			od.setTotal(od.getTotal());
			updateOrder(od);
		});
	}
	
	public boolean updateOrder(Order order) {
		boolean result = false;
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.merge(order);
			tr.commit();
			result = true;
		}catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}
		return result;
	}
	
	public Order getOrder(Long id) {
		return em.find(Order.class, id);
	}
	
}
