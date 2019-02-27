package android.rockchip.update.dataRecord;

/**用于描述远端服务器登入账户信息 */
public class RemoteServiceAcountInfo{
/**服务器侧需要记录信息 */
    /**远端服务器id */
    private int hostId;
    /**远端服务器地址 */
    private String hostAddress;
    /**远端服务器端口号 */
    private int hostPort ;
    /**远端服务器登入用户名 */
    private String hostUser;
    /**远端服务器登入密码 */
    private String hostPassword;

/**客户端侧需要记录信息 */
    /**请求发起者id,该标志必须唯一*/
    private String deviceId;
    /**请求发起者名称*/
    private String deviceName;

    public RemoteServiceAcountInfo() {
    }

    public RemoteServiceAcountInfo(int hostId, String hostAddress, int hostPort, String hostUser, String hostPassword, String deviceId, String deviceName) {
        this.hostId = hostId;
        this.hostAddress = hostAddress;
        this.hostPort = hostPort;
        this.hostUser = hostUser;
        this.hostPassword = hostPassword;
        this.deviceId = deviceId;
        this.deviceName = deviceName;
    }

    public int getHostId() {
        return this.hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public String getHostAddress() {
        return this.hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    public int getHostPort() {
        return this.hostPort;
    }

    public void setHostPort(int hostPort) {
        this.hostPort = hostPort;
    }

    public String getHostUser() {
        return this.hostUser;
    }

    public void setHostUser(String hostUser) {
        this.hostUser = hostUser;
    }

    public String getHostPassword() {
        return this.hostPassword;
    }

    public void setHostPassword(String hostPassword) {
        this.hostPassword = hostPassword;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @Override
    public String toString() {
        return "{" +
            " hostId='" + getHostId() + "'" +
            ", hostAddress='" + getHostAddress() + "'" +
            ", hostPort='" + getHostPort() + "'" +
            ", hostUser='" + getHostUser() + "'" +
            ", hostPassword='" + getHostPassword() + "'" +
            ", deviceId='" + getDeviceId() + "'" +
            ", deviceName='" + getDeviceName() + "'" +
            "}";
    }
 

}
