package android.rockchip.update.dataRecord;

public class Constants {

    /*mqtt服务器tcp地址*/
    public static final String MQTT_BROKER_URL = "tcp://10.10.10.205:61613";
    /*mqtt服务器用户名*/
    public static final String SERVICE_USER = "admin";
    /*mqtt服务器密码*/
    public static final String SERVICE_PD = "password";
    /*后续以USR-EPC400-43-<MAC>*/
    //public static final String CLIENT_ID = "USR-EPC400";
    public static final String CLIENT_ID = "USR-EPC400-43";

    public static final String SUBSCRIPTION = "ctrlCmd";
    public static final String RETURN_TOPIC = "return";
    /*FTP*/
    public static final String FTP_SERVICE_ADD = "10.10.10.206";
    public static final String FTP_SERVICE_USER = "chenqigan";
    public static final String FTP_SERVICE_PD = "nibaba";
    public static final String FIRMWARE_NAME = "update.zip";
    public static final String FIRMWARE_URI = "ftp://"+FTP_SERVICE_USER+":"+FTP_SERVICE_PD+"@"+FTP_SERVICE_ADD+"/"+FIRMWARE_NAME;
    
    


}
