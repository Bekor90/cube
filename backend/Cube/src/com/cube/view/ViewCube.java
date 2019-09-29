package com.cube.view;

/**
 * 
 * @author John Anderson Garcia Vanegas
 * @Responsabilidad: Mostrar una respuesta a la solicitud del usuario.
 * 
 */

import java.util.List;

import com.cube.controller.OperationsController;
import com.cube.entity.Cube;
import com.cube.utils.JsonUtil;
import com.cube.utils.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

import spark.Request;
import spark.Response;


public class ViewCube {
	
	public static Object generate(Request req, Response res){

		try {

			ObjectMapper mapper = new ObjectMapper();
			Cube.Request input;			
			input = mapper.readValue(req.body(), Cube.Request.class);
	
			if(input != null) {
					
				List<List<String>> reponse = OperationsController.generate(input);
	
				if(reponse!=null && reponse.size() > 0){					
					res.status(200);
					return JsonUtil.dataToJson(new Message(200,"OK", reponse));
				}else {
					res.status(204);
					return JsonUtil.dataToJson(new Message(204,"Not_Content", ""));
				}
			}

			return JsonUtil.dataToJson(new Message(400,"ERROR","Datos incorrectos"));
		} catch (Exception e) {
			e.printStackTrace();		
			res.status(400);
			return JsonUtil.dataToJson(new Message(400,"ERROR",e.getMessage()));
		}
	}

}
