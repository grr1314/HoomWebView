package hoomsun.com.lc.hoomwebview.data.post;


/**
 * Created by hoomsun on 2018/4/16.
 */

public class BasePostModel<T> {
    public String serviceName;
    public String platformNo;
    public String userData;
    public T reqData;
    public String keySerial;
    public String sign;

    public BasePostModel(String serviceName, String platformNo, String userData, T reqData, String keySerial, String sign) {
        this.serviceName = serviceName;
        this.platformNo = platformNo;
        this.userData = userData;
        this.reqData = reqData;
        this.keySerial = keySerial;
        this.sign = sign;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getPlatformNo() {
        return platformNo;
    }

    public void setPlatformNo(String platformNo) {
        this.platformNo = platformNo;
    }

    public String getUserData() {
        return userData;
    }

    public void setUserData(String userData) {
        this.userData = userData;
    }

    public T getReqData() {
        return reqData;
    }

    public void setReqData(T reqData) {
        this.reqData = reqData;
    }

    public String getKeySerial() {
        return keySerial;
    }

    public void setKeySerial(String keySerial) {
        this.keySerial = keySerial;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
