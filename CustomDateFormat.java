import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateFormat {
    SimpleDateFormat formater;
    public CustomDateFormat(){
        formater=new SimpleDateFormat("dd/MM/yyyy");
    }
    public Date cvStringToDate(String str){
        Date date=null;
        try{
             date= formater.parse(str);
             return date;
        }
        catch(Exception e){
        System.out.println("< Date khong hop le.Dinh dang dung la dd/MM/yyyy >");
        return null;
        }
        
    }
    public String cvDateToString(Date date){
     return formater.format(date);
    }

}
