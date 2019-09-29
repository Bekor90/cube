package com.cube.main;

import static spark.Spark.before;
import static spark.Spark.options;
import static spark.Spark.post;

import com.cube.view.ViewCube;

import spark.servlet.SparkApplication;


public class Application implements SparkApplication {
	
	 @Override
	 public void init() {
		 
		 options("/*", (request, response) -> {
			 String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
			 if (accessControlRequestHeaders != null) {
			 response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
			 }
			 String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
			 if (accessControlRequestMethod != null) {
			 response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
			 }
			 return "OK";
		 }); 
	
		 before((request,response)->{
			 response.header("Access-Control-Allow-Origin", "*");
			 response.header("Access-Control-Expose-Headers", "user");
		 }); 
		 
		 //Endpoint login
		 post("/private/post/generate_cube","application/json", (req,res) -> {return ViewCube.generate(req, res);});

	 }
}

