package org.brightlife.api.service.model.dto.response;

public class APIResponse {
	private int status;
	private ResponseData data;
	private ErrorData error;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ResponseData getData() {
		return data;
	}

	public void setData(ResponseData data) {
		this.data = data;
	}

	public ErrorData getError() {
		return error;
	}

	public void setError(ErrorData error) {
		this.error = error;
	}
}
