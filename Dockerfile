#1
#docker network create jenkins

#2
#docker run --name jenkins-docker --rm --detach
#--restart=on-failure
#  --privileged --network jenkins --network-alias docker
#  --env DOCKER_TLS_CERTDIR=/certs
#  --volume jenkins-docker-certs:/certs/client
#  --volume jenkins-data:/var/jenkins_home
#  --publish 2376:2376
#  docker:dind

# docker run --name jenkins-blueocean
#--restart=on-failure
#--detach
#--network jenkins
#--env DOCKER_HOST=tcp://docker:2376
#--env DOCKER_CERT_PATH=/certs/client
#--env DOCKER_TLS_VERIFY=1
#--volume jenkins-data:/var/jenkins_home
#--volume jenkins-docker-certs:/certs/client:ro
#--publish 8090:8080
#--publish 50000:50000
# myjenkins-blueocean:2.492.3-1

# docker exec -it jenkins-blueocean cat /var/jenkins_home/secrets/initialAdminPassword
#1 docker exec --user root -it jenkins-blueocean bash
#2 git clone ...
#3 ssh-keygen -t rsa -b 2048 -C "jenkins git key"
# password wsxcde32
#4 public key
#cat /root/.ssh/id_rsa.pub
#5 private key
#cat /root/.ssh/id_rsa



#FROM jenkins/jenkins:2.492.3-jdk17
FROM jenkins/jenkins:2.430-jdk21

USER root
RUN apt-get update && apt-get install -y lsb-release
RUN curl -fsSLo /usr/share/keyrings/docker-archive-keyring.asc \
  https://download.docker.com/linux/debian/gpg

# Установка Chrome
RUN curl -LO  https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
RUN apt-get install -y ./google-chrome-stable_current_amd64.deb
RUN rm google-chrome-stable_current_amd64.deb
# Check chrome version
RUN echo "Chrome: " && google-chrome --version

# Установка Firefox не пошло
#RUN apt-get update && \
#    apt-get install -y firefox && \
#    rm -rf /var/lib/apt/lists/* \

# Установка Microsoft Edge не пошло
#RUN apt-get update && apt-get install -y microsoft-edge-stable

RUN echo "deb [arch=$(dpkg --print-architecture) \
  signed-by=/usr/share/keyrings/docker-archive-keyring.asc] \
  https://download.docker.com/linux/debian \
  $(lsb_release -cs) stable" > /etc/apt/sources.list.d/docker.list
RUN apt-get update && apt-get install -y docker-ce-cli
USER jenkins
RUN jenkins-plugin-cli --plugins "blueocean docker-workflow"

# 1 docker-compose
#FROM jenkins/jenkins:lts
#RUN jenkins-plugin-cli --plugins workflow-aggregator git docker-workflow
#RUN apt-get update && apt-get install -y maven
#COPY jenkins_home/custom_config.xml /var/jenkins_home/config.xml
#COPY scripts/init.groovy.d /usr/share/jenkins/ref/init.groovy.d/
#EXPOSE 8080
#EXPOSE 50000

# 2 jenkins.yaml
#FROM jenkins/jenkins:lts
#RUN jenkins-plugin-cli --plugins configuration-as-code
#COPY jenkins.yaml /var/jenkins_home/jenkins.yaml
#EXPOSE 8082
#EXPOSE 50002

# 3
#FROM jenkins/jenkins:lts
#USER root
#RUN apt-get update && apt-get install -y docker.io