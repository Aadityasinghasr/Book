package com.example.employeeSpring.Repo;

import com.example.employeeSpring.Dto.BookDtoResponse;
import com.example.employeeSpring.Model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepo extends JpaRepository<BookModel,Integer> {
    List<BookDtoResponse> findByCategory(String category);
}
