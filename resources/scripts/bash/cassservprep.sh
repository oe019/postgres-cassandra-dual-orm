#!/bin/bash
#3-8-17		olgunerguzel@reengen.com
# installs cassandra to ubunutu 16.04 azure vm

echo "ubunutu cassandra set up started..."
echo "installing java 8 oracle..."
apt-add-repository ppa:webupd8team/java
echo "updating ubunutu..."
apt-get update
echo "install java 8 oracle..."
apt-get install oracle-java8-installer
echo "installed java version is:"
java -version
sleep 5s
echo "setting JAVA_HOME and PATH variables for java 8 oracle..."
echo 'JAVA_HOME="/usr/lib/jvm/java-8-oracle"' >> /etc/environment
sleep 5s
echo "environment variable configurations finished..."
echo "installing JNA package..."
apt-get install libjna-java -y  
echo "JNA installation completed..."
sleep 5s
echo "adding cassandra repositories..."
echo "deb http://www.apache.org/dist/cassandra/debian 30x main" | sudo tee -a /etc/apt/sources.list.d/cassandra.sources.list  
echo "deb-src http://www.apache.org/dist/cassandra/debian 30x main" | sudo tee -a /etc/apt/sources.list.d/cassandra.sources.list
gpg --keyserver pgp.mit.edu --recv-keys F758CE318D77295D  
gpg --export --armor F758CE318D77295D | sudo apt-key add -  
gpg --keyserver pgp.mit.edu --recv-keys 2B5C1B00  
gpg --export --armor 2B5C1B00 | sudo apt-key add -  
gpg --keyserver pgp.mit.edu --recv-keys 0353B12C  
gpg --export --armor 0353B12C | sudo apt-key add -
echo "added cassandra repositories..."
sleep 5s
echo "updating ubunutu packages..."
apt-get update
echo "installing cassandra nosql database..."
apt-get install cassandra  
"cassandra installation completed..."
PATH=$PATH:/usr/lib/jvm/java-8-oracle/bin; export PATH
echo "cassandra current status is below:"
service cassandra status  
echo "configuration completed!"
