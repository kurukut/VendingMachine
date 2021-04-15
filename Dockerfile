From openjdk:11
EXPOSE 9090
copy ./target/VendingMachine-0.0.1-SNAPSHOT.jar VendingMachine-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","VendingMachine-0.0.1-SNAPSHOT.jar"]