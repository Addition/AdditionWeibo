package weibo.genew.com.weibo.model;

import org.json.JSONException;
import org.json.JSONObject;

public class VisibleModel
{
	private Integer type;
	private Integer list_id;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getList_id() {
		return list_id;
	}

	public void setList_id(Integer list_id) {
		this.list_id = list_id;
	}

	public static VisibleModel coverJSONObject2VisibleModel(JSONObject jsonObject) {
		VisibleModel visibleModel = new VisibleModel();
		try {
			visibleModel.setType(jsonObject.getInt("type"));
			visibleModel.setList_id(jsonObject.getInt("list_id"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return visibleModel;
	}
}
