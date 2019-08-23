package btrace.function;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Duration;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;

/**
 * 输出方法执行时间
 *
 * @author mason
 * @since 2019/8/2
 */
@BTrace
public class PrintDuration {
    @OnMethod(clazz = "me.mason.monitor.controller.UserController"
    ,method = "getUsers"
    ,location = @Location(Kind.RETURN))
    public static void getUsersDuration(@Duration long duration){
        // 打印时间
        BTraceUtils.println(BTraceUtils.Time.timestamp("yyyy-MM-dd HH:mm:ss"));
        BTraceUtils.println("time(ns):" + duration);
        BTraceUtils.println("time(ms):" + BTraceUtils.str(duration / 1000000));
        BTraceUtils.println("time(s):" + BTraceUtils.str(duration / 1000000000));
        BTraceUtils.println("==========================");
    }
}
