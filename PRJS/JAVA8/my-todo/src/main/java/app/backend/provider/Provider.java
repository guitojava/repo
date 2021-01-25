package app.backend.provider;


/**
 * General purpose class for any kind of provider (API).
 */
public interface Provider<Request extends ProviderRequest, Response extends ProviderResponse> {

    Response request(final Request rq);

}
