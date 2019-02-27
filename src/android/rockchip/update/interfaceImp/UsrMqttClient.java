package android.rockchip.update.interfaceImpl;

import android.rockchip.update.dataRecord.RemoteRequestMsg;
import android.rockchip.update.dataRecord.RemoteServiceAcountInfo;
import android.rockchip.update.util.UsrMqttAndroidClient;
import android.rockchip.update.interfaces.IClient;
import android.rockchip.update.interfaces.ServiceCallBack;
import android.util.Log;
import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import java.io.UnsupportedEncodingException;
import android.content.Context;
import android.content.Intent;
import android.rockchip.update.service.PackageDownloadActivity;


public class UsrMqttClient implements IClient{

    private final String TAG = "UsrMqttClient";
    private final String FIRMWARE_URI = "ftp://chenqigan:nibaba@10.10.10.205/update.zip";
    /**客户端需要接收消息的topic */
    private final String[] topics= {"cmdCtrl"};

    /*代表本机client,对一个client进行了封装
    **/
    private UsrMqttAndroidClient usrMqttAndroidClient ;
    /* 一个通用的AndroidMqttClient用于发布或订阅topic*/
    private MqttAndroidClient mqttAndroidClient;

    private Context mContext;//这个必须为getApplicationContext()

    public UsrMqttClient(Context context){

        mContext = context;
    }

    /**与服务器建立链接 */
    @Override
    public int connectToService(RemoteServiceAcountInfo acountInfo, ServiceCallBack callBack){
        try{
            usrMqttAndroidClient = new UsrMqttAndroidClient();
            mqttAndroidClient = usrMqttAndroidClient.getMqttClient(mContext,acountInfo);
            /**设置监听器 */
            mqttAndroidClient.setCallback(callBack);
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    /**用于注册所有的topic */
    public int subscribeTopics(){
        try{        
            for(String topic:topics){
                Log.d(TAG, "subscribeTopics:[topic]"+topics);
                /**此处需要将注册成功的结果返回 */
                usrMqttAndroidClient.subscribe(mqttAndroidClient, topic, 2);
            }
        }catch(MqttException mqttE){
            mqttE.printStackTrace();
            Log.e(TAG, "subscribeTopics:error!!!");
            return -1;
        }
        return 0;
    }

    /**与服务器断开链接 */
    @Override
    public int disconnectToService(){
        try {
            usrMqttAndroidClient.disconnect(mqttAndroidClient);
        } catch (MqttException e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    @Override
    /**发送msg至服务器 */
    public int sendMsgToService(RemoteRequestMsg msg){

        Log.d(TAG, "sendMsgToService:"+msg.toString());
        return 0;
    }
}