package android.rockchip.update.interfaces;

import android.rockchip.update.dataRecord.RemoteRequestMsg;
import android.rockchip.update.dataRecord.RemoteServiceAcountInfo;

/**用于描述与服务器进行通信的一端 */
public interface IClient{

    /**与服务器建立链接 */
    int connectToService(RemoteServiceAcountInfo acountInfo, ServiceCallBack callBack);
    /**与服务器断开链接 */
    int disconnectToService();
    /**注册要监听的内容 */
    int subscribeTopics();
    /**发送msg至服务器 */
    int sendMsgToService(RemoteRequestMsg msg);
}