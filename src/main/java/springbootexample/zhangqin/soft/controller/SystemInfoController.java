package springbootexample.zhangqin.soft.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springbootexample.zhangqin.soft.aop.ControllerLogAspect;
import springbootexample.zhangqin.soft.service.ISysInfoService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
public class SystemInfoController {

    private static final Logger LOG = LoggerFactory.getLogger(SystemInfoController.class);
    @Resource
    private ISysInfoService sysInfoService;

    @GetMapping("/info")
    public String infoPage(ModelMap modelMap, HttpServletRequest request){

        modelMap.put("qps", sysInfoService.getQpsInfo());
        modelMap.put("headers",sysInfoService.getReqHeader(request));
        modelMap.put("jvms",sysInfoService.getJvmInfo());
        modelMap.put("systemInfos",sysInfoService.getSystemInfo());

        return "info";
    }

    @GetMapping("/test/error")
    public @ResponseBody
     String error(){

        LOG.error("测试");

        return "testErrorLog";
    }
}
