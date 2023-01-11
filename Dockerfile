FROM openjdk:11.0
EXPOSE 8085
WORKDIR /Library
COPY ./library.jar /Library
CMD java -jar /Library/library.jar
ENTRYPOINT ["java", "-jar","library.jar"]
#RUN locale-gen en_US.UTF-8
#RUN update-locale LANG=en_US.UTF-8 LC_MESSAGES=POSIX
