package de.verschwiegener.lwjgl3;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.openal.AL;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;

public class Sys {

    public static long getTime(){
        return (long) GLFW.glfwGetTime();
    }
    public static long getTimerResolution() {
        return GLFW.glfwGetTimerFrequency();
    }
    public static boolean openURL(String url) {
        // Attempt to use Webstart if we have it available
        try {
            // Lookup the javax.jnlp.BasicService object
            final Class<?> serviceManagerClass = Class.forName("javax.jnlp.ServiceManager");
            Method lookupMethod = AccessController.doPrivileged(new PrivilegedExceptionAction<Method>() {
                public Method run() throws Exception {
                    return serviceManagerClass.getMethod("lookup", String.class);
                }
            });
            Object basicService = lookupMethod.invoke(serviceManagerClass, new Object[] {"javax.jnlp.BasicService"});
            final Class<?> basicServiceClass = Class.forName("javax.jnlp.BasicService");
            Method showDocumentMethod = AccessController.doPrivileged(new PrivilegedExceptionAction<Method>() {
                public Method run() throws Exception {
                    return basicServiceClass.getMethod("showDocument", URL.class);
                }
            });
            try {
                Boolean ret = (Boolean)showDocumentMethod.invoke(basicService, new URL(url));
                return ret;
            } catch (MalformedURLException e) {
                e.printStackTrace(System.err);
                return false;
            }
        } catch (Exception ue) {
            ue.printStackTrace();
            return false;
        }
    }
    public static String getVersion() {
        return "LWJGL3 Port by Juli15 Version 2.0 stable, based on LWJGL" + Version.getVersion();
    }
}
