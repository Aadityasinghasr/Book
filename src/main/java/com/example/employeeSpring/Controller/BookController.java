package com.example.employeeSpring.Controller;

import com.example.employeeSpring.Dto.BookDtoRequest;
import com.example.employeeSpring.Dto.BookDtoResponse;
import com.example.employeeSpring.Model.BookModel;
import com.example.employeeSpring.Service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookservice;

    @PostMapping("/addBook")
    public ResponseEntity<BookDtoResponse> addBook(@RequestBody BookDtoRequest bookDto){
        return new ResponseEntity<>(bookservice.createNewBook(bookDto), HttpStatus.OK);

    }


    @GetMapping("/getBook/{x}")
    public ResponseEntity<BookDtoResponse> getbook(@PathVariable Integer x){
        return new ResponseEntity<>(bookservice.getBook(x),HttpStatus.OK);
    }

    @GetMapping("/getAllBook/{x}")
    public List<BookDtoResponse> getAllBook(@PathVariable Integer x){
        return bookservice.getAllBook(x);
    }

    @GetMapping("/category/{x}")
    public List<BookDtoResponse> getBookByCategory(@PathVariable String x){
        return bookservice.getBookByCategory(x);
    }

    @PatchMapping("/updateBook/{x}")
    public String updateBook(@PathVariable Integer x,@RequestBody  BookDtoRequest bookDtoRequest){
        return bookservice.updateBook(x, bookDtoRequest);
    }

    @DeleteMapping("/deleteBook/{x}")
    public String deleteBook(@PathVariable Integer x){
        return bookservice.deleteBook(x);
    }
}
