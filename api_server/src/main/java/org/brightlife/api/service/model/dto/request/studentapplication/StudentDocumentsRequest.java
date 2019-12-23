package org.brightlife.api.service.model.dto.request.studentapplication;

import org.springframework.web.multipart.MultipartFile;

public class StudentDocumentsRequest extends StudentApplicationRequest {

	private MultipartFile photoidProof;
	private MultipartFile incomeProof;
	private MultipartFile addressProof;
	private MultipartFile others1;
	private MultipartFile others2;
	private MultipartFile others3;

	public StudentDocumentsRequest(MultipartFile photoidProof, MultipartFile incomeProof, MultipartFile addressProof, MultipartFile others1,
			MultipartFile others2, MultipartFile others3) {
		this.addressProof = addressProof;
		this.incomeProof = incomeProof;
		this.others1 = others1;
		this.others2 = others2;
		this.others3 = others3;
		this.photoidProof = photoidProof;
	}

	public MultipartFile getPhotoidProof() {
		return photoidProof;
	}

	public void setPhotoidProof(MultipartFile photoidProof) {
		this.photoidProof = photoidProof;
	}

	public MultipartFile getIncomeProof() {
		return incomeProof;
	}

	public void setIncomeProof(MultipartFile incomeProof) {
		this.incomeProof = incomeProof;
	}

	public MultipartFile getAddressProof() {
		return addressProof;
	}

	public void setAddressProof(MultipartFile addressProof) {
		this.addressProof = addressProof;
	}

	public MultipartFile getOthers1() {
		return others1;
	}

	public void setOthers1(MultipartFile others1) {
		this.others1 = others1;
	}

	public MultipartFile getOthers2() {
		return others2;
	}

	public void setOthers2(MultipartFile others2) {
		this.others2 = others2;
	}

	public MultipartFile getOthers3() {
		return others3;
	}

	public void setOthers3(MultipartFile others3) {
		this.others3 = others3;
	}

}
