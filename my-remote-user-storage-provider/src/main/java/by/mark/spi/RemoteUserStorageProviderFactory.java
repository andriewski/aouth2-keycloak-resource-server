package by.mark.spi;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.storage.UserStorageProviderFactory;

public class RemoteUserStorageProviderFactory implements UserStorageProviderFactory<RemoteUserStorageProvider> {

    public static final String PROVIDER_NAME = "my-remote-user-storage-provider";
    public static final String LEGACY_USER_SERVICE_API = "http://legacy-user-service:8080";

    @Override
    public RemoteUserStorageProvider create(KeycloakSession session, ComponentModel model) {
        return new RemoteUserStorageProvider(session, model, buildHttpClient(LEGACY_USER_SERVICE_API));
    }

    @Override
    public String getId() {
        return PROVIDER_NAME;
    }

    private UsersApiService buildHttpClient(String uri) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(uri);

        return target.proxyBuilder(UsersApiService.class)
                .classloader(UsersApiService.class.getClassLoader())
                .build();
    }
}
