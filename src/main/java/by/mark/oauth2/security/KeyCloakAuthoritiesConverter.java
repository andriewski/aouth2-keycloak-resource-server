package by.mark.oauth2.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KeyCloakAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    /**
     * Claims has next structure:
     * <pre>
     * "claims": {
     *    ...
     *    "realm_access": {
     *        "roles": [
     *            "developer",
     *            "other_role"
     *        ]
     *    }
     *    "scope": "openid address profile email",
     *    ...
     * }
     * </pre>
     *
     * @param jwt jwt token that we want to convert to list of granted authorities
     * @return list of {@link GrantedAuthority}
     */
    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        return Stream.concat(getRolesAsGrantedAuthoritiesStream(jwt), getScopesAsGrantedAuthoritiesStream(jwt))
                .collect(Collectors.toList());

    }

    @SuppressWarnings("unchecked")
    private Stream<GrantedAuthority> getRolesAsGrantedAuthoritiesStream(Jwt jwt) {
        Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims().get("realm_access");

        if (CollectionUtils.isEmpty(realmAccess)) {
            return Stream.empty();
        }

        List<String> roles = (List<String>) realmAccess.get("roles");

        if (CollectionUtils.isEmpty(roles)) {
            return Stream.empty();
        }

        return roles.stream()
                .map(roleName -> "ROLE_" + roleName)
                .map(SimpleGrantedAuthority::new);
    }

    private Stream<SimpleGrantedAuthority> getScopesAsGrantedAuthoritiesStream(Jwt jwt) {
        Object scopes = jwt.getClaims().get("scope");

        if (scopes == null) {
            return Stream.empty();
        }

        return Arrays.stream(scopes.toString().split(" "))
                .map(scopeName -> "SCOPE_" + scopeName)
                .map(SimpleGrantedAuthority::new);
    }
}
