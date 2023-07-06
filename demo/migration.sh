#!/bin/sh

echo "\n=========== start flyway  ==========\n"
cd db_schema

for file in `ls`; do
	if [[ $file =~ ".json" ]] 
		then
			echo "Migrating ${file} ..."
			java -jar scalardb-schema-loader-3.9.0.jar --config ../scalardb.properties --schema-file ./${file} --coordinator;
	fi
done
echo "\n=========== end  flyway  ==========\n"