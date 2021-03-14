FROM adoptopenjdk/openjdk15:latest AS TEMP_BUILD_IMAGE
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY build.gradle settings.gradle gradlew $APP_HOME/
COPY gradle $APP_HOME/gradle/
RUN ./gradlew -x test --info || return 0
COPY . .
RUN ./gradlew -x test build

FROM adoptopenjdk/openjdk15:latest
ENV ARTIFACT_RESERVATION_NAME=jihun.war
ENV ARTIFACT_ALARM_NAME=alarmServer.jar
ENV ARTIFACT_REFLECT_NAME=reflectPromotionServer.jar
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY --from=TEMP_BUILD_IMAGE $APP_HOME/entrypoint.sh .
COPY --from=TEMP_BUILD_IMAGE $APP_HOME/module-reservation/build/libs/$ARTIFACT_RESERVATION_NAME .
COPY --from=TEMP_BUILD_IMAGE $APP_HOME/module-alarm/build/libs/$ARTIFACT_ALARM_NAME .
COPY --from=TEMP_BUILD_IMAGE $APP_HOME/module-reflectPromotion/build/libs/$ARTIFACT_REFLECT_NAME .

EXPOSE 8080
EXPOSE 8975
EXPOSE 8976
EXPOSE 3306

ENTRYPOINT ["bash", "entrypoint.sh"]
