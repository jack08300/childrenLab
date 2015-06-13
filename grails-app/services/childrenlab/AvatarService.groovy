package childrenlab

import grails.transaction.Transactional

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

@Transactional
class AvatarService {

    def convertUploadImageFileToBytes(def file){
        BufferedImage originalImage = ImageIO.read(file.getInputStream())

        if(originalImage.width < 100 || originalImage.height < 100){
            return [success: false, message: "We're unable to crop images smaller than 200 pixels, please use a larger size"]
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write( originalImage, "png", baos );
        baos.flush();
        return [success: true, imageBytes:  "data:image/png;base64,${baos.toByteArray().encodeBase64()}", width: originalImage.width, height: originalImage.height];
    }
}
