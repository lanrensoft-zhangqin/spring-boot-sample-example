package springbootexample.zhangqin.soft.service;

import springbootexample.zhangqin.soft.vo.QpsInfoVO;
import springbootexample.zhangqin.soft.vo.ReqHeadInfoVO;
import springbootexample.zhangqin.soft.vo.RunTimeJvmInfoVO;
import springbootexample.zhangqin.soft.vo.SystemInfoVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 系统信息处理类
 */
public interface ISysInfoService {

    /**
     * 获取系统配置信息
     * @return
     */
     List<SystemInfoVO>  getSystemInfo();

    /**
     * 获取系统最近qps
     * @return
     */
    List<QpsInfoVO> getQpsInfo();

    /**
     * 获取系统jvm信息
     * @return
     */
    List<RunTimeJvmInfoVO> getJvmInfo();

    /**
     * 获取请求头信息
     * @return
     */
    List<ReqHeadInfoVO> getReqHeader(HttpServletRequest request);
}
