package com.cube.entity;

/**
 * 
 * @author John Anderson Garcia Vanegas
 * @Responsabilidad: Dar un formato estandar de los datos que se necesitan para generar el cubo.
 * 
 */

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cube {
	
	public static class Request{
		
		@JsonProperty(value="cpb")
		private Integer cant_test;
		@JsonProperty(value="tes")
		private String test;
		@JsonProperty(value="dat")
		private List<Data> datos;
		
		public Integer getCant_test() {
			return cant_test;
		}
		public void setCant_test(Integer cant_test) {
			this.cant_test = cant_test;
		}
		public String getTest() {
			return test;
		}
		public void setTest(String test) {
			this.test = test;
		}
		public List<Data> getDatos() {
			return datos;
		}
		public void setDatos(List<Data> datos) {
			this.datos = datos;
		}
	}
	
	public static class Data{
		@JsonProperty(value="conf")
		private String configuration;
		@JsonProperty(value="consul")
		private List<String> consulta; 
						
		public String getConfiguration() {
			return configuration;
		}
		public void setConfiguration(String configuration) {
			this.configuration = configuration;
		}
		public List<String> getConsulta() {
			return consulta;
		}
		public void setConsulta(List<String> consulta) {
			this.consulta = consulta;
		}
	}
}
