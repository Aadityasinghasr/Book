package com.example.employeeSpring.Service;

import com.example.employeeSpring.Dto.BookDtoRequest;
import com.example.employeeSpring.Dto.BookDtoResponse;

import java.util.List;

public interface BookService {
    public BookDtoResponse createNewBook(BookDtoRequest bookdto);

    public List<BookDtoResponse> getAllBook(Integer id);

    public BookDtoResponse getBook(Integer id);

    public String updateBook(Integer id,BookDtoRequest bookDtoRequest);

    public String deleteBook(Integer id);

    List<BookDtoResponse> getBookByCategory(String category);
}
