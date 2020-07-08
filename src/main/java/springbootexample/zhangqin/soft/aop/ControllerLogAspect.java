package springbootexample.zhangqin.soft.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Currency;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * controller日志
 */
@Aspect
@Component
public class ControllerLogAspect {
    //临时定义到这里
    public static final ConcurrentHashMap<Long,Long> QPS = new ConcurrentHashMap<Long,Long>();

    private Logger LOG = LoggerFactory.getLogger(this.getClass());


    /**
     * 定义切入点
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void pointcut() {
    }


    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        long beginTime = System.currentTimeMillis();
        HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String requestURI = request.getRequestURI();
        try {
            result = point.proceed();
        } catch (Throwable e) {
            LOG.error(e.getMessage());
            throw e;
        }finally {
            cleanQps();
            long endTime = System.currentTimeMillis();
            long time =  endTime- beginTime;
            long qpsKey = endTime / 1000;
            QPS.put(qpsKey,QPS.get(qpsKey)==null?1:QPS.get(qpsKey)+1);
            LOG.info("request:{},耗时:{}毫秒",requestURI,time);
        }
        return result;
    }

    /**
     * 防止对象过大
     */
    public void cleanQps(){
        int size = QPS.size();
        if(size>1000){
            Enumeration<Long> keys = QPS.keys();
            while (keys.hasMoreElements()){
                Long aLong = keys.nextElement();
                long l = System.currentTimeMillis()/1000-1800;
                if(l>aLong){
                    QPS.remove(aLong);
                }
            }
        }
    }
}