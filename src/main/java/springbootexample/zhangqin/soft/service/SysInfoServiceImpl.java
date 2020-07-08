package springbootexample.zhangqin.soft.service;

import org.springframework.stereotype.Service;
import springbootexample.zhangqin.soft.aop.ControllerLogAspect;
import springbootexample.zhangqin.soft.component.JVMResource;
import springbootexample.zhangqin.soft.vo.QpsInfoVO;
import springbootexample.zhangqin.soft.vo.ReqHeadInfoVO;
import springbootexample.zhangqin.soft.vo.RunTimeJvmInfoVO;
import springbootexample.zhangqin.soft.vo.SystemInfoVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 系统信息处理类
 */
@Service
public class SysInfoServiceImpl implements ISysInfoService{

    @Resource
    private JVMResource jvmResource;
    /**
     * 获取系统配置信息
     * @return
     */
    public List<SystemInfoVO>  getSystemInfo(){
        List<SystemInfoVO> list = new ArrayList<>();
        Enumeration<String> enumeration = (Enumeration<String>) System.getProperties().propertyNames();
        while (enumeration.hasMoreElements()){
            String o = enumeration.nextElement();
            list.add(new SystemInfoVO(o,System.getProperties().getProperty(o)));
        }
        return list;
     }

    /**
     * 获取系统最近qps
     * @return
     */
    public List<QpsInfoVO> getQpsInfo(){
        List<QpsInfoVO> list = new ArrayList<>();
        Set<Map.Entry<Long, Long>> entries = ControllerLogAspect.QPS.entrySet();
        for (Map.Entry<Long, Long> entry : entries) {
            Long key = entry.getKey();
            Long value = entry.getValue();
            list.add(new QpsInfoVO(key,value));
        }
        Collections.sort(list);
        return list;
    }

    /**
     * 获取系统jvm信息
     * @return
     */
    public List<RunTimeJvmInfoVO> getJvmInfo(){

        return jvmResource.getJvms();
    }

    @Override
    public List<ReqHeadInfoVO> getReqHeader(HttpServletRequest request) {
        List<ReqHeadInfoVO> list = new ArrayList<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()){
            String o = enumeration.nextElement();
            list.add(new ReqHeadInfoVO(o,request.getHeader(o)));
        }
        return list;
    }
}
