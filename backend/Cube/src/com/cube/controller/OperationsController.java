package com.cube.controller;

import java.util.ArrayList;
import java.util.List;

import com.cube.entity.Cube;
import com.cube.home.OperationsHome;

import test.cuboTest;

/**
 * 
 * @author John Anderson Garcia Vanegas
 * @Responsabilidad: Determinar como se genera el cubo:
 * 
 *  -Mediante pruebas unitarias o integración
 *  -El usuario de forma manual ingresando información
 *  -La cantidad de veces a ejecutar la generación del cubo
 */

public class OperationsController {
	
	public static List<List<String>> generate(Cube.Request req){

		OperationsHome dao = null;
		List<List<String>>listreponse  = new ArrayList<List<String>>();

		if(req.getTest().equals("1")) {  // execute test unit and integration 
			 OperationsHome operations = new OperationsHome(4);	
			 cuboTest test = new cuboTest();
					 
			System.out.println("pruebas unitarias ");   // pruebas unitarias			
			listreponse.add(new ArrayList<String>());
			listreponse.get(0).add(test.updateTest(2, 2, 2, 4, operations)+" " +test.sumaTest(1, 1, 1, 3, 3, 3, operations, 4));
			//listreponse.get(0).add(test.sumaTest(1, 1, 1, 3, 3, 3, operations, 4));
			//test.updateTest(2, 2, 2, 4, operations)
			//test.sumaTest(1, 1, 1, 3, 3, 3, operations, 4);  
			
			System.out.println("pruebas integración ");     // pruebas integracion
			listreponse.add(new ArrayList<String>());
			listreponse.get(1).add(test.updateTest(2, 2, 2, 1, operations) + "  " 
								+test.sumaTest(1, 1, 1, 1, 1, 1, operations, 0)
								+test.sumaTest(1, 1, 1, 2, 2, 2, operations, 1)
								+test.sumaTest(2, 2, 2, 2, 2, 2, operations, 1));
			
			
		}else {
			for (int i = 0; i < req.getCant_test(); i++) {
				List<Cube.Data> listData= req.getDatos();
				String[] dataParts = listData.get(i).getConfiguration().trim().split(" ");
				int dimensions = Integer.valueOf(dataParts[0]);
				int numOperations = Integer.valueOf(dataParts[1]);
				dao = new OperationsHome(dimensions); 
				listreponse.add(new ArrayList<String>());

				for (int j = 0; j < numOperations; j++) {
					String consult = listData.get(i).getConsulta().get(j).toUpperCase();
					String[] consultParts = consult.split(" ");
					
					if (consultParts[0].equals("UPDATE")) {
						dao.update(Integer.valueOf(consultParts[1])-1, Integer.valueOf(consultParts[2])-1, Integer.valueOf(consultParts[3])-1, Integer.valueOf(consultParts[4]));
					}
					if (consultParts[0].equals("QUERY")) {
						listreponse.get(i).add(String.valueOf(dao.query(Integer.valueOf(consultParts[1])-1, Integer.valueOf(consultParts[2])-1,Integer.valueOf(consultParts[3])-1, Integer.valueOf(consultParts[4])-1, Integer.valueOf(consultParts[5])-1, Integer.valueOf(consultParts[6])-1)));
					}
				}
			}
		}
		
		return listreponse;
	}

}
