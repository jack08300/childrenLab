package childrenlab

import grails.transaction.Transactional
import sun.misc.BASE64Decoder

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

@Transactional
class AvatarService {

    def ftpService
    def springSecurityService

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

    def uploadProfileImage(String encodedImage){
        encodedImage = encodedImage.replace("data:image/png;base64,", "")
        BufferedImage image = decodeToImage(encodedImage)
        ByteArrayOutputStream baos = new ByteArrayOutputStream()
        ImageIO.write( image, "png", baos )

        User user = springSecurityService.getCurrentUser() as User

        String fileName = "avatar_${user.id}.png"
        ftpService.save(baos.toByteArray(), fileName, "avatars")

        user.profile = fileName

        return [success: true, profileImage: user.profile]

    }

    def uploadKidProfileImage(String encodedImage, int kidId){
        User user = springSecurityService.getCurrentUser() as User
        def kid = Kids.findByParentAndId(user, kidId)
        if(!kid) {
            return [success: false, message: "Can't find the kid"]
        }
        encodedImage = encodedImage.replace("data:image/png;base64,", "")
        BufferedImage image = decodeToImage(encodedImage)
        ByteArrayOutputStream baos = new ByteArrayOutputStream()
        ImageIO.write( image, "png", baos )

        String fileName = "avatar_kid_${kid.id}.png"
        ftpService.save(baos.toByteArray(), fileName, "avatars")

        kid.profile = fileName

        return [success: true, profileImage: kid.profile]

    }
    def BufferedImage decodeToImage(String imageString) {

        BufferedImage image = null;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder()
            imageByte = decoder.decodeBuffer(imageString)
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte)
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
}
