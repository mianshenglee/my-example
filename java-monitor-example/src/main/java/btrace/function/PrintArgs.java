package btrace.function;

import com.sun.btrace.AnyType;
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.ProbeClassName;
import com.sun.btrace.annotations.ProbeMethodName;

import java.lang.reflect.Field;

/**
 * 拦截方法参数
 *
 * @author mason
 * @since 2019/8/2
 */
@BTrace
public class PrintArgs {
    /**
     * 输出多个参数
     * 普通方法：@OnMethod(clazz = "",method = "")
     * 同名函数，使用参数区分
     * 拦截时机：location = @Location(Kind)
     *          Kind.ENTRY，入口（默认值），Kind.RETURN 返回时
     *          Kind.THROW，异常，Kind.LINE 行数
     * @param className
     * @param methodName
     * @param args
     */
    @OnMethod(clazz = "me.mason.monitor.controller.UserController"
            ,method = "getUsers",location = @Location(Kind.ENTRY))
    public static void readFunction(@ProbeClassName String className, @ProbeMethodName String methodName
            , AnyType[] args) {
        // 打印时间
        BTraceUtils.println(BTraceUtils.Time.timestamp("yyyy-MM-dd HH:mm:ss"));
        BTraceUtils.println("method controller");
        BTraceUtils.printArray(args);
        BTraceUtils.println(className + "," + methodName);
        BTraceUtils.println("==========================");
    }

    /**
     * 打印单个参数
     * @param arg
     */
    @OnMethod(clazz = "me.mason.monitor.service.UserService",
            method = "getUsers",
            location = @Location(Kind.ENTRY))
    public static void readGetUserService(int arg){
        // 打印时间
        BTraceUtils.println(BTraceUtils.Time.timestamp("yyyy-MM-dd HH:mm:ss"));
        BTraceUtils.println("method service ");
        //简单类型使用print或println
        BTraceUtils.println(arg);
        BTraceUtils.println("==========================");
    }

    /**
     * 构造函数：@OnMethod(clazz = "",method = "<init>")
     * @param args
     */
    @OnMethod(clazz = "me.mason.monitor.entity.User",method = "<init>")
    public static void readConstruct(AnyType[]args){
        // 打印时间
        BTraceUtils.println(BTraceUtils.Time.timestamp("yyyy-MM-dd HH:mm:ss"));
        BTraceUtils.println("construct:");
        BTraceUtils.printArray(args);
        BTraceUtils.println("==========================");
    }

    /**
     * 正则式匹配
     * @param className
     * @param methodName
     * @param args
     */
    @OnMethod(clazz = "me.mason.monitor.service.UserService",
            method = "/.*/",
            location = @Location(Kind.ENTRY))
    public static void printAllMethod(@ProbeClassName String className,@ProbeMethodName String methodName, AnyType[] args){
        // 打印时间
        BTraceUtils.println(BTraceUtils.Time.timestamp("yyyy-MM-dd HH:mm:ss"));
        BTraceUtils.println("regex print");
        BTraceUtils.println(className + ","+ methodName);
        BTraceUtils.printArray(args);
        BTraceUtils.println("==========================");
    }

}
