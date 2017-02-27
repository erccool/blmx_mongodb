package net.tutorial.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.tutorial.utilities.MongoService;

public class Runner {

	public static void main(String[] args) {
		Map<String, String> document = new HashMap<String, String>();
		MongoService ms = MongoService.getInstance();
		ms.connect();
		
		ms.delete("58b3259338849b9a1ab22f4a");
		
//		ArrayList<Map<String, String>> documents = ms.allDocuments();
//		System.out.println(documents);
//		for (Map<String, String> obj : documents) {
//			System.out.println(obj.get("name"));
//		}
		
//		document.put("name", "Dean Alexander Yuan B. Torralba");
//		document.put("email", "day.torralba@gmail.com");
//		document.put("mobile", "09272145538");		
//		ms.create(document);
		
		System.out.println("Run!");
		ms.close();
	}

}
