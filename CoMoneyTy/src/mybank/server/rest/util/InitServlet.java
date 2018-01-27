package mybank.server.rest.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import mybank.server.beans.GenerateurTest;
import mybank.server.beans.Operation;

/**
 * Servlet implementation class InitServlet
 */
@WebServlet("/InitServlet")
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static ArrayList<Operation> listeOperationReference = null;

	public void init(ServletConfig config) throws ServletException {
		// Démarrage de la servlet
		Logger.getLogger("Init").info("INIT DES CONNEXIONS COUCHBASE");
		AccesseurGenerique.getInstance().init(); 

		Logger.getLogger("Init").info("CHARGEMENT DES OPERATIONS FICTIVES");
		listeOperationReference = new ArrayList<Operation>();

		try {
			BufferedReader myBuff = new BufferedReader(
					new FileReader(new GenerateurTest().getClass().getResource("listeOperationTest.txt").getFile()));

			String str = myBuff.readLine();
			while (str != null) {
				Operation ope = new Operation();
				String[] tab = str.split(";");
				ope.setDate(Utilitaire.FORMAT_DATE_STANDARD.parse(tab[0]));
				ope.setDescription(tab[1]);
				ope.setMontant(Double.parseDouble(tab[2].replaceAll(",", ".")));
				listeOperationReference.add(ope);
				str = myBuff.readLine();
			}

			myBuff.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

	public void destroy() {
		// Save dans un fichier
		try {
			AccesseurGenerique.getInstance().sauvegarde();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InitServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
