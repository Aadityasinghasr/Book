package com.example.employeeSpring.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDtoRequest {
    private String title;

    private String author;

    private String category;

    private double price;

    private double rating;

    private String date;
}
