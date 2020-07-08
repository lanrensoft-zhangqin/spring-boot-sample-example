package springbootexample.zhangqin.soft.component;

import org.springframework.stereotype.Component;
import springbootexample.zhangqin.soft.vo.RunTimeJvmInfoVO;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class JVMResource{


    private NumberFormat fmtI = new DecimalFormat("###,###", new DecimalFormatSymbols(Locale.ENGLISH));
    private NumberFormat fmtD = new DecimalFormat("###,##0.000", new DecimalFormatSymbols(Locale.ENGLISH));

    private final int Kb = 1024;

    public List<RunTimeJvmInfoVO> getJvms() {
        List<RunTimeJvmInfoVO> list = new ArrayList<>();
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        OperatingSystemMXBean os = ManagementFactory.getOperatingSystemMXBean();
        ThreadMXBean threads = ManagementFactory.getThreadMXBean();
        MemoryMXBean mem = ManagementFactory.getMemoryMXBean();
        ClassLoadingMXBean cl = ManagementFactory.getClassLoadingMXBean();
        list.add(new RunTimeJvmInfoVO("jvmName",runtime.getVmName()));
        list.add(new RunTimeJvmInfoVO("version",runtime.getVmVersion()));
        list.add(new RunTimeJvmInfoVO("jvmVendor",runtime.getVmVendor()));
        list.add(new RunTimeJvmInfoVO("jvmUptime",toDuration(runtime.getUptime())));
        list.add(new RunTimeJvmInfoVO("threadsLive",threads.getThreadCount()+""));
        list.add(new RunTimeJvmInfoVO("threadsDaemon",threads.getDaemonThreadCount()+""));
        list.add(new RunTimeJvmInfoVO("threadsPeak",threads.getPeakThreadCount()+""));
        list.add(new RunTimeJvmInfoVO("threadsTotal",threads.getTotalStartedThreadCount()+""));

        list.add(new RunTimeJvmInfoVO("heapCurr", mem.getHeapMemoryUsage().getUsed() / Kb+""));
        list.add(new RunTimeJvmInfoVO("heapMax",mem.getHeapMemoryUsage().getMax() / Kb+""));
        list.add(new RunTimeJvmInfoVO("heapCommitted",mem.getHeapMemoryUsage().getCommitted() / Kb+""));


        list.add(new RunTimeJvmInfoVO("osName", os.getArch()+""));
        list.add(new RunTimeJvmInfoVO("osArch",os.getAvailableProcessors()+""));
        list.add(new RunTimeJvmInfoVO("osCores",os.getAvailableProcessors()+""));

        list.add(new RunTimeJvmInfoVO("clsCurrLoaded", cl.getLoadedClassCount()+""));
        list.add(new RunTimeJvmInfoVO("clsLoaded",cl.getTotalLoadedClassCount()+""));
        list.add(new RunTimeJvmInfoVO("clsUnloaded",cl.getUnloadedClassCount()+""));

        return list;
    }

    protected String printSizeInKb(double size) {
        return fmtI.format((long) (size / 1024)) + " kbytes";
    }

    protected String toDuration(double uptime) {
        uptime /= 1000;
        if (uptime < 60) {
            return fmtD.format(uptime) + " seconds";
        }
        uptime /= 60;
        if (uptime < 60) {
            long minutes = (long) uptime;
            String s = fmtI.format(minutes) + (minutes > 1 ? " minutes" : " minute");
            return s;
        }
        uptime /= 60;
        if (uptime < 24) {
            long hours = (long) uptime;
            long minutes = (long) ((uptime - hours) * 60);
            String s = fmtI.format(hours) + (hours > 1 ? " hours" : " hour");
            if (minutes != 0) {
                s += " " + fmtI.format(minutes) + (minutes > 1 ? " minutes" : " minute");
            }
            return s;
        }
        uptime /= 24;
        long days = (long) uptime;
        long hours = (long) ((uptime - days) * 24);
        String s = fmtI.format(days) + (days > 1 ? " days" : " day");
        if (hours != 0) {
            s += " " + fmtI.format(hours) + (hours > 1 ? " hours" : " hour");
        }
        return s;
    }

}