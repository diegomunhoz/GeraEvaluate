package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class GeraEvaluate {

	private Scanner leitorEnvio;
	private Scanner leitorRetorno;
	private PrintStream gravador;
	private List<String> varWrkECCC = new ArrayList<String>();
	private List<String> varWrkDescricao = new ArrayList<String>();
	private String campoECCC;
	private String campoDescricao;
	private String linha1;
	private String linha2;
	private String linha3;
	private String aux;
	private int b;
	private boolean envio = false;
	private boolean retorno = false;

	public void processar() {
		try {
			this.leitorEnvio = new Scanner(new FileReader("envio.txt"));
			this.leitorRetorno = new Scanner(new FileReader("retorno.txt"));

			while (this.leitorEnvio.hasNext()) {
				this.montarListaVariaveisECCC();
			}

			while (this.leitorRetorno.hasNext()) {
				this.montarListaVariaveisDescricao();
			}

			if (this.varWrkECCC.size() > 0) {
				this.gravador = new PrintStream("saida.txt");
				this.envio = true;
				this.montaWorkingECCC();
			} else {
				this.envio = false;
			}

			if (this.envio == false && this.retorno == false) {
				JOptionPane.showMessageDialog(null,
						"Informar ENVIO ou RETORNO - BOOK NÃO gerado!");
			} else {
				this.gravador.close();
				JOptionPane.showMessageDialog(null, "Processamento Concluído.");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	private void montaWorkingECCC() {

		for (String a : this.varWrkECCC) {

			this.linha1 = "               WHEN '" + a.toUpperCase()
					+ "                           '";
			this.gravador.println(this.linha1);

			this.aux = "" + this.varWrkDescricao.get(this.b);
			this.linha2 = "                   MOVE '" + this.aux.toUpperCase()
					+ "'";
			this.gravador.println(this.linha2);

			this.linha3 = "						                TO WCDE-MENSAGEM";
			this.gravador.println(this.linha3);

			this.b++;
		}
	}

	private void montarListaVariaveisECCC() {
		try {
			this.campoECCC = (String) this.leitorEnvio.nextLine();
			this.varWrkECCC.add(this.campoECCC.trim());
		} catch (Exception e) {
			JOptionPane
					.showMessageDialog(null, "Erro ao montar lista de ECCC.");
		}
	}

	private void montarListaVariaveisDescricao() {
		try {
			this.campoDescricao = (String) this.leitorRetorno.nextLine();
			this.varWrkDescricao.add(this.campoDescricao);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao montar lista de Descricao.");
		}
	}

}