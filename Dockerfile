FROM jenkins/jenkins:lts

# 1
#RUN jenkins-plugin-cli --plugins workflow-aggregator git docker-workflow
#COPY jenkins_home/custom_config.xml /var/jenkins_home/config.xml
#COPY scripts/init.groovy.d /usr/share/jenkins/ref/init.groovy.d/

# 2
FROM jenkins/jenkins:lts
RUN jenkins-plugin-cli --plugins configuration-as-code
COPY jenkins.yaml /var/jenkins_home/jenkins.yaml

EXPOSE 8080
EXPOSE 50000