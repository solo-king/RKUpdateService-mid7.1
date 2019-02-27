package android.rockchip.update.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.rockchip.update.util.UsrDelay;
import android.util.Log;
import android.rockchip.update.dataRecord.Constants;
import android.rockchip.update.dataRecord.RemoteServiceAcountInfo;
import android.rockchip.update.interfaceImp.MqttRemoteServiceConnector;
import android.rockchip.update.interfaces.IRemoteServiceConnector;
import android.rockchip.update.interfaces.ServiceCallBack;
import android.rockchip.update.interfaces.IClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;

/*功能描述:
*   用于接收并处理远端服务器消息
*   */
public class DealThingsService extends Service {

    private final String TAG = "DealThingsService";
    /**服务处理间隔时间 */
    private final long SERVICE_DEAL_INTERVAL_TIME = 1000;

    /**账号信息 */
    private RemoteServiceAcountInfo mServiceAcountInfo;
    /**用于远端链接器,具体是ftp还是mqtt看指向的实现类 */
    private IRemoteServiceConnector mRemoteServiceContor;
    /**记录客户端 */
    private IClient mClient;
    
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        /**填充账号信息 */
        fillAcountInfo();
        /**远端服务其注册 */
        connectToRemoteService();
        
    }

    private void fillAcountInfo(){

        /**对于mqtt服务端的类型 */
        mServiceAcountInfo = new RemoteServiceAcountInfo();
        mServiceAcountInfo.setDeviceId(Constants.CLIENT_ID);
        mServiceAcountInfo.setHostAddress(Constants.MQTT_BROKER_URL);
        mServiceAcountInfo.setHostUser(Constants.SERVICE_USER);
        mServiceAcountInfo.setHostPassword(Constants.SERVICE_PD);
        Log.d(TAG,  "fillAcountInfo:[mServiceAcountInfo]"+mServiceAcountInfo.toString());
    }

    /**连接至远端服务器 */
    private void connectToRemoteService(){
        try{
            mRemoteServiceContor =  new MqttRemoteServiceConnector(getApplicationContext());
            /**记录当前的client */
            mClient =  mRemoteServiceContor.connectToRemoteService(mServiceAcountInfo, new ServiceCallBack(){
                @Override
                public void connectComplete(boolean b, String s) {
                    Log.d(TAG, "connectComplete");
                    /**注册需要订阅的topic */
                    mClient.subscribeTopics();
                }
    
                @Override
                public void connectionLost(Throwable throwable) {
                    Log.d(TAG, "connectionLost:print throwable blow" );
                    throwable.printStackTrace();
                }
    
                @Override
                public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                    /*处理接收到的消息*/
                    dealThingFromRemoteService(topic, mqttMessage);
                }
    
                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
    
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**对命令做处理, 此处可以使用命令设计模式 */
    private void dealThingFromRemoteService(String topic, MqttMessage mqttMessage){

        String msg = new String(mqttMessage.getPayload());
        Log.d(TAG, "dealThingFromRemoteService:[topic]"+topic+"[msg]"+msg);
        if("update".equals(msg)){

            startUpdateFirmware();
            
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }



    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind");
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
        /**使用的服务器资源必须再次释放 */
    }

     /**开始下载并更新固件 */
     private void startUpdateFirmware() {
        Intent intent = new Intent(getApplicationContext(), PackageDownloadActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("uri", Constants.FIRMWARE_URI);
		intent.putExtra("OtaPackageName", Constants.FIRMWARE_NAME);
		getApplicationContext().startActivity(intent);
    }
}
