package cn.com.goods.util;

public class Message {

	

	int pageSize;
	int pageNum;
	Long queryCount;

	String retCode;
	String retMsg;
	Object headMsg;
	Object content;
	Object body;
	String token;
	Object jsonObj;
	String queryTotal;
	

	public String getQueryTotal() {
		return queryTotal;
	}

	public void setQueryTotal(String queryTotal) {
		this.queryTotal = queryTotal;
	}

	public Object getJsonObj() {
		return jsonObj;
	}

	public void setJsonObj(Object jsonObj) {
		this.jsonObj = jsonObj;
	}

	public Object getHeadMsg() {
		return headMsg;
	}

	public void setHeadMsg(Object headMsg) {
		this.headMsg = headMsg;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public Long getQueryCount() {
		return queryCount;
	}

	public void setQueryCount(Long queryCount) {
		this.queryCount = queryCount;
	}

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	public Object getBody() {
		return body;
	}

	@Override
	public String toString() {
		return "Message{" +
				"pageSize=" + pageSize +
				", pageNum=" + pageNum +
				", queryCount=" + queryCount +
				", retCode='" + retCode + '\'' +
				", retMsg='" + retMsg + '\'' +
				", headMsg=" + headMsg +
				", content=" + content +
				", body=" + body +
				", token='" + token + '\'' +
				", jsonObj=" + jsonObj +
				", queryTotal='" + queryTotal + '\'' +
				'}';
	}

	public void setBody(Object body) {
		this.body = body;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
