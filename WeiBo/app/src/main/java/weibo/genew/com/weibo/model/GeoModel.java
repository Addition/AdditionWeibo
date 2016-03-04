package weibo.genew.com.weibo.model;

import org.json.JSONException;
import org.json.JSONObject;

public class GeoModel{
	private static final long serialVersionUID = 4618588250790246125L;

    private String longitude;//	经度坐标
    private String latitude;//	string	维度坐标
    private String city;//	string	所在城市的城市代码
    private String province;//	string	所在省份的省份代码
    private String city_name;//	string	所在城市的城市名称
    private String province_name;//	string	所在省份的省份名称
    private String address;//	string	所在的实际地址，可以为空
    private String pinyin;//	string	地址的汉语拼音，不是所有情况都会返回该字段
    private String more;//	string	更多信息，不是所有情况都会返回该字段

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }

    public static GeoModel coverJSONObject2GeoModel(JSONObject jsonObject)
    {
        GeoModel geoModel = new GeoModel();
        try {
            if (jsonObject.has("address"))
            geoModel.setAddress(jsonObject.getString("address"));
            geoModel.setCity(jsonObject.getString("city"));
            geoModel.setCity_name(jsonObject.getString("city_name"));
            geoModel.setLatitude(jsonObject.getString("latitude"));
            geoModel.setLongitude(jsonObject.getString("longitude"));
            if (jsonObject.has("more"))
            geoModel.setMore(jsonObject.getString("more"));
            if (jsonObject.has("pinyin"))
            geoModel.setPinyin(jsonObject.getString("pinyin"));
            geoModel.setProvince(jsonObject.getString("province"));
            geoModel.setProvince_name(jsonObject.getString("province_name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return geoModel;
    }
}
