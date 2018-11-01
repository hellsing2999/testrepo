package fr.m2iformation.applijee.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest")// partie milieu des url apres le http://localhost:8080/aplliJEE-web
// et avant les valeurs de @Path() des classes java
public class RestApplicationConfig extends Application {
	
	

}
