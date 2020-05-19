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
@Table(name = "books")
public class Book {
	
	@Id
	private Long id;
	private String title;
	private String isbn;
	private int pageCount;
	private LocalDate publishedDate;
	private String status;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> authors;
	
	/**
	 * @param id
	 * @param title
	 * @param isbn
	 * @param pageCount
	 * @param publishedDate
	 * @param status
	 * @param authors
	 */
	
	public Book(Long id, String title, String isbn, int pageCount, LocalDate publishedDate, String status,
			List<String> authors) {
		this.id = id;
		this.title = title;
		this.isbn = isbn;
		this.pageCount = pageCount;
		this.publishedDate = publishedDate;
		this.status = status;
		this.authors = authors;
	}

	/**
	 * Default constructor
	 */
	public Book() {
		this(0l);
	}
	
	/**
	 * @param id
	 */
	public Book(Long id) {
		this(id, "no-title", "no-isbn",0,LocalDate.now(),"no-status",new ArrayList<String>());
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * @param isbn the isbn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * @return the pageCount
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * @param pageCount the pageCount to set
	 */
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * @return the publishedDate
	 */
	public LocalDate getPublishedDate() {
		return publishedDate;
	}

	/**
	 * @param publishedDate the publishedDate to set
	 */
	public void setPublishedDate(LocalDate publishedDate) {
		this.publishedDate = publishedDate;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the authors
	 */
	public List<String> getAuthors() {
		return authors;
	}

	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", isbn=" + isbn + ", pageCount=" + pageCount
				+ ", publishedDate=" + publishedDate + ", status=" + status + ", authors=" + authors + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Book other = (Book) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
