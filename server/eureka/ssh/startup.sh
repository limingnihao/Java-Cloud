#!/bin/sh

#服务器地址
ADDRESS=$2

#端口号，根据此端口号确定PID
PORT=$3

rm -f tpid

nohup java -jar org.limingnihao.server.eureka-1.0.0.jar --config.address=$ADDRESS --config.port=$PORT > logs/server.log 2>&1 &

echo $! > tpid