package com.kitsune.escobar.util;

import org.bukkit.Bukkit;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ReflectionUtils {

    // Caches
    private static String version;
    private static int major;
    private static int minor;

    private final static Map<String, Class<?>> nmsClasses;
    private final static Map<String, Class<?>> ocbClasses;

    private final static Map<Class<?>, Map<String, Method>> cachedMethods;

    static {
        nmsClasses = new HashMap<>();
        ocbClasses = new HashMap<>();
        cachedMethods = new HashMap<>();
    }

    public static String getVersion() {
        if (version == null) {
            String declaration = Bukkit.getServer().getClass().getPackage().getName();
            version = declaration.substring(declaration.lastIndexOf('.') + 1) + ".";
            String[] pts = version.substring(1).split("_");
            major = Integer.parseInt(pts[0]);
            minor = Integer.parseInt(pts[1]);
        }
        return version;
    }

    public static int getMajor() {
        if(version == null) {
            getVersion();
        }
        return major;
    }

    public static int getMinor() {
        if(version == null) {
            getVersion();
        }
        return minor;
    }

    public static Class<?> getNMSClass(String localPackage) {

        if (nmsClasses.containsKey(localPackage))
            return nmsClasses.get(localPackage);

        String declaration = "net.minecraft.server." + getVersion() + localPackage;
        Class<?> clazz;

        try {
            clazz = Class.forName(declaration);
        } catch (Throwable e) {
            e.printStackTrace();
            return nmsClasses.put(localPackage, null);
        }

        nmsClasses.put(localPackage, clazz);
        return clazz;
    }

    public static Class<?> getOCBClass(String localPackage) {

        if (ocbClasses.containsKey(localPackage))
            return ocbClasses.get(localPackage);

        String declaration = "org.bukkit.craftbukkit." + getVersion() + localPackage;
        Class<?> clazz;

        try {
            clazz = Class.forName(declaration);
        } catch (Throwable e) {
            e.printStackTrace();
            return ocbClasses.put(localPackage, null);
        }

        ocbClasses.put(localPackage, clazz);
        return clazz;
    }

    public static Method getMethod(Class<?> clazz, String methodName, Class<?>... params) {
        if (!cachedMethods.containsKey(clazz))
            cachedMethods.put(clazz, new HashMap<String, Method>());

        Map<String, Method> methods = cachedMethods.get(clazz);

        if (methods.containsKey(methodName))
            return methods.get(methodName);

        try {
            Method method = clazz.getMethod(methodName, params);
            methods.put(methodName, method);
            cachedMethods.put(clazz, methods);
            return method;
        } catch (Throwable e) {
            e.printStackTrace();
            methods.put(methodName, null);
            cachedMethods.put(clazz, methods);
            return null;
        }
    }

}