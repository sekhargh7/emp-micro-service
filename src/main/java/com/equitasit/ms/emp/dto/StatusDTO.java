package com.equitasit.ms.emp.dto;

import lombok.Data;

@Data
public class StatusDTO {

	public StatusDTO() {

	}

	public StatusDTO(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	private String statusMsg;

}
