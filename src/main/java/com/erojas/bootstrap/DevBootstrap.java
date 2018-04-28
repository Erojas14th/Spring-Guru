package com.erojas.bootstrap;

import com.erojas.model.Author;
import com.erojas.model.Book;
import com.erojas.model.Publisher;
import com.erojas.repositories.AuthorRepository;
import com.erojas.repositories.BookRepository;
import com.erojas.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {


    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }

    public void initData(){


        Publisher publisher = new Publisher();
        publisher.setName("foo");
        publisherRepository.save(publisher);

        // Eric
        Author eric = new Author("Eric","Evans");
        Book ddd = new Book("Domain Driven Design","1234",publisher);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        // Rod
        Author rod = new Author("Rod","Johnson");
        Book noEJB = new Book("J2EE Development without EJB","23444",publisher);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

    }
}
