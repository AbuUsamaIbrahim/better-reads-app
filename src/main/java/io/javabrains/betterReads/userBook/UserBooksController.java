package io.javabrains.betterReads.userBook;

import java.lang.ProcessBuilder.Redirect;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import io.javabrains.betterReads.book.Book;
import io.javabrains.betterReads.book.BookRepository;
import io.javabrains.betterReads.user.BooksByUser;
import io.javabrains.betterReads.user.BooksByUserRepository;

@Controller
public class UserBooksController {

  @Autowired
  UserBooksRepository userBooksRepository;

  @Autowired
  BooksByUserRepository booksByUserRepository;

  @Autowired
  BookRepository bookRepository;
  
  @PostMapping(value = "/addUserBook")
  public ModelAndView addBook(@RequestBody MultiValueMap<String, String> formData,
      @AuthenticationPrincipal OAuth2User principal) {

    if (principal == null && principal.getAttribute("login") == null) {
      return null;
    }
    String bookId = formData.getFirst("bookId");
    Optional<Book> optionalBook = bookRepository.findById(bookId);
    if(!optionalBook.isPresent()){
      return new ModelAndView("redirect:/");
    }
    Book book = optionalBook.get();

    UserBooksPrimaryKey key = new UserBooksPrimaryKey();
    UserBooks userBooks = new UserBooks();
    String userId = principal.getAttribute("login");
    key.setUserId(userId);
    key.setBookId(bookId);

    int rating = Integer.parseInt(formData.getFirst("ratring"));

    userBooks.setKey(key);
    userBooks.setStartDate(LocalDate.parse(formData.getFirst("startDate")));
    userBooks.setEndDate(LocalDate.parse(formData.getFirst("completedDate")));
    userBooks.setReadingStatus(formData.getFirst("readingStatus"));
    userBooks.setRatring(rating);
    userBooksRepository.save(userBooks);

    BooksByUser booksByUser = new BooksByUser();
        booksByUser.setId(userId);
        booksByUser.setBookId(bookId);
        booksByUser.setBookName(book.getName());
        booksByUser.setCoverIds(book.getCoverIds());
        booksByUser.setAuthorNames(book.getAuthorName());
        booksByUser.setReadingStatus(formData.getFirst("readingStatus"));
        booksByUser.setRating(rating);
        booksByUserRepository.save(booksByUser);

    return new ModelAndView("redirect:/book/" + bookId);

  }
}
