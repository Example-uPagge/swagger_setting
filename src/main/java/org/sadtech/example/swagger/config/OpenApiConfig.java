package org.sadtech.example.swagger.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

/**
 * @author upagge 30.12.2020
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Loyalty System Api",
                description = "Loyalty System", version = "1.0.0",
                contact = @Contact(
                        name = "Struchkov Mark",
                        email = "mark@struchkov.dev",
                        url = "https://mark.struchkov.dev"
                )
        )
)
//@SecurityScheme(
//        name = "Oauth2",
//        type = SecuritySchemeType.OAUTH2,
//        flows = @OAuthFlows(
//                authorizationCode = @OAuthFlow(
//                        tokenUrl = "https://oauth.mocklab.io/oauth/token",
//                        refreshUrl = "https://oauth.mocklab.io/oauth/token",
//                        authorizationUrl = "https://oauth.mocklab.io/oauth/authorize",
//                        scopes = {@OAuthScope(name = "profile"), @OAuthScope(name = "email")}
//                )
//        )
//)
@SecurityScheme(
        name = "jsessionid",
        in = SecuritySchemeIn.COOKIE,
        type = SecuritySchemeType.APIKEY,
        paramName = "JSESSIONID"
)
public class OpenApiConfig {

}
