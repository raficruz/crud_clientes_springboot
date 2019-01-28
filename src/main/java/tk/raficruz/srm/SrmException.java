package tk.raficruz.srm;

import org.springframework.http.HttpStatus;

public class SrmException  extends RuntimeException {

	  private static final long serialVersionUID = 5756629042593962926L;

	  private final HttpStatus status;
	  private final String message;
	  private final String textTranslated;

	  public HttpStatus getStatus() {
	    return status;
	  }

	  @Override
	  public String getMessage() {
	    if(hasTranslated()) {
	      return textTranslated;
	    }
	    return message;
	  }

	  public String getTextTranslated() {
	    return textTranslated;
	  }

	  public Boolean hasTranslated() {
	    return textTranslated != null;
	  }

	  public SrmException(HttpStatus status, String message) {
	    this.status = status;
	    this.message = message;
	    this.textTranslated = null;
	  }

	  public SrmException(HttpStatus status, String message, String translate) {
	    this.status = status;
	    this.message = message;
	    this.textTranslated = translate;
	  }
	}
