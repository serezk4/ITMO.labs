# fedora 38
# todo: fix copy lib into jar !

# pre-init
clear
java -version
rm -rf target

# create directories
mkdir target
mkdir target/classes
mkdir target/lib

# copy lib to build directory
cp ./lib/Pokemon.jar ./target/lib/

# build classes
echo 'building pokemons...'
javac -d ./target/classes -cp "lib/Pokemon.jar:./" ./Main.java $(find . | grep .java)

# create manifest
cd target/classes || exit
echo -e 'Main-Class: com/serezka/lab2/Main\nClass-Path: lib/Pokemon.jar' >> Manifest.mf
cat Manifest.mf

# create jar-file
jar cfm ../lab2.jar Manifest.mf ./com/serezka/lab2/Main.class ./com/serezka/lab2/*

# print result
cd ./..
tree
jar tf lab2.jar
java -jar lab2.jar
