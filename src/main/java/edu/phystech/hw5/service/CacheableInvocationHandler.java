package edu.phystech.hw5.service;

import edu.phystech.hw5.annotation.Cacheable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author kzlv4natoly
 */
public class CacheableInvocationHandler implements InvocationHandler {
    // Здесь необходимо реализовать логику по обработке @cacheable и вызову конкретного метода объекта

    private final Object target;
    private final Map<Method, Map<List<Object>, Object>> cache = new HashMap<>();

    public CacheableInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] arguments) throws Throwable {
        if (method.isAnnotationPresent(Cacheable.class))
        {
            List<Object> arg;
            if (arguments == null || arguments.length == 0) {
                arg = null;
            } else {
                // Перевожу массив в список, массивы не подходят для использования в виде ключей в мапе
                arg = Arrays.stream(arguments).toList();
            }

            Map<List<Object>, Object> cacheMethod = cache.computeIfAbsent(method, k -> new HashMap<>());
            if (cacheMethod.containsKey(arg)) {
                return cacheMethod.get(arg);
            }

            Object res = method.invoke(target, arg.toArray());
            cacheMethod.put(arg, res);
            return res;
        }
        return method.invoke(target, arguments);
    }
}
