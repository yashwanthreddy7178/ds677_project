package gpw.webservice;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.jboss.logging.Logger;

import gpw.ejb.SincronizadorStatelessLocal;
import gpw.ejblookup.LookUps;
import gpw.webservice.security.Authenticator;
import gpw.webservice.util.CnstWs;
import gpw.ws.datatypes.errors.ErrorServicio;
import gpw.ws.datatypes.errors.ErroresServicioCod;
import gpw.ws.datatypes.pedido.ParamObtPedidosNoSinc;
import gpw.ws.datatypes.pedido.ParamRecPedidosASinc;
import gpw.ws.datatypes.pedido.ResultObtPedidosNoSinc;
import gpw.ws.datatypes.pedido.ResultRecPedidosASinc;
import gpw.ws.datatypes.persona.ParamObtPersonasNoSinc;
import gpw.ws.datatypes.persona.ParamRecPersonasASinc;
import gpw.ws.datatypes.persona.ResultObtPersonasNoSinc;
import gpw.ws.datatypes.persona.ResultRecPersonasASinc;
import gpw.ws.datatypes.producto.ParamRecProductosASinc;
import gpw.ws.datatypes.producto.ResultRecProductosASinc;
import gpw.ws.datatypes.wsfunc.ResultServFuncional;

@WebService(targetNamespace = CnstWs.TNS, portName = CnstWs.PORTNAME, serviceName = CnstWs.WSNAME)
public class WsGestPed {

	private static Logger logger = Logger.getLogger(WsGestPed.class);
	@Resource
	WebServiceContext wsCtx;
	MessageContext msgCtx;

	/*******************************************************************************************/
	
	@WebMethod(operationName = "servFuncional", action = "servFuncional", exclude = false)
	@WebResult(name = "resultServFuncional")
	public ResultServFuncional servFuncional() {
		logger.info("<<< # WS # >>> Inicia operacion servicioFuncional...");
		ResultServFuncional result = new ResultServFuncional();
		msgCtx = wsCtx.getMessageContext();
		if(Authenticator.authenticateWsCAll(msgCtx)) {
			SincronizadorStatelessLocal sincSl = LookUps.lookUpEjb();
			result = sincSl.servicioFuncional();
		} else {
			result.setError(new ErrorServicio(ErroresServicioCod.CODERR_WSAUTH, ErroresServicioCod.WSERR_AUTH));
			logger.warn("WARNING WsGestPed > servicioFuncional: Se registra un error al autenticar las credenciales");
		}
		logger.info("<<< # WS # >>> Finaliza operacion servicioFuncional...");
		return result;
	}
	
	@WebMethod(operationName = "obtenerPersonasNoSinc", action = "obtenerPersonasNoSinc", exclude = false)
	@WebResult(name = "resultObtPersonasNoSinc")
	public ResultObtPersonasNoSinc obtenerPersonasNoSinc(@WebParam(name = "paramObtPersonasNoSinc") ParamObtPersonasNoSinc paramObtPersonasNoSinc) {
		logger.info("<<< # WS # >>> Inicia operacion obtenerPersonasNoSinc...");
		ResultObtPersonasNoSinc result = new ResultObtPersonasNoSinc();
		msgCtx = wsCtx.getMessageContext();
		if(Authenticator.authenticateWsCAll(msgCtx)) {
			SincronizadorStatelessLocal sincSl = LookUps.lookUpEjb();
			result = sincSl.obtPersonasNoSinc(paramObtPersonasNoSinc);
		} else {
			ErrorServicio errorServ = new ErrorServicio(ErroresServicioCod.CODERR_WSAUTH, ErroresServicioCod.WSERR_AUTH);
			result.getErroresServ().add(errorServ);
			logger.warn("WARNING WsGestPed > obtenerPersonasNoSinc: Se registra un error al autenticar las credenciales");
		}

		logger.info("<<< # WS # >>> Finaliza operacion obtenerPersonasNoSinc...");
		return result;
	}
	
