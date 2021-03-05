#! /bin/bash

docker run -d --hostname my-rabbit --name guowm-rabbit -e RABBITMQ_DEFAULT_USER=admin -e RABBITMQ_DEFAULT_PASS=admin -p 15672:15672 -p 5672:5672 rabbitmq:3.8.14-management
