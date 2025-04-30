import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyProxyHandler implements InvocationHandler{

	private Object tarObj;
	
	public MyProxyHandler(Object tarObj) {
		this.tarObj = tarObj;	
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("支付装备...");
		
		Object result = method.invoke(tarObj, args);
				
		System.out.println("支付完成");
				
		return result;
	}

	
}
