# Migrating the MySQL database using disk files

** DATE: Sept 12, 2022 **

Main sql file extensions

- .frm – This is the extension of file which contains the schema or definition of the table.
- .myd – This is the extension of file which contains MyISAM table data.
- .myi – This is the extension of file which contains MyISAM table indices.
Should exist for all tables.
- .opt - Whenever database is created or changed using MySQL commands, the characteristics of database are stored in text file namely db.opt file . 
- .ibd - These are the files with extensions \*.ibd which stores the data and index of MySQL InnoDB tables. This type of file is created or used by MySQL InnoDB software and associated with it . 

InnoDB is a general-purpose storage engine that balances high reliability and high performance. In MySQL 8.0, InnoDB is the default MySQL storage engine. Unless you have configured a different default storage engine, issuing a CREATE TABLE statement without an ENGINE clause creates an InnoDB table.

These database files are used for backup purpose for securing schema, data and indexes for some migration or upgrade of the database.

The disk files can be usually found in the /var/lib/mysql/ folder. But in the case of an SQL instance inside Kubernetes, the disk file location is something like */var/lib/docker/overlay2/0100534bfa545d2873d4a95202df9725d6c71a3fc669f0ef393c1fffbbd49b5a/merged/mnt/data/*.

This folder contains a sub-folder with the database name, which contains the files; 

- courses.frm  
- courses.ibd 
- db.opt

To find the correct place to copy from 

- sudo su
- find / -iname 'coursedb\*'
where 'coursedb' is the name of the database to find. 

**What files should we copy (transfer from source to target)???**

- definitely the *coursedb/* folder that contains the .frm, .idb, .opt files
- ibdata1, ib\_logfile0 and ib\_logfile1 in the */data* directory -- (for ERROR 1146 (42S02): Table 'database.TABLE\_ONE' doesn't exist from [https://stackoverflow.com/questions/7759170/mysql-table-doesnt-exist-but-it-does-or-it-should](https://stackoverflow.com/questions/7759170/mysql-table-doesnt-exist-but-it-does-or-it-should))
- we still get the same errors so ended up copying the entire *\data* folder!!! -- Find out if we can load the database without copying the entire folder. 

For ERROR 1006 (HY000) Can't create database (errno: 13) MySQL 5.6.1, we need to change permissions on the /data folder. 
[https://stackoverflow.com/questions/18719748/error-1006-hy000-cant-create-database-errno-13-mysql-5-6-12](https://stackoverflow.com/questions/18719748/error-1006-hy000-cant-create-database-errno-13-mysql-5-6-12)
**chown -R root:systemd-coredump file-path\data\**
Change permission from root to systemd-coredump, where systemd-coredump is the process handling the docker mysql instance. 

How do we find the location to copy the disk files to at the destination???
HACK! -- figure out the proper way. 
Create the mysql instance and the database at the destination machine and 'find' for coursedb. 

After copying, to reload the database, 
1. delete the existing mysql deployment (sudo kubectl delete deployment ...)
2. sudo kubectl run -it --rm --image=mysql:5.6 --restart=Never mysql-client -- mysql -h mysql -ppassword
The database (schema and data) should be loaded now!

ISSUES: 

- How would the PS system find the DB disk file path in the source machine?
- How would the PS system find the DB disk file path in the destination machine?
- To correctly reload, we should copy files and then run the mysql-client pod. But this further complicates finding the DB disk file path. 
- Creating the database manually at the destination doesn't sound right. It should happen with the disk files. Right now, we do this to create and find the file path. 


