package org.brightlife.api.service.model.dto.response;

import java.util.List;

import org.brightlife.api.service.model.StudentApplication;

public class ApplicationResponseList extends ResponseData {

	private List<StudentApplication> dataList;

	public List<StudentApplication> getDataList() {
		return dataList;
	}

	public void setDataList(List<StudentApplication> dataList) {
		this.dataList = dataList;
	}

}
