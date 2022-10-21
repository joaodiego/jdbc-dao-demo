package application;


import java.util.Date;

import entities.model.Department;
import entities.model.Seller;

public class Program {

	public static void main(String[] args) {

		Department obj = new Department(1, "Books");
		Seller seller = new Seller(1, "Fulano", "Fulano@gmail.com", new Date(), 2000.00, obj);
		System.out.println(seller);
	}
}
