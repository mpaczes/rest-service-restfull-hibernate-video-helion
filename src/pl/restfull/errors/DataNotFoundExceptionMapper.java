package pl.restfull.errors;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Biblioteka Jersey mapuje po tej klasie, czyli @Provider.
 * <br />
 * Zeby obsluga bledow zadzialala poprawnie trzeba zaktualizowac plik 'pom.xml', czyli zakomentowac bibliotteki z Sun'a, a dodac 'Glass Fish'a'.
 *
 */
@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException exception) {
		
		ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), 404, "go to the documentation website");
		
		return Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
		
	}

}
