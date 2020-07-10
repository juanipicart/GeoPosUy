package com.interfaz;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.constantes.Constantes;

public final class EJBLocator {

    private static EJBLocator instance;

    private Context initialContext;

    public static synchronized EJBLocator getInstance() {
        if (instance == null) {
            instance = new EJBLocator();
        }
        return instance;
    }

    private EJBLocator() {
    }

    private Context getInitialContext() throws NamingException {
        if (initialContext == null) {
        	initialContext = new InitialContext();
        }
        return initialContext;
    }
    
    /**
     * Obtiene el JNDI para el bean especificado en el modulo core_negocios
     */
    public <T> String getJNDIName(final Class<T> ejbClass) {
        return getJNDIName(ejbClass, Constantes.MODULO_EJB);
    }
    
    /**
     * Obtiene el JNDI para el bean especificado en el modulo especifico.
     */
    public <T> String getJNDIName(final Class<T> ejbClass, final String modulo) {
        String jndiName = "java:" + modulo + "/";

        if (ejbClass.getSimpleName().endsWith("Local")) {
            jndiName += ejbClass.getSimpleName().substring(0, ejbClass.getSimpleName().lastIndexOf("Local"));
        } else if (ejbClass.getSimpleName().endsWith("Remote")) {
            jndiName += ejbClass.getSimpleName().substring(0, ejbClass.getSimpleName().lastIndexOf("Remote"));
        }

        jndiName += "!" + ejbClass.getCanonicalName();
        return jndiName;
    }

    /**
     * Obtiene el JNDI para el bean especificado en el modulo especifico.
     */
    public String getJNDIName(final String ejbName, final String ejbClassCanonico, final String modulo) {
        return "java:" + modulo + "/" + ejbName + "!" + ejbClassCanonico;
    }

    /**
     * Devuelve la referencia al EJB que implementa la interfaz remota o local que se le pasa.
     * Para que funcione con interfaz local esta debe terminar en Local.
     */
    public <T> T lookup(final Class<T> ejbClass) throws NamingException {
        return (T) getInitialContext().lookup(getJNDIName(ejbClass));
    }

    /**
     * Devuelve la referencia al EJB que implementa la interfaz remota o local que se le pasa en un modulo particular.
     * Para que funcione con interfaz local esta debe terminar en Local.
     */
    public <T> T lookup(final Class<T> ejbClass, final String modulo) throws NamingException {
        return (T) getInitialContext().lookup(getJNDIName(ejbClass, modulo));
    }
}