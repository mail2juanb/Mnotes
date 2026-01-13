# Utilise une image Alpine légère avec JRE 21 (pas besoin de JDK en production) sinon eclipse-temurin:21-jdk-jammy (assez lourd mais avec un JDK)
FROM eclipse-temurin:21-jre-alpine

# Définit le répertoire de travail dans le conteneur
WORKDIR /app

# Variables d'environnement pour la configuration
ENV MONGO_HOST=mongodb-mnotes
ENV MONGO_PORT=27017
ENV EUREKA_SERVER_HOST=eureka-server
ENV EUREKA_SERVER_PORT=9102

# Copier le jar de l'application
COPY target/mnotes-*.jar app.jar

# Exposer le port sur lequel Eureka écoute
EXPOSE 9002

# Commande pour lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]