package app.backend.provider;

/**
 * General purpose class for any kind of request
 */
@SuppressWarnings("unchecked")
public abstract class ProviderRequest<Rq extends ProviderRequest, Rs extends ProviderResponse> {

    public ProviderRequest() {
        super();
    }

    public Rs exec() {
        return createProvider().request((Rq) this);
    }

    public void checkIntegrityAndApplyDefaults() {
    }

    public abstract Provider<Rq, Rs> createProvider();

    public abstract Rs createResponse();

}
