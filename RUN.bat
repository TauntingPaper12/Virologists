dir /s /B *.java > sources.txt

javac @sources.txt -d ./out -encoding UTF-8
java -cp ./out projlab.Skeleton
