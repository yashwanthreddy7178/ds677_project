package util.log.interfaces;

import cucumber.api.Scenario;

public interface ILogBdd extends ILog {

	public void createLog(Scenario cenario);

	public void addMensageInfo(String mensagem);
	
	public void addMensageInfoPrint(String mensagem);
	
	public void addMensageSuccessPrint(String string);

	public void addMensageFailPrint(String string);

}
