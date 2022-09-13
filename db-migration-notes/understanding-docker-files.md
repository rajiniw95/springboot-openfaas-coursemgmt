The relevant database disk files are stored in a path similar to the below;

**/var/lib/docker/overlay2/f3cd27bd4d5d97ff5ca80cf31875c362be0b0f9d8bc6b3476b984e01922d4c9d/merged/mnt/data/coursedb**

where f3cd27bd4d5d97ff5ca80cf31875c362be0b0f9d8bc6b3476b984e01922d4c9d is a long hash of a docker image layer. 

## What does this mean? How would we find the correct disk file path for each new instance? 

The path is currently hardcoded in the *variables.sh* file -- needs to be fixed!

## Understanding docker file path...

[https://docs.docker.com/storage/](https://docs.docker.com/storage/)

By default all files created inside a container are stored on a writable container layer. 

A container’s writable layer is tightly coupled to the host machine where the container is running. 

Writing into a container’s writable layer requires a storage driver to manage the filesystem. The storage driver provides a union filesystem, using the Linux kernel. This extra abstraction reduces performance as compared to using data volumes, which write directly to the host filesystem.

Docker has two options for containers to store files on the host machine, so that the files are persisted even after the container stops: volumes, and bind mounts.

No matter which type of mount you choose to use, the data looks the same from within the container. It is exposed as either a directory or an individual file in the container’s filesystem.

[https://stackoverflow.com/questions/46672001/is-it-safe-to-clean-docker-overlay2](https://stackoverflow.com/questions/46672001/is-it-safe-to-clean-docker-overlay2)

Docker uses **/var/lib/docker** to store your images, containers, and local named volumes. Deleting this can result in data loss and possibly stop the engine from running. 

The **overlay2** subdirectory specifically contains the various filesystem layers for images and containers.

[https://docs.docker.com/storage/storagedriver/overlayfs-driver/](https://docs.docker.com/storage/storagedriver/overlayfs-driver/)

OverlayFS is a modern union filesystem that is similar to AUFS, but faster and with a simpler implementation. Docker provides two storage drivers for OverlayFS: the original overlay, and the newer and more stable overlay2.

```console
root@ra-esl-target-tacc-sep12-instance-server-nbapd425upcs:/var/lib/docker# docker info
Client:
 Context:    default
 Debug Mode: false
 Plugins:
  app: Docker App (Docker Inc., v0.9.1-beta3)
  buildx: Docker Buildx (Docker Inc., v0.9.1-docker)

Server:
 Containers: 2
  Running: 2
  Paused: 0
  Stopped: 0
 Images: 14
 Server Version: 20.10.18
 ***Storage Driver: overlay2***
  Backing Filesystem: extfs
  Supports d_type: true
  Native Overlay Diff: true
  userxattr: false
```

Docker is using the overlay2 storage driver and has automatically created the overlay mount with the required lowerdir, upperdir, **merged**, and workdir constructs.

Each image layer has its own directory within /var/lib/docker/overlay2/, which contains its contents. The image layer IDs do not correspond to the directory IDs.

OverlayFS layers two directories on a single Linux host and presents them as a single directory. These directories are called layers and the unification process is referred to as a union mount. OverlayFS refers to the lower directory as lowerdir and the upper directory a upperdir. The unified view is exposed through its own directory called **merged**.

## In summary: 

- docker stores all its files in /var/lib/docker/ 
- overlay2 is the storage driver used by docker
- this folder containes hased folder names for docker image layers
- diff contains the layer’s contents
- merged contains the unified contents of its parent layer and itself

The same 'coursedb' folder (disk files) can be found in 'diff' and 'merged' directories. Do we need to update both these to reload at the target machine? The reload seems to work by just updating in the 'merged' folder! But is this correct? Would this create problems in future when running workloads on this database? 

The image layer is the lowerdir and the container layer is the upperdir. The unified view is exposed through a directory called merged which is effectively the containers mount point. The diagram shows how Docker constructs map to OverlayFS constructs. Check the webpage above for the diagram. 

Where the image layer and the container layer contain the same files, the container layer “wins” and obscures the existence of the same files in the image layer.  
