package controller;

import lombok.SneakyThrows;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/FatherServlet")
public class FatherServlet extends HttpServlet {
    @SneakyThrows
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
//       获得请求标记的方法名
        String method = req.getParameter("method");

        //        装的数据类型谁继承FatherServlet就装谁,获得子Servlet类的反射对象
        Class<? extends FatherServlet> aClass = this.getClass();
        //        获得请求的方法的反射对象,第一个方法名,第二个是那个方法带的形参的数据类型,第三个那个方法带的形参的数据类型,
        Method declaredMethod = aClass.getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);

        //        设置方法的访问权限
        declaredMethod.setAccessible(true);
//        用方法的反射对象调用方法自身,接收方法的返回值
        String invoke = (String) declaredMethod.invoke(this, req, resp);
        if (invoke.startsWith("forward:")) {
            String substring = invoke.substring(invoke.indexOf(":") + 1);
            req.getRequestDispatcher(substring).forward(req, resp);
        } else if (invoke.startsWith("redirect:")) {
            String substring = invoke.substring(invoke.indexOf(":") + 1);
            req.getRequestDispatcher(substring).forward(req, resp);
        }
    }

    public <T> T requestMap(Class<T> clazz, HttpServletRequest request) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
//        接收请求中所有数据
        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println(parameterMap);

//        为了方便给属性设值,将参数Map(parameterMap)中每个value转换为String类型
        Map<String, String> hashMap = new HashMap<>();
//        边遍历参数集合边将原键名和转换为字符串的值存入新的集合
//        key指代的时hashMap中的键名
        for (String key : parameterMap.keySet()) {
            if (!key.equals("method")) {
                String value = Arrays.toString(parameterMap.get(key));
//                去掉"[]"
                 value = value.substring(1, value.length() - 1);
                hashMap.put(key, value);
            }
            System.out.println(key);
        }

//            获得要接收值实体对象,实例,反射的实例对象
        T t = clazz.getDeclaredConstructor().newInstance();
//            将值设置到实体类的属性中
        for (String hashMapValue : hashMap.keySet()) {
//                获得属性的反射对象
            //                反射的属性Field(字段,成员变量)对象
            Field fieldObject = clazz.getDeclaredField(hashMapValue);
//                设置属性的反射对象的访问权限
            fieldObject.setAccessible(true);
//                获得属性的数据类型
            String dataType = fieldObject.getGenericType().toString();
//                根据属性的数据类型,将value进行判断
            if ("class java.lang.String".equals(dataType)) {
//                    用属性的反射对象给属性设值
                fieldObject.set(t, hashMap.get(hashMapValue));
            } else if ("int".equals(dataType)) {
                fieldObject.set(t, Integer.parseInt(hashMap.get(hashMapValue)));//get(CurrentValue)获得当前集合中的键值
            } else if ("float".equals(dataType)) {
                fieldObject.set(t, Float.parseFloat(hashMap.get(hashMapValue)));
            }
        }
        return t;
    }
}