package com.example.employeeSpring.Service;

import com.example.employeeSpring.Dto.BookDtoRequest;
import com.example.employeeSpring.Dto.BookDtoResponse;
import com.example.employeeSpring.Dto.LoginUserDto;
import com.example.employeeSpring.Dto.RegisterUser;
import com.example.employeeSpring.Model.BookModel;
import com.example.employeeSpring.Repo.BookRepo;
import com.example.employeeSpring.Repo.UserRepo;
import com.example.employeeSpring.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserRepo userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepo userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUser input) {
        User user = new User();
        user.setEmail(input.getEmail());
        user.setFullName(input.getFullName());
        user.setPassword(passwordEncoder.encode(input.getPassword()));


        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }

    @Service
    public static class BookServiceImpl implements BookService {
        @Autowired
        private BookRepo bookRepo;
        @Override
        public BookDtoResponse createNewBook(BookDtoRequest bookdtoRequest) {
            BookModel book = new BookModel();
            book.setTitle(bookdtoRequest.getTitle());
            book.setRating(bookdtoRequest.getRating());
            book.setAuthor(bookdtoRequest.getAuthor());
            book.setPrice(bookdtoRequest.getPrice());
            book.setCategory(bookdtoRequest.getCategory());
            book.setDate(bookdtoRequest.getDate());

            BookModel savedBook = bookRepo.save(book);

            BookDtoResponse dto = new BookDtoResponse();
            dto.setTitle(savedBook.getTitle());
            dto.setRating(savedBook.getRating());
            dto.setDate(savedBook.getDate());
            dto.setPrice(savedBook.getPrice());
            dto.setCategory(savedBook.getCategory());
            dto.setAuthor(savedBook.getAuthor());

            return dto;
        }

        @Override
        public List<BookDtoResponse> getAllBook(Integer id) {
            List<BookDtoResponse> dtoResponses = new ArrayList<>();
            List<BookModel> books = bookRepo.findAll();

            for (BookModel book:books){
                BookDtoResponse dto = new BookDtoResponse();
                dto.setTitle(book.getTitle());
                dto.setRating(book.getRating());
                dto.setDate(book.getDate());
                dto.setPrice(book.getPrice());
                dto.setCategory(book.getCategory());
                dto.setAuthor(book.getAuthor());
                dtoResponses.add(dto);
            }

            return dtoResponses;
        }

        @Override
        public BookDtoResponse getBook(Integer id) {
            Optional<BookModel> book = bookRepo.findById(id);
            BookModel b = book.get();
            if (book.isPresent()){
                BookDtoResponse dto = new BookDtoResponse();
                dto.setTitle(b.getTitle());
                dto.setRating(b.getRating());
                dto.setDate(b.getDate());
                dto.setPrice(b.getPrice());
                dto.setCategory(b.getCategory());
                dto.setAuthor(b.getAuthor());
                return dto;
            }
            return null;
        }

        @Override
        public String updateBook(Integer id, BookDtoRequest bookDtoRequest) {
            Optional<BookModel> book = bookRepo.findById(id);
            if (book.isPresent()){
                BookModel b = new BookModel();
                b.setTitle(b.getTitle());
                b.setRating(b.getRating());
                b.setDate(b.getDate());
                b.setPrice(b.getPrice());
                b.setCategory(b.getCategory());
                b.setAuthor(b.getAuthor());

                BookModel books = bookRepo.save(b);

                BookDtoResponse dto = new BookDtoResponse();
                dto.setTitle(b.getTitle());
                dto.setRating(b.getRating());
                dto.setDate(b.getDate());
                dto.setPrice(b.getPrice());
                dto.setCategory(b.getCategory());
                dto.setAuthor(b.getAuthor());

            }
            return "Updated Successfully";
        }

        @Override
        public String deleteBook(Integer id) {
            Optional<BookModel> book = bookRepo.findById(id);
            if (book.isPresent()){
                bookRepo.deleteById(id);
                return "deleted";

            }
            return "Not Found";
        }

        @Override
        public List<BookDtoResponse> getBookByCategory(String category) {
            return bookRepo.findByCategory(category);
        }
    }
}
