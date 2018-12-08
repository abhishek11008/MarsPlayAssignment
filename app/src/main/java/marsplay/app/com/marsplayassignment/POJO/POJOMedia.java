package marsplay.app.com.marsplayassignment.POJO;

import org.json.JSONArray;

import java.io.Serializable;

public class POJOMedia implements Serializable {

    String imgUrl;
    String imgUrlKey;


    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgUrlKey() {
        return imgUrlKey;
    }

    public void setImgUrlKey(String imgUrlKey) {
        this.imgUrlKey = imgUrlKey;
    }
}

