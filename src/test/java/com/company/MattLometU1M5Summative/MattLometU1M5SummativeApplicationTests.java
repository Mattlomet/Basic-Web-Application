package com.company.MattLometU1M5Summative;

import com.company.MattLometU1M5Summative.dao.AuthorDaoImpl;
import com.company.MattLometU1M5Summative.dao.BookDaoImpl;
import com.company.MattLometU1M5Summative.dao.PublisherDaoImpl;
import com.company.MattLometU1M5Summative.dto.Author;
import com.company.MattLometU1M5Summative.dto.Book;
import com.company.MattLometU1M5Summative.dto.Publisher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MattLometU1M5SummativeApplicationTests {

	@Autowired
	protected BookDaoImpl bookDao;

	@Autowired
	protected AuthorDaoImpl authorDao;

	@Autowired
	protected PublisherDaoImpl publisherDao;


	@Before
	public void setUp(){
		List<Book> bookList = bookDao.getAllBooks();

		bookList.stream()
				.forEach(book -> bookDao.deleteBook(book.getBook_id()));

		List<Author> authList = authorDao.getAllAuthors();

		authList.stream()
				.forEach(author -> authorDao.deleteAuthor(author.getAuthor_id()));

		List<Publisher> publisherList = publisherDao.getAllPublishers();

		publisherList.stream()
				.forEach(publisher -> publisherDao.deletePublisher(publisher.getPublisher_id()));
	}

	@Test
	public void contextLoads() {
	}


	@Test
	public void getCreateDeleteAuthor(){
		Author author = new Author();
		author.setFirst_name("first");
		author.setLast_name("last");
		author.setStreet("street");
		author.setState("nj");
		author.setCity("city");
		author.setPostal_code("postal code");
		author.setPhone("phone");
		author.setEmail("email");

		author = authorDao.addAuthor(author);

		assertEquals(author, authorDao.getAuthor(author.getAuthor_id()));

		authorDao.deleteAuthor(author.getAuthor_id());
		assertEquals(0, authorDao.getAllAuthors().size());


	}

	@Test
	public void getCreateDeletePublisher(){

		Publisher publisher = new Publisher();
		publisher.setName("name");
		publisher.setStreet("street");
		publisher.setCity("city");
		publisher.setState("nj");
		publisher.setPostal_code("postal code");
		publisher.setPhone("phone");
		publisher.setEmail("email");

		publisher = publisherDao.addPublisher(publisher);

		assertEquals(publisher, publisherDao.getPublisher(publisher.getPublisher_id()));

		publisherDao.deletePublisher(publisher.getPublisher_id());

		assertEquals(0, publisherDao.getAllPublishers().size());


	}


	@Test
	public void getCreateDeleteBookAuthorPublisherTest(){
		Author author = new Author();
		author.setFirst_name("first");
		author.setLast_name("last");
		author.setStreet("street");
		author.setState("nj");
		author.setCity("city");
		author.setPostal_code("postal code");
		author.setPhone("phone");
		author.setEmail("email");

		author = authorDao.addAuthor(author);



		Publisher publisher = new Publisher();
		publisher.setName("name");
		publisher.setStreet("street");
		publisher.setCity("city");
		publisher.setState("nj");
		publisher.setPostal_code("postal code");
		publisher.setPhone("phone");
		publisher.setEmail("email");

		publisher = publisherDao.addPublisher(publisher);



		Book book = new Book();
		book.setIsbn("isbn");
		book.setPublish_date(LocalDate.of(10,10,10));
		book.setAuthor_id(author.getAuthor_id());
		book.setTitle("title");
		book.setPublisher_id(publisher.getPublisher_id());
		book.setPrice(5.50);


		book = bookDao.addBook(book);

		assertEquals(book, bookDao.getBook(book.getBook_id()));

		bookDao.deleteBook(book.getBook_id());

		assertEquals(0, bookDao.getAllBooks().size());

	}

	@Test
	public void getBookByAuthorTest(){


		Author author = new Author();
		author.setFirst_name("first");
		author.setLast_name("last");
		author.setStreet("street");
		author.setState("nj");
		author.setCity("city");
		author.setPostal_code("postal code");
		author.setPhone("phone");
		author.setEmail("email");

		author = authorDao.addAuthor(author);



		Publisher publisher = new Publisher();
		publisher.setName("name");
		publisher.setStreet("street");
		publisher.setCity("city");
		publisher.setState("nj");
		publisher.setPostal_code("postal code");
		publisher.setPhone("phone");
		publisher.setEmail("email");

		publisher = publisherDao.addPublisher(publisher);



		Book book = new Book();
		book.setIsbn("isbn");
		book.setPublish_date(LocalDate.of(10,10,10));
		book.setAuthor_id(author.getAuthor_id());
		book.setTitle("title");
		book.setPublisher_id(publisher.getPublisher_id());
		book.setPrice(5.50);


		book = bookDao.addBook(book);

		assertEquals(book, bookDao.getBookByAuthorId(author.getAuthor_id()));
	}

}
