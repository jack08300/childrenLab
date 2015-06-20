package auth

import childrenlab.User
import com.odobo.grails.plugin.springsecurity.rest.RestAuthenticationToken
import com.odobo.grails.plugin.springsecurity.rest.oauth.OauthUser
import com.odobo.grails.plugin.springsecurity.rest.token.rendering.RestAuthenticationTokenJsonRenderer
import grails.converters.JSON
import groovy.util.logging.Slf4j
import org.pac4j.core.profile.CommonProfile
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.util.Assert

/**
 * Created by Jay on 6/20/2015.
 */
@Slf4j
class ChildLabAuthenticationTokenJsonRender implements RestAuthenticationTokenJsonRenderer {

    String usernamePropertyName
    String tokenPropertyName
    String authoritiesPropertyName

    Boolean useBearerToken

    String generateJson(RestAuthenticationToken restAuthenticationToken) {
        Assert.isInstanceOf(UserDetails, restAuthenticationToken.principal, "A UserDetails implementation is required")
        UserDetails userDetails = restAuthenticationToken.principal as UserDetails

        def result = [
                (usernamePropertyName) : userDetails.username,
                (authoritiesPropertyName) : userDetails.authorities.collect {GrantedAuthority role -> role.authority }
        ]

        result["$tokenPropertyName"] = restAuthenticationToken.tokenValue

        if (userDetails instanceof OauthUser) {
            CommonProfile profile = (userDetails as OauthUser).userProfile
            result.with {
                email = profile.email
                displayName = profile.displayName
            }
        }

        if (useBearerToken) {
            result.token_type = 'Bearer'
        }

        //get user profile
        result.profileImage = User.findByEmail(userDetails.username)?.profile

        def jsonResult = result as JSON

        log.debug "Generated JSON:\n${jsonResult.toString(true)}"

        return jsonResult.toString()
    }
}
