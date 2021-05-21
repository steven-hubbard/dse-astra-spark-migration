# DSE To Astra Spark Migration

This Spark tool will allow you to make a direct connection to your Astra instance from DSE. This application specifically was created to move data from DSE to Astra, but with simple changes could be altered to reverse the flow, or compare data between the two data stores.

## Installation

Use sbt to create a new Jar file. The created Jar will be found at the following location:

dse-astra-spark-migration/target/scala-2.11/Migration.jar

```bash
sbt clean assembly
```

## Usage
Use dse-submit to start the migration

[username] = Source DSE username

[password] = Source DSE password

[num cores] = Int value for number of cores

[num executors] = Int value for number of executors

[GB of memory] = Int value for GB of memory. Note the G needs to be supplied

```bash
dse -u [username] -p [password] spark-submit --class com.astra.spark.Migration --executor-cores
[num cores] --num-executors [num executors] --executor-memory [GB of memory]G Migration.jar
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[GNU](https://github.com/steven-hubbard/dse-astra-spark-migration/blob/main/LICENSE)