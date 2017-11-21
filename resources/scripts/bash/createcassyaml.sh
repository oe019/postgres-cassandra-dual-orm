#/bin/bash

#3-8-17		olgunerguzel@reengen.com
#overrides cassandra.yaml file to /config/cassandra/
#parameters {overrideproperties ie key1=value1 key2=value2 ..}

#get override properties to memory

#if[overrideproperties.hasextension]{echo no extension allowed for parameter files; break script}

#if(overrideproperries is not empty){
#foreach line in overrideproperties parameter
#        foreach line in /etc/cassandra/cassandra.yaml{
#                if(overrideproperties.line.value==cassandra.yml.line.value  ){
#                        [ cassandra.yml.line.value=overrideproperties.line.value ]
#		}
#	}
#}
#fi[echo !WARNING: parameter file is empty... Original cassandra.yaml file preserved! ]

#TODO

while read line
do
	for param in "$@"
	do
		if ["$param"="$line"]; then
			echo "parameter=$param and satir=$line"
		fi 
			echo "not the same"
	done

done</etc/cassandra/default/cassandra.yaml
