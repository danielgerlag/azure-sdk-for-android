package com.azure.android.core.http.options;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.azure.android.core.http.ServiceClient;
import com.azure.android.core.util.logging.ClientLogger;

public class AzureClientOptions {
    @NonNull
    private String apiVersion;
    @Nullable
    private ClientLogger logger;
    @NonNull
    private TelemetryOptions telemetryOptions;
    @NonNull
    private TransportOptions transportOptions;

    /**
     * Options for configuring a {@link ServiceClient}.
     *
     * @param apiVersion The API version of the service to invoke.
     * @param logger     The {@link ClientLogger} to be used by the {@link ServiceClient}.
     */
    public AzureClientOptions(@NonNull String apiVersion,
                              @Nullable ClientLogger logger) {
        this(apiVersion, logger, new TelemetryOptions(false, null), new TransportOptions(0, null));
    }

    /**
     * Options for configuring a {@link ServiceClient}.
     *
     * @param apiVersion       The API version of the service to invoke.
     * @param logger           The {@link ClientLogger} to be used by the {@link ServiceClient}.
     * @param telemetryOptions Options for configuring telemetry sent by the {@link ServiceClient}.
     * @param transportOptions Options for configuring calls sent by the {@link ServiceClient}.
     */
    public AzureClientOptions(@NonNull String apiVersion,
                              @Nullable ClientLogger logger,
                              @NonNull TelemetryOptions telemetryOptions,
                              @NonNull TransportOptions transportOptions) {
        this.apiVersion = apiVersion;
        this.logger = logger == null ? ClientLogger.getDefault(AzureClientOptions.class) : logger;
        this.telemetryOptions = telemetryOptions;
        this.transportOptions = transportOptions;
    }

    /**
     * Gets the service API version the {@link ServiceClient} will use when making calls.
     *
     * @return The service API version.
     */
    @NonNull
    public String getApiVersion() {
        return apiVersion;
    }

    /**
     * Gets the {@link ServiceClient client}'s logger.
     *
     * @return The {@link ClientLogger}.
     */
    @Nullable
    public ClientLogger getLogger() {
        return logger;
    }

    /**
     * Gets the {@link ServiceClient client}'s {@link TelemetryOptions}.
     *
     * @return The {@link TelemetryOptions}.
     */
    @NonNull
    public TelemetryOptions getTelemetryOptions() {
        return telemetryOptions;
    }

    /**
     * Gets the {@link ServiceClient client}'s {@link TransportOptions}.
     *
     * @return The {@link TransportOptions}.
     */
    @NonNull
    public TransportOptions getTransportOptions() {
        return transportOptions;
    }
}