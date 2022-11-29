package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;


    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,PublisherRepository publisherRepository){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String ... args) throws Exception {

    Publisher pb = new Publisher("Bd Unirii, 32" , "Bucharest","Romania", "31312", "TestPublisher");
    publisherRepository.save(pb);
    System.out.println("No of publishers : " + publisherRepository.count());

    Author eric = new Author("Steve", "Evans");
    Book book = new Book("Domain driven design","12312312312");

    eric.getBooks().add(book);
    book.getAuthors().add(eric);
    book.setPublisher(pb);
    pb.getBooks().add(book);

    authorRepository.save(eric);
    bookRepository.save(book);
    publisherRepository.save(pb);

    Author rod = new Author("Rod", "Evans");
    Book noEJB = new Book("J2EE Development without EJB","323213123");

    rod.getBooks().add(noEJB);
    noEJB.getAuthors().add(rod);
    noEJB.setPublisher(pb);

    authorRepository.save(rod);
    bookRepository.save(noEJB);

    pb.getBooks().add(noEJB);
    publisherRepository.save(pb);

    System.out.println("Started in Bootstrap");
    System.out.println("No of Books : " + bookRepository.count());

    System.out.println("Publisher number of books " + pb.getBooks().size());



    }

}
