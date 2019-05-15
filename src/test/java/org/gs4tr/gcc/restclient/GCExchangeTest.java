package org.gs4tr.gcc.restclient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.isA;

import java.io.IOException;

import org.gs4tr.gcc.restclient.operation.SessionStart;
import org.gs4tr.gcc.restclient.operation.SessionStart.SessionStartResponse;
import org.gs4tr.gcc.restclient.operation.SessionStart.SessionStartResponseData;
import org.gs4tr.gcc.restclient.util.APIUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@PrepareForTest({ APIUtils.class })
@RunWith(PowerMockRunner.class)
public class GCExchangeTest {

    private static final GCConfig TEST_CONFIG = getTestConfig();

    private GCExchange exchange;

    @Before
    public void setUp() throws IOException {
        PowerMockito.mockStatic(APIUtils.class);
        PowerMockito.when(APIUtils.doRequest(isA(SessionStart.class)))
                .thenReturn(getSuccessfulSessionStartResponse());
        exchange = new GCExchange(TEST_CONFIG);
    }

    @Test
    public void addCustomHeader_shouldAddHeaderToConfig() {
        String name = "Authorization2";
        String value = "Bearer 1234";

        exchange.addCustomHeader(name, value);

        String customHeader = exchange.getConfig()
                .getCustomHeaders()
                .get(name);
        assertEquals(value, customHeader);
    }

    private static GCConfig getTestConfig() {
        return GCConfig.builder()
                .apiUrl("https://test.api.url/v0")
                .connectorKey("test-connector")
                .password("pass1234")
                .userAgent("test-agent")
                .userName("test-user")
                .build();
    }

    private SessionStartResponse getSuccessfulSessionStartResponse() {
        SessionStartResponse response = new SessionStartResponse();
        response.setStatus(200);
        SessionStartResponseData data = new SessionStartResponseData();
        data.setUserSessionKey("1234bearertoken");
        response.setResponseData(data);
        return response;
    }

}
