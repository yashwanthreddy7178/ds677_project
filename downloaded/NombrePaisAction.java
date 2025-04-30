package controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.PaisesService;

@WebServlet("/NombrePaisAction")
public class NombrePaisAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
@Inject
private PaisesService servicePais;
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Instrucciones para evitar la cache del navegador
				response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
				response.setHeader("Pragma", "no-cache"); 
				response.setHeader("Expires", "0");
				
				
		List<String> nombresPaises=servicePais.recuperarPaises().keySet().stream().sorted().collect(Collectors.toList());
		
		ServletContext context= request.getServletContext();
		
		context.setAttribute("nombresPaises", nombresPaises);
		
	}

}
