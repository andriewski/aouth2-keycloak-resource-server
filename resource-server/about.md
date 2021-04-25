##### For checking Keycloak realm config try the following link, where mrk-apps - name of the created realm

    http://localhost:8081/auth/realms/mrk-apps/.well-known/openid-configuration
    
### To export realm execute command
   
    docker exec -it keycloak /opt/jboss/keycloak/bin/standalone.sh -Djboss.socket.binding.port-offset=1 -Dkeycloak.migration.action=export -Dkeycloak.migration.provider=singleFile -Dkeycloak.migration.realmName=mrk-apps -Dkeycloak.migration.usersExportStrategy=REALM_FILE -Dkeycloak.migration.file=/tmp/mrk-apps.json
    
##### then attach to container and copy the file from /tmp/mrk-apps.json

    docker cp <containerId>:/tmp/mrk-apps.json D:/git/udemy/aoth2/oauth2-keycloak-example/files

### To import realm run docker
    docker run --name custom-keycloak -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin -e KEYCLOAK_IMPORT=mrk-apps.json -v /D:/git/udemy/aoth2/oauth2-keycloak-example/files/:/tmp custom-keycloak