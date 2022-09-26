## Running the daemon from the RabbitMQ docker image

`sudo docker run -it --hostname my-rabbit --name some-rabbit rabbitmq:3`

This will start a RabbitMQ container listening on the default port of 5672. (needs to be run on a separate terminal (-it)) 

Verify with `sudo docker ps`. The created container would be removed when we exit the docker run of this container. 

We should specify -h/--hostname explicitly for each daemon so that we don't get a random hostname and can keep track of our data.

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
