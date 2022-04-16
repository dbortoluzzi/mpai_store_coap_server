package org.mpai.store.coapserver;

import com.mbed.coap.server.CoapServer;
import com.mbed.coap.utils.ReadOnlyCoapResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.InetAddress;

import static com.mbed.coap.server.CoapServerBuilder.newBuilder;

@Configuration
public class App
{

    @Value("${mpai.store.host}")
    String mpaiStoreHost;

    @Bean
    CoapServer coapServer() throws IOException {
        CoapServer coapServer = newBuilder().transport(InetAddress.getByName(mpaiStoreHost), 5683).build();
        coapServer.addRequestHandler("/test", new ReadOnlyCoapResource("Hello from coap server"));
        coapServer.addRequestHandler("/large", new ReadOnlyCoapResource("{\"$schema\":\"https://json-schema.org/draft/2020-12/schema\",\"$id\":\"https://mpai.community/standards/resources/MPAI-AIF/V1/AIF-metadata.schema.json\",\"title\":\"MPAI-AIF V1 AIF metadata\",\"ImplementerID\":1,\"Version\":\"v1.0\",\"APIProfile\":\"Main\",\"ResourcePolicies\":[{\"Name\":\"Memory\",\"Minimum\":\"50000\",\"Maximum\":\"120000\",\"Request\":\"80000\"},{\"Name\":\"CPUNumber\",\"Minimum\":\"1\",\"Maximum\":\"2\",\"Request\":\"1\"},{\"Name\":\"CPU:Class\",\"Minimum\":\"Low\",\"Maximum\":\"High\",\"Request\":\"Low\"}],\"Authentication\":\"admin\",\"TimeBase\":\"NTP\"}"));
        coapServer.addRequestHandler("/config/aif/demo", new ReadOnlyCoapResource("{\"$schema\":\"https://json-schema.org/draft/2020-12/schema\",\"$id\":\"https://mpai.community/standards/resources/MPAI-AIF/V1/AIF-metadata.schema.json\",\"title\":\"MPAI-AIF V1 AIF metadata\",\"ImplementerID\":1,\"Version\":\"v1.0\",\"APIProfile\":\"Main\",\"ResourcePolicies\":[{\"Name\":\"Memory\",\"Minimum\":\"50000\",\"Maximum\":\"120000\",\"Request\":\"80000\"},{\"Name\":\"CPUNumber\",\"Minimum\":\"1\",\"Maximum\":\"2\",\"Request\":\"1\"},{\"Name\":\"CPU:Class\",\"Minimum\":\"Low\",\"Maximum\":\"High\",\"Request\":\"Low\"}],\"Authentication\":\"admin\",\"TimeBase\":\"NTP\"}"));

        coapServer.start();
        return coapServer;
    }
}
