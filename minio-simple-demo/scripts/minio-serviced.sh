#!/bin/sh
#
#
# chkconfig: 2345 67 92
# description: minio service

#执行程序启动所使用的系统用户
RUNNING_USER=root

###################################
#环境变量及程序执行参数
###################################
MINIO_HOME=/opt/minio
MINIO_DATA_HOME=/opt/min-data
MINIO_LOGFILE=${MINIO_HOME}/minio.log
MINIO_HOST=127.0.0.1
MINIO_PORT=9000
#accesskey and secretkey
ACCESS_KEY=minio 
SECRET_KEY=minio123

#初始化psid变量（全局）
psid=0

checkpid() {
   pidps=`ps -ef|grep ${MINIO_DATA_HOME}|grep -v grep`
 
   if [ -n "${pidps}" ]; then
      psid=`echo ${pidps} | awk '{print $2}'`
   else
      psid=0
   fi
}

###################################
#(函数)启动程序
###################################
start() {
   checkpid
 
   if [ ${psid} -ne 0 ]; then
      echo "================================"
      echo "warn: minio already started! (pid=${psid})"
      echo "================================"
   else
      echo -n "Starting minio, data path: ${MINIO_DATA_HOME} ..."
      START_CMD="MINIO_ACCESS_KEY=${ACCESS_KEY} MINIO_SECRET_KEY=${SECRET_KEY} nohup ${MINIO_HOME}/minio  server --address "${MINIO_HOST}:${MINIO_PORT}" ${MINIO_DATA_HOME}  > ${MINIO_LOGFILE} 2>&1 &"
      
	  su - ${RUNNING_USER} -c "${START_CMD}"
      checkpid
      if [ ${psid} -ne 0 ]; then
         echo "(pid=$psid) [OK]"
      else
         echo "[Failed]"
      fi
   fi
}

###################################
#(函数)停止程序
###################################
stop() {
   checkpid
   
   if [ ${psid} -ne 0 ]; then
      echo -n "Stopping minio ...(pid=${psid}) "
      su - ${RUNNING_USER} -c "kill -9 ${psid}"
      if [ $? -eq 0 ]; then
         echo "[OK]"
      else
         echo "[Failed]"
      fi
 
      checkpid
      if [ ${psid} -ne 0 ]; then
         stop
      fi
   else
      echo "================================"
      echo "warn: minio is not running"
      echo "================================"
   fi
}

###################################
#(函数)检查程序运行状态
###################################
status() {
   checkpid
 
   if [ ${psid} -ne 0 ];  then
      echo "minio is running! (pid=${psid})"
   else
      echo "minio is not running"
   fi
}

###################################
#读取脚本的第一个参数($1)，进行判断
#参数取值范围：{start|stop|restart|status}
#如参数不在指定范围之内，则打印帮助信息
###################################
case "$1" in
   'start')
      start
      ;;
   'stop')
     stop
     ;;
   'restart')
     stop
     start
     ;;
   'status')
     status
     ;;
  *)
echo "Usage: $0 {start|stop|restart|status}"
exit 1
esac 
exit 0
