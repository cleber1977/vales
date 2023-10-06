package Controle;

import Persistencia.CargoDAO;
import Persistencia.EmpresaDAO;
import Persistencia.FuncionarioDAO;
import Persistencia.HistoricoDAO;
import Persistencia.UsuarioDAO;

public class ListagemCrtl {
	public void lisCargo(){
		CargoDAO DAO = new CargoDAO();
		DAO.lisCargo();
	}
	
	public void lisEmpresa(){
		EmpresaDAO DAO = new EmpresaDAO();
		DAO.lisEmpresa();
	}
	
	public void lisFuncionario(){
		FuncionarioDAO  DAO = new FuncionarioDAO();
		DAO.lisFuncionario();
	}

	public void listHistorico(){
		HistoricoDAO DAO = new HistoricoDAO();
		DAO.lisHistorico();
	}
	
	public void listUsuario(){
		UsuarioDAO DAO = new UsuarioDAO();
		DAO.lisUsuario();
	}
}
