package btrace.function;

import com.sun.btrace.AnyType;
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.Return;

/**
 * 拦截返回值
 *
 * @author mason
 * @since 2019/8/2
 */
@BTrace
public class PrintReturn {

    @OnMethod(clazz = "me.mason.monitor.controller.UserController"
            ,method = "getUser"
            ,location = @Location(Kind.RETURN))
    public static void printReturnData(@Return AnyType result){
        // 打印时间
        BTraceUtils.println(BTraceUtils.Time.timestamp("yyyy-MM-dd HH:mm:ss"));
        BTraceUtils.printFields(result);
        BTraceUtils.println("==========================");
    }


    @OnMethod(clazz = "me.mason.monitor.service.UserService"
            ,method = "getUsers"
            ,location = @Location(Kind.RETURN))
    public static void printReturnData1(@Return AnyType result){
        // 打印时间
        BTraceUtils.println(BTraceUtils.Time.timestamp("yyyy-MM-dd HH:mm:ss"));
        BTraceUtils.printFields(result);
        BTraceUtils.println("==========================");
        BTraceUtils.println(BTraceUtils.str(result));
        BTraceUtils.println("==========================");
    }
}
