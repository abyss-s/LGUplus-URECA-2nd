package com.uplus.eureka.book.model.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class Book implements Serializable {
	private static final long serialVersionUID=1L;
	
	@Schema(description = "책 일련 번호" , example = "979-11-602")
	private String isbn         ;
	@Schema(description = "제목" , example="스프링 부트 3 백엔드 개발자 되기2" )
	private String title        ;
	@Schema(description = "저자" , example="동글양" )
	private String author       ;
	@Schema(description = "가격" , example="42000" )
	private int 	price       ;
	@Schema(description = "책 상세 설명" , example="자바 백엔드 개발자가 되고 싶다면" )
	private String describ      ;
	@Schema(description = "이미지" , example="979-11-602.png" )
	private String img          ;

	public Book() {}
	public Book(String isbn, String title, String author, int price, String describ, String img) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.price = price;
		this.describ = describ;
		this.img = img;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescrib() {
		return describ;
	}
	public void setDescrib(String describ) {
		this.describ = describ;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", author=" + author + ", price=" + price + ", describ="
				+ describ + ", img=" + img + "]";
	}
	
	
}
