#!/usr/bin/env python
import pika
import os

os.chdir('../chameleon-prototype-ps')

file1 = open('variables.sh', 'r')  
for line in file1:
    if (line.strip()[0:14] == 'destination_ip'):
        destination_ip=line.strip()[16:-1]
file1.close()

connection = pika.BlockingConnection(
    pika.ConnectionParameters(host=destination_ip))
channel = connection.channel()

channel.queue_declare(queue='invocation_queue')

channel.basic_publish(exchange='', routing_key='invocation_queue', body='sample invocation body')
print(" [x] Sent new invocation to target machine")
connection.close()
