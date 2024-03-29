package PART4;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 *
 */
public class Log  implements InvocationHandler {
	
	private Object target = null;
	
	/**
	 * Set the target as a local variable using the constructor
	 * @param target is the object that we want to wrap 
	 */
    private Log(Object target) {
        this.target = target;
    }
	
    /**
     * Wraps the object with the dynamic proxy
     * @param target any java object
     * @return the object with the wrapper/proxy
     */
    @SuppressWarnings("rawtypes")
	public static Object newInstance(Object target){
		Class targetClass = target.getClass();
        Class interfaces[] = targetClass.getInterfaces();
        return Proxy. newProxyInstance(targetClass.getClassLoader(),
                interfaces, new Log(target));
    }
	

	@SuppressWarnings("finally")
	@Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        Object invocationResult = null;
        try
        {
            System.out.println("Invoking method " + method.getName());
            invocationResult = method.invoke(this.target, args);
        }
        catch(InvocationTargetException ite)
        {
             throw ite.getTargetException();
        }
        catch(Exception e)
        {
            System.err.println("Invocation of " + method.getName() + " failed");
        }
        finally{
            return invocationResult;
        }
    }

}
