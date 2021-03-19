# Path Recreation Example

## How to use:

```bash
mvn package
java -jar target/PathRecreator-1.0-SNAPSHOT.jar siteA.csv siteB.csv
```

## Expected Result:
Paths of length > 1!
```bash
siteA.csv imported
siteB.csv imported
imported CCs: 
1:A <--> B:1
1:B <--> A:1
2:A <--> B:2
2:B <--> C:2
2:B <--> C:3
2:C <--> D:2
2:D <--> E:3
3:E <--> F:4
new path [2:A <--> B:2,2:B <--> C:2,2:C <--> D:2,2:D <--> E:3,3:E <--> F:4]
new path [2:B <--> C:2,2:C <--> D:2,2:D <--> E:3,3:E <--> F:4]
new path [2:C <--> D:2,2:D <--> E:3,3:E <--> F:4]
new path [2:D <--> E:3,3:E <--> F:4]
```

## TODOs
There is much left to do!

The algorithm checks if the `Connection Cable` is contained in the `Path` by comparing hashcodes not line numbers from imported csv files
Therefore, `[1:A <--> B:1,1:B <--> A:1]` is not a path!
