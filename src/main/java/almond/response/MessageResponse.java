package almond.response;

import lombok.Data;

@Data
public class MessageResponse {
	private Boolean result;
	private String message;
	
	public MessageResponse() {
		super();
	}
	public MessageResponse(Boolean result, String message) {
		this.result = result;
		this.message = message;
	}
}
