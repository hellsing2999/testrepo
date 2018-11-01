package fr.m2iformation.applijee.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.m2iformation.applijee.entity.Compte;
import fr.m2iformation.applijee.entity.Operation;
import fr.m2iformation.applijee.service.IServiceCompte;

/**
 * Servlet implementation class CompteServlet
 */

@WebServlet(name="CompteServlet",urlPatterns="/CompteServlet")
public class CompteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB // Pour initialiser serviceCompte en refrencant un EJB existant
	//compatible avec IServiceCompte
	private IServiceCompte serviceCompte;
   
    public CompteServlet() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String task = request.getParameter("task");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		
		if("virement".equals(task)) {
			long numCptDeb = Long.parseLong(request.getParameter("numCptDeb"));
			long numCptCred = Long.parseLong(request.getParameter("numCptCred"));
			double montant = Double.parseDouble(request.getParameter("montant"));
			try {
				serviceCompte.transferer(montant, numCptDeb, numCptCred);
				out.println("Virement bien effectué. <br/>");
			} catch (Exception e) {
				out.println("Echec Virement. <br/>");
			}
			
		}else if("rechercherCompte".equals(task)) {
			String numeroCompteAsString = request.getParameter("numeroCompte");
			Long numeroCompte = Long.parseLong(numeroCompteAsString);
			Compte compte = serviceCompte.rechercherCompteParNumero(numeroCompte);			
			out.println("label = "+compte.getLabel()+"<br/>");
			out.println("solde = "+compte.getSolde()+"<br/>");
		}else if("testerCompte".equals(task)) {
			this.testerCompte(out);
		}
		
		out.println("</body></html>");
	}
	
	private void testerCompte(PrintWriter out) {
		out.println("tester Compte / CRUD <br/>"); //aujourd'hui via servlet utilisant EJB d'autre fois dans test JUnit utilisant Service Spring
		//Astuce : pour tester Service et DAO, la sequence suivante 
		//Ajout en base d'une nouvelle entité 
		Compte nouveauCompte = new Compte(null,"nouveau compte",200.0);
		this.serviceCompte.ajouterCompte(nouveauCompte);
		Long numCpt = nouveauCompte.getNumero();
		out.println("Numero du compte ajouté : " + numCpt +"<br/>");
		//relecture pour verifier
		Compte compteRelu = this.serviceCompte.rechercherCompteParNumero(numCpt);
		out.println("Compte ajouté et relu depuis DB : " + compteRelu +"<br/>");
		//Modif en mémoire et en base 
		nouveauCompte.setSolde(400.0);
		this.serviceCompte.mettreAjourCompte(nouveauCompte);
		//relecture pour verifier la mise à jour
		compteRelu = this.serviceCompte.rechercherCompteParNumero(numCpt);
		out.println("Compte ajouté et relu depuis DB : " + compteRelu +"<br/>");
		//Suppression en base et verification
		this.serviceCompte.supprimerCompte(numCpt);
		compteRelu = this.serviceCompte.rechercherCompteParNumero(numCpt);
		if(compteRelu == null)
		out.println("Compte supprimé." + "<br/>");
		
		Compte cpt1 = this.serviceCompte.rechercherCompteAvecOperationsParNumero(1L);
		for (Operation op : cpt1.getOperations()) {
			out.println("operation du compte 1:" + op + "<br/>");
		}
		
		List<Compte> comptes = this.serviceCompte.rechercherCompteDeClient(2L);
		for (Compte compte : comptes) {
			out.println("Compte du client 2 :"+compte+"<br/>");
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doGet(request, response);
	}

}
