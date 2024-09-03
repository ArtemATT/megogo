FROM openjdk:21-jdk-slim

ARG ALLURE_VERSION=2.29.0

# Install dependencies
RUN apt-get update && \
    apt-get install -y wget unzip && \
    apt-get clean;

# Install Maven Wrapper
RUN wget https://raw.githubusercontent.com/takari/maven-wrapper/master/mvnw -O /usr/local/bin/mvnw && \
    chmod +x /usr/local/bin/mvnw;

# Install Maven Wrapper Scripts
COPY .mvn /app/.mvn
COPY mvnw /app/mvnw

# Install Allure Commandline
RUN wget https://github.com/allure-framework/allure2/releases/download/$ALLURE_VERSION/allure-$ALLURE_VERSION.zip && \
    unzip allure-$ALLURE_VERSION.zip -d /opt && \
    ln -s /opt/allure-$ALLURE_VERSION/bin/allure /usr/local/bin/allure && \
    rm allure-$ALLURE_VERSION.zip;

# Set the working directory
WORKDIR /app

# Copy Maven project files
COPY pom.xml .
COPY src /app/src

# Install project dependencies and build the project
RUN ./mvnw install

CMD allure generate target/allure-results --clean -o target/allure-report
