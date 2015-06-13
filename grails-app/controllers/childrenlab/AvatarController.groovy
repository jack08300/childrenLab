package childrenlab

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER'])
class AvatarController {

    def avatarService

    def temp(){
        def result
        try{
            def file = request.getFile('img')
            if (file) {
                result = avatarService.convertUploadImageFileToBytes(file)
            }else{
                result = [success: false, message: "No image uploaded."]
            }
        }catch(Exception e){
            log.error("Error on Avatar Controller tempAvatar: ${e.message}")
            result = [status: "error", message: "Unable to open image, please check the file and try again."]
        }

        render result as JSON
    }
}
