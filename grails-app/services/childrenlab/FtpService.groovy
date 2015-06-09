package childrenlab

import grails.transaction.Transactional
import org.apache.commons.net.ftp.FTPClient

@Transactional
class FtpService {
    def grailsApplication

    static transactional = true

    def save(byte[] file, String fileName, String folder) {
        def folders = folder.split("/")

        new FTPClient().with {
            connect grailsApplication.config.ftp.server
            enterLocalPassiveMode()
            login grailsApplication.config.ftp.username, grailsApplication.config.ftp.password
            String directory = "";
            folders.each(){
                directory += it
                makeDirectory directory
                directory += "/"
            }
            changeWorkingDirectory folder
            fileType = FTPClient.BINARY_FILE_TYPE
            def bais = new ByteArrayInputStream(file)
            storeFile(fileName, bais)
            disconnect()
        }
    }

}