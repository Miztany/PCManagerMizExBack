package almond.response;

import lombok.Data;

@Data
public class ContentResponse<T> {
	private Boolean result;
	private T content;
}