	@WebMethod(operationName = "recibirPersonasASinc", action = "recibirPersonasASinc", exclude = false)
	@WebResult(name = "resultRecPersonasASinc")
	public ResultRecPersonasASinc recibirPersonasASinc(@WebParam(name = "paramRecPersonasASinc") ParamRecPersonasASinc paramRecPersonasASinc) {
		logger.info("<<< # WS # >>> Inicia operacion recibirPersonasASinc...");
		ResultRecPersonasASinc result = new ResultRecPersonasASinc();
		msgCtx = wsCtx.getMessageContext();
		if(Authenticator.authenticateWsCAll(msgCtx)) {
			SincronizadorStatelessLocal sincSl = LookUps.lookUpEjb();
			result = sincSl.recPersonasASinc(paramRecPersonasASinc);
		} else {
			ErrorServicio errorServ = new ErrorServicio(ErroresServicioCod.CODERR_WSAUTH, ErroresServicioCod.WSERR_AUTH);
			result.getErroresServ().add(errorServ);
			logger.warn("WARNING WsGestPed > recibirPersonasASinc: Se registra un error al autenticar las credenciales");
		}

		logger.info("<<< # WS # >>> Finaliza operacion recibirPersonasASinc...");
		return result;
	}
	
	@WebMethod(operationName = "recibirProductosASinc", action = "recibirProductosASinc", exclude = false)
	@WebResult(name = "resultRecProductosASinc")
	public ResultRecProductosASinc recibirProductosASinc(@WebParam(name = "paramRecProductosASinc") ParamRecProductosASinc paramRecProductosASinc) {
		logger.info("<<< # WS # >>> Inicia operacion recibirProductosASinc...");
		ResultRecProductosASinc result = new ResultRecProductosASinc();
		msgCtx = wsCtx.getMessageContext();
		if(Authenticator.authenticateWsCAll(msgCtx)) {
			SincronizadorStatelessLocal sincSl = LookUps.lookUpEjb();
			result = sincSl.recProductosASinc(paramRecProductosASinc);
		} else {
			ErrorServicio errorServ = new ErrorServicio(ErroresServicioCod.CODERR_WSAUTH, ErroresServicioCod.WSERR_AUTH);
			result.getErroresServ().add(errorServ);
			logger.warn("WARNING WsGestPed > recibirProductosASinc: Se registra un error al autenticar las credenciales");
		}
		logger.info("<<< # WS # >>> Finaliza operacion recibirProductosASinc...");
		return result;
	}
	
	@WebMethod(operationName = "obtenerPedidosNoSinc", action = "obtenerPedidosNoSinc", exclude = false)
	@WebResult(name = "resultObtPedidosNoSinc")
	public ResultObtPedidosNoSinc obtenerPedidosNoSinc(@WebParam(name = "paramObtPedidosNoSinc") ParamObtPedidosNoSinc paramObtPedidosNoSinc) {
		logger.info("<<< # WS # >>> Inicia operacion obtenerPedidosASinc...");
		ResultObtPedidosNoSinc result = new ResultObtPedidosNoSinc();
		msgCtx = wsCtx.getMessageContext();
		if(Authenticator.authenticateWsCAll(msgCtx)) {
			SincronizadorStatelessLocal sincSl = LookUps.lookUpEjb();
			result = sincSl.obtPedidosNoSinc(paramObtPedidosNoSinc);
		} else {
			ErrorServicio errorServ = new ErrorServicio(ErroresServicioCod.CODERR_WSAUTH, ErroresServicioCod.WSERR_AUTH);
			result.getErroresServ().add(errorServ);
			logger.warn("WARNING WsGestPed > obtenerPedidosNoSinc: Se registra un error al autenticar las credenciales");
		}
		logger.info("<<< # WS # >>> Finaliza operacion obtenerPedidosASinc...");
		return result;
	}
	
	@WebMethod(operationName = "recibirPedidosASinc", action = "recibirPedidosASinc", exclude = false)
	@WebResult(name = "resultRecPedidosASinc")
	public ResultRecPedidosASinc recibirPedidosASinc(@WebParam(name = "paramRecPedidosASinc") ParamRecPedidosASinc paramRecPedidosASinc) {
		logger.info("<<< # WS # >>> Inicia operacion recibirPedidosASinc...");
		ResultRecPedidosASinc result = new ResultRecPedidosASinc();
		msgCtx = wsCtx.getMessageContext();
		if(Authenticator.authenticateWsCAll(msgCtx)) {
			SincronizadorStatelessLocal sincSl = LookUps.lookUpEjb();
			result = sincSl.recPedidosASinc(paramRecPedidosASinc);
		} else {
			ErrorServicio errorServ = new ErrorServicio(ErroresServicioCod.CODERR_WSAUTH, ErroresServicioCod.WSERR_AUTH);
			result.getErroresServ().add(errorServ);
			logger.warn("WARNING WsGestPed > recibirPedidosASinc: Se registra un error al autenticar las credenciales");
		}
		logger.info("<<< # WS # >>> Finaliza operacion recibirPedidosASinc...");
		return result;
	}
	
}
