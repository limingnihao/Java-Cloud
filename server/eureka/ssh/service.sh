#!/bin/bash

#服务器地址
ADDRESS=$2

#端口号，根据此端口号确定PID
PORT=$3

#查询出监听了PORT端口TCP协议的程序
pid=`netstat -anp|grep $PORT|awk '{printf $7}'|cut -d/ -f1`


start(){
    if [ -n "$pid" ]; then
       echo "server already start,pid:$pid"
       return 0
    fi
    nohup java -jar org.limingnihao.server.eureka-1.0.0.jar --config.address=$ADDRESS --config.port=$PORT > logs/server.log 2>&1 &
    echo "start at port:$PORT"
}

stop(){
    if [ -z "$pid" ]; then
       echo "not find program on port:$PORT"
       return 0
    fi
    #结束程序，使用讯号2，如果不行可以尝试讯号9强制结束
    kill -9 $pid
    rm -rf $pid
    echo "kill program use signal 2,pid:$pid"
}
status(){
    if [ -z "$pid" ]; then
       echo "not find program on port:$PORT"
    else
       echo "program is running,pid:$pid"
    fi
}

case $1 in
    start)
       start
    ;;
    stop)
       stop
    ;;
    restart)
       $0 stop
       sleep 2
       $0 start
    ;;
    status)
       status
    ;;
    *)
       echo "Usage: {start|stop|status}"
    ;;
esac

exit 0