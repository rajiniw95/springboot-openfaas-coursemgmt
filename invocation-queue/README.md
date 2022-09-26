## Running the daemon from the RabbitMQ docker image

`sudo docker run --rm -it -p 15672:15672 -p 5672:5672 rabbitmq:3-management`

This will start a RabbitMQ container listening on the default port of 5672. (needs to be run on a separate terminal (-it)) 

Verify with `sudo docker ps`. The created container would be removed when we exit the docker run of this container. 

We should specify -h/--hostname explicitly for each daemon so that we don't get a random hostname and can keep track of our data.

Install the python pika package with `python -m pip install pika --upgrade`

## Running the sample Hello World queue 

First, let's start a **consumer**, which will run continuously waiting for deliveries:

`python receive.py`

`# => [*] Waiting for messages. To exit press CTRL+C`

Now start the **producer** in a new terminal. The producer program will stop after every run:

`python send.py`

`# => [x] Sent 'Hello World!'`

The consumer will print the message:

`# => [*] Waiting for messages. To exit press CTRL+C`

`# => [x] Received 'Hello World!'`

## To send and receive on different physical machines

1. At sourceIP, `sudo docker run --rm -it -p 15672:15672 -p 5672:5672 rabbitmq:3-management`

2. At targetIP, `sudo docker run --rm -it -p 15672:15672 -p 5672:5672 rabbitmq:3-management`

3. Open new terminals in each sourceIP and targetIP and run in both, `python -m pip install pika --upgrade`

4. `python receive.py` in the targetIP (new site), with `host=localhost`

5. `python send.py` in the sourceIP (old site), with `host=targetIP`

 


