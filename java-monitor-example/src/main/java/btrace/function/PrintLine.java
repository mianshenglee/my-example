package btrace.function;

import com.sun.btrace.AnyType;
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.ProbeClassName;
import com.sun.btrace.annotations.ProbeMethodName;

/**
 * 按等号拦截
 *
 * @author mason
 * @since 2019/8/2
 */
@BTrace
public class PrintLine {

    @OnMethod(clazz = "me.mason.monitor.service.UserService"
            ,method = "getUsers"
            ,location = @Location(value = Kind.LINE,line = 39))
    public static void printLineData(@ProbeClassName String className
            , @ProbeMethodName String methodName
            ,int line){
        // 打印时间
        BTraceUtils.println(BTraceUtils.Time.timestamp("yyyy-MM-dd HH:mm:ss"));
        BTraceUtils.println(className + "," + methodName + ","+line);
        BTraceUtils.println("==========================");
    }
}
