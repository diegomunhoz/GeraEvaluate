package teste;

import model.GeraEvaluate;
import util.UtilProjeto;

public class Main {

	public static void main(String[] args) {

		UtilProjeto util = new UtilProjeto();
		util.mudarAparencia();

		GeraEvaluate working = new GeraEvaluate();
		working.processar();
				
	}
}