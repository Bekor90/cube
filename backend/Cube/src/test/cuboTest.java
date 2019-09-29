package test;

import org.junit.Test;

import com.cube.home.OperationsHome;

import junit.framework.TestCase;

public class cuboTest extends TestCase{
	
	 @Test
	 public String updateTest(int x, int y, int z, int value, OperationsHome operations) {	
		 operations.update(x, y, z, value);
		 System.out.println("update "+x+","+y+","+z+","+value);
		 return "update 2, 2, 2, 4 ";
	 }
	
	 @Test
	 public String sumaTest(int x1, int y1, int z1, int x2, int y2, int z2, OperationsHome operations, int espe) {	
		 long resultado = operations.query(x1, y1, z1, x2, y2, z2);
		 String ejecucion ="";
		 System.out.println("query "+x1+","+ y1+","+ z1+","+ x2+","+ y2+","+ z2);
		 ejecucion = "query "+x1+","+ y1+","+ z1+","+ x2+","+ y2+","+ z2;
		 ejecucion += " resultado: "+espe;
		 assertTrue(resultado == espe);
		 System.out.println("valor esperado de la suma: "+ espe);
		 
		 return ejecucion;
	 }

}
