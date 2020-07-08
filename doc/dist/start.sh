#chmod u+x *.sh
#export JAVA_HOME=你的javahome
#!/bin/bash
#这里可替换为你自己的执行程序,其他代码无需更改
APP_HOME=./
APP_NAME=$APP_HOME/zhangqin.soft-0.0.1-SNAPSHOT.jar
JVM="-server -Xms256m -Xmx512m -XX:PermSize=64M -XX:MaxNewSize=128m -XX:MaxPermSize=128m -Djava.awt.headless=true -XX:+CMSClassUnloadingEnabled -XX:+CMSPermGenSweepingEnabled"
#使用说明,用来提示输入参数
usage() {
echo "Usage: sh 执行脚本.sh [start|stop|restart|status]"
exit 1
}
#检查程序是否在运行
is_exist(){
pid=`ps -ef|grep $APP_NAME|grep -v grep|awk '{print $2}' `
#如果不存在返回1,存在返回0
if [ -z "${pid}" ]; then
return 1
else
return 0
fi
}
#启动方法
start(){
is_exist
if [ $? -eq "0" ]; then
echo "${APP_NAME} is already running. pid=${pid} ."
else
#参考的设置方法 设置 JVM 还有配置文件路径
nohup ${JAVA_HOME}/bin/java -jar $APP_NAME  >$APP_HOME/log/nohup 2>&1 &
echo "服务启动成功！"
fi
}
#停止方法
stop(){
is_exist
if [ $? -eq "0" ]; then
kill -9 $pid
else
echo "${APP_NAME} is not running"
fi
}
#输出运行状态
status(){
is_exist
if [ $? -eq "0" ]; then
echo "${APP_NAME} is running. Pid is ${pid}"
else
echo "${APP_NAME} is NOT running."
fi
}
#重启
restart(){
stop
start
}
#根据输入参数,选择执行对应方法,不输入则执行使用说明
case "$1" in
"start")
start
;;
"stop")
stop
;;
"status")
status
;;
"restart")
restart
;;
*)
usage
;;
esac
