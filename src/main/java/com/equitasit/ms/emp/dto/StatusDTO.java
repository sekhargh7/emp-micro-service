package com.equitasit.ms.emp.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StatusDTO {

	public StatusDTO() {

	}

	public StatusDTO(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	private String statusMsg;

}
