package childrenlab

import grails.transaction.Transactional

import java.text.DateFormat
import java.text.SimpleDateFormat

@Transactional
class ToolsService {

    def Date stringToDate(String date, String format = "yyyy-MM-dd hh:mm"){
        try{
            if(!date){ return null }
            DateFormat dateFormat = new SimpleDateFormat(format, Locale.ENGLISH)
            return dateFormat.parse(date)
        }catch(Exception e){
            return null
        }

    }
}
