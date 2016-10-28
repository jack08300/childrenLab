package childrenlab

import com.notnoop.apns.APNS
import com.notnoop.apns.ApnsService
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Secured(['IS_AUTHENTICATED_ANONYMOUSLY', 'ROLE_TESTER', 'ROLE_ADMIN'])
class PushNotificationController {

    ApnsService service

    @Secured(['ROLE_ADMIN'])
    def push(String token, String message){

        try{
            setupService()
            String payload = APNS.newPayload().alertBody(message).build();
            service.push(token, payload)

            Map<String, Date> inactiveDevices = service.getInactiveDevices();
            for (String deviceToken : inactiveDevices.keySet()) {
                Date inactiveAsOf = inactiveDevices.get(deviceToken);
                println inactiveAsOf
            }
        }catch(Exception e){
            e.printStackTrace()
        }

        render([success: true] as JSON)

    }

    def setupService(){
        try{
            if(!service){
                InputStream certFile = this.class.classLoader.getResourceAsStream('data/swing-push-product.p12')
                service = APNS.newService().withCert(certFile, "111111").withProductionDestination().build()
            }
        }catch(Exception e){
            e.printStackTrace()
        }

        return service;
    }
}
