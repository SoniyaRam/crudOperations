package com.startProject.crudOperation.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Book {
	private int id;
	private String name;
	private String author;
	private int cost;
	}