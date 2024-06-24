package almond.response;

import lombok.Data;

@Data
public class ContentResponse<T> {
	private Boolean result;
	private T content;

	public ContentResponse() {
		super();
	}

	public ContentResponse(Boolean result, T content) {
		this.result = result;
		this.content = content;
	}
}
