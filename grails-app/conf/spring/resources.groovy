import auth.ChildLabAuthFailureHandler
import auth.ChildLabAuthenticationTokenJsonRender
import auth.ChildLabSuccessHandler
import auth.ChildrenLabAuthFilter

import javax.servlet.http.HttpServletResponse

// Place your Spring DSL code here
beans = {
    restAuthenticationFilter(ChildrenLabAuthFilter) {
        authenticationManager = ref('authenticationManager')
        authenticationSuccessHandler = ref('restAuthenticationSuccessHandler')
        authenticationFailureHandler = ref('restAuthenticationFailureHandler')
        authenticationDetailsSource = ref('authenticationDetailsSource')
        credentialsExtractor = ref('credentialsExtractor')
        tokenStorageService = ref('tokenStorageService')
        endpointUrl = application.config.grails.plugin.springsecurity.rest.login.endpointUrl ?: '/api/login'
        tokenGenerator = ref('tokenGenerator')
    }

    restAuthenticationFailureHandler(ChildLabAuthFailureHandler) {
        statusCode = application.config.grails.plugin.springsecurity.rest.login.failureStatusCode ?: HttpServletResponse.SC_UNAUTHORIZED
        //renderer = ref('restAuthenticationTokenJsonRenderer')
    }

    defaultRestAuthenticationTokenJsonRenderer(ChildLabAuthenticationTokenJsonRender) {
        usernamePropertyName = "email"
        tokenPropertyName = "access_token"
        authoritiesPropertyName = "rule"
        //renderer = ref('restAuthenticationTokenJsonRenderer')
    }

    restAuthenticationSuccessHandler(ChildLabSuccessHandler){
        renderer = ref('defaultRestAuthenticationTokenJsonRenderer')
    }


}
