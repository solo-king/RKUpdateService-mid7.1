package android.rockchip.update.interfaceImp;

import android.rockchip.update.interfaces.IRemoteServiceConnector;
import android.rockchip.update.interfaces.ServiceCallBack;
import android.rockchip.update.interfaceImpl.UsrMqttClient;
import android.rockchip.update.interfaces.IClient;
import android.content.Context;
import android.rockchip.update.dataRecord.RemoteServiceAcountInfo;

/**mqtt 链接器 */
public class MqttRemoteServiceConnector implements IRemoteServiceConnector{

    private Context mContext;

    public MqttRemoteServiceConnector(Context context){

        mContext = context;
    }
    /**连接到远端服务器 */
    @Override
    public IClient connectToRemoteService(RemoteServiceAcountInfo accInfo, ServiceCallBack callBack){
        UsrMqttClient client = new  UsrMqttClient(mContext);
        if(client != null){
            
            client.connectToService(accInfo, callBack);
        }

        return client;
    }
    
    /**关闭远端服务器的链接*/
    @Override
    public int disconnectToRemoteService(IClient clinet){

        return clinet.disconnectToService();
    }

}