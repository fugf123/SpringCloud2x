#!/usr/bin/env bash

mvn clean package -Dmaven.test.skip=true

docker build -t hub.c.163.com/springcloud/eureka

docker tag hub.c.163.com/springcloud/eureka hub.c.163.com/springcloud/eureka

docker push hub.c.163.com/springcloud/eureka