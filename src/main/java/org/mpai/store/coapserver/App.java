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

    public static final String CONFIG_AIF_DEMO_URL = "/config/aif/demo";
    public static final String CONFIG_AIW_CAE_REV_URL = "/config/aiw/CAE-REV";
    public static final String CONFIG_AIM_VOLUME_PEAKS_ANALYSIS_URL = "/config/aim/VolumePeaksAnalysis";
    public static final String CONFIG_AIM_CONTROL_UNIT_SENSORS_READING_URL = "/config/aim/ControlUnitSensorsReading";
    public static final String CONFIG_AIM_MOTION_RECOGNITION_ANALYSIS_URL = "/config/aim/MotionRecognitionAnalysis";
    public static final String CONFIG_AIM_MOVEMENTS_WITH_AUDIO_VALIDATION_URL = "/config/aim/MovementsWithAudioValidation";

    @Value("${mpai.store.host}")
    String mpaiStoreHost;

    @Bean
    CoapServer coapServer() throws IOException {
        CoapServer coapServer = newBuilder().transport(InetAddress.getByName(mpaiStoreHost), 5683).build();
        // example
        coapServer.addRequestHandler("/test", new ReadOnlyCoapResource("Hello from coap server"));
        coapServer.addRequestHandler("/large", new ReadOnlyCoapResource("{\"$schema\":\"https://json-schema.org/draft/2020-12/schema\",\"$id\":\"https://mpai.community/standards/resources/MPAI-AIF/V1/AIF-metadata.schema.json\",\"title\":\"MPAI-AIF V1 AIF metadata\",\"ImplementerID\":1,\"Version\":\"v1.0\",\"APIProfile\":\"Main\",\"ResourcePolicies\":[{\"Name\":\"Memory\",\"Minimum\":\"50000\",\"Maximum\":\"120000\",\"Request\":\"80000\"},{\"Name\":\"CPUNumber\",\"Minimum\":\"1\",\"Maximum\":\"2\",\"Request\":\"1\"},{\"Name\":\"CPU:Class\",\"Minimum\":\"Low\",\"Maximum\":\"High\",\"Request\":\"Low\"}],\"Authentication\":\"admin\",\"TimeBase\":\"NTP\"}"));

        // mpai
        coapServer.addRequestHandler(CONFIG_AIF_DEMO_URL, new ReadOnlyCoapResource("{\"$schema\":\"https://json-schema.org/draft/2020-12/schema\",\"$id\":\"https://mpai.community/standards/resources/MPAI-AIF/V1/AIF-metadata.schema.json\",\"title\":\"MPAI-AIF V1 AIF metadata\",\"ImplementerID\":1,\"Version\":\"v1.0\",\"APIProfile\":\"Main\",\"ResourcePolicies\":[{\"Name\":\"Memory\",\"Minimum\":\"50000\",\"Maximum\":\"120000\",\"Request\":\"80000\"},{\"Name\":\"CPUNumber\",\"Minimum\":\"1\",\"Maximum\":\"2\",\"Request\":\"1\"},{\"Name\":\"CPU:Class\",\"Minimum\":\"Low\",\"Maximum\":\"High\",\"Request\":\"Low\"}],\"Authentication\":\"admin\",\"TimeBase\":\"NTP\"}"));
        coapServer.addRequestHandler(CONFIG_AIW_CAE_REV_URL, new ReadOnlyCoapResource("{\"$schema\":\"https://json-schema.org/draft/2020-12/schema\",\"$id\":\"https://mpai.community/standards/resources/MPAI-AIF/V1/AIW-AIM-metadata.schema.json\",\"title\":\"CAE AIF v1 AIW/AIM metadata\",\"Identifier\":{\"ImplementerID\":1,\"Specification\":{\"Standard\":\"MPAI-CAE\",\"AIW\":\"CAE-REV\",\"AIM\":\"CAE-REV\",\"Version\":\"1\"}},\"APIProfile\":\"Main\",\"Description\":\"AIW that implements Use-Case CAE-REV (Rehabilitation Exercises Validation)\",\"Types\":[{\"Name\":\"Sensors_Data_t\",\"Type\":\"mpai_message_t\"},{\"Name\":\"Mic_Buffer_Data_t\",\"Type\":\"mpai_message_t\"},{\"Name\":\"Mic_Peak_Data_t\",\"Type\":\"mpai_message_t\"},{\"Name\":\"Motion_Data_t\",\"Type\":\"mpai_message_t\"}],\"Ports\":[{\"Name\":\"SensorsDataChannel\",\"Direction\":\"InputOutput\",\"RecordType\":\"Sensors_Data_t\",\"Technology\":\"Software\",\"Protocol\":\"\",\"IsRemote\":false},{\"Name\":\"MicBufferDataChannel\",\"Direction\":\"InputOutput\",\"RecordType\":\"Mic_Buffer_Data_t\",\"Technology\":\"Software\",\"Protocol\":\"\",\"IsRemote\":false},{\"Name\":\"MicPeakDataChannel\",\"Direction\":\"InputOutput\",\"RecordType\":\"Mic_Peak_Data_t\",\"Technology\":\"Software\",\"Protocol\":\"\",\"IsRemote\":false},{\"Name\":\"MotionDataChannel\",\"Direction\":\"InputOutput\",\"RecordType\":\"Motion_Data_t\",\"Technology\":\"Software\",\"Protocol\":\"\",\"IsRemote\":false}],\"Topology\":[{\"Output\":{\"AIMName\":\"MotionRecognitionAnalysis\",\"PortName\":\"SensorsDataChannel\"},\"Input\":{\"AIMName\":\"ControlUnitSensorsReading\",\"PortName\":\"SensorsDataChannel\"}},{\"Output\":{\"AIMName\":\"MovementsWithAudioValidation\",\"PortName\":\"MicPeakDataChannel\"},\"Input\":{\"AIMName\":\"VolumePeaksAnalysis\",\"PortName\":\"MicPeakDataChannel\"}},{\"Output\":{\"AIMName\":\"\",\"PortName\":\"MicBufferDataChannel\"},\"Input\":{\"AIMName\":\"VolumePeaksAnalysis\",\"PortName\":\"\"}},{\"Output\":{\"AIMName\":\"MovementsWithAudioValidation\",\"PortName\":\"MotionDataChannel\"},\"Input\":{\"AIMName\":\"MotionRecognitionAnalysis\",\"PortName\":\"MotionDataChannel\"}}],\"SubAIMs\":[{\"Name\":\"VolumePeaksAnalysis\",\"Identifier\":{\"ImplementerID\":1,\"Specification\":{\"Standard\":\"MPAI-CAE\",\"AIW\":\"CAE-REV\",\"AIM\":\"VolumePeaksAnalysis\",\"Version\":\"1\"}}},{\"Name\":\"ControlUnitSensorsReading\",\"Identifier\":{\"ImplementerID\":1,\"Specification\":{\"Standard\":\"MPAI-CAE\",\"AIW\":\"CAE-REV\",\"AIM\":\"ControlUnitSensorsReading\",\"Version\":\"1\"}}},{\"Name\":\"MotionRecognitionAnalysis\",\"Identifier\":{\"ImplementerID\":1,\"Specification\":{\"Standard\":\"MPAI-CAE\",\"AIW\":\"CAE-REV\",\"AIM\":\"MotionRecognitionAnalysis\",\"Version\":\"1\"}}},{\"Name\":\"MovementsWithAudioValidation\",\"Identifier\":{\"ImplementerID\":1,\"Specification\":{\"Standard\":\"MPAI-CAE\",\"AIW\":\"CAE-REV\",\"AIM\":\"MovementsWithAudioValidation\",\"Version\":\"1\"}}}],\"Implementations\":[{\"BinaryName\":\"firmware.bin\",\"Architecture\":\"arm\",\"OperatingSystem\":\"Zephyr RTOS\",\"Version\":\"v0.1\",\"Source\":\"AIMStorage\",\"Destination\":\"\"}],\"ResourcePolicies\":[{\"Name\":\"Memory\",\"Minimum\":\"50000\",\"Maximum\":\"120000\",\"Request\":\"80000\"},{\"Name\":\"CPUNumber\",\"Minimum\":\"1\",\"Maximum\":\"2\",\"Request\":\"1\"},{\"Name\":\"CPU:Class\",\"Minimum\":\"Low\",\"Maximum\":\"High\",\"Request\":\"Low\"}],\"Documentation\":[{\"Type\":\"Tutorial\",\"URI\":\"https://mpai.community/standards/mpai-cae/\"}]}"));
        coapServer.addRequestHandler(CONFIG_AIM_VOLUME_PEAKS_ANALYSIS_URL, new ReadOnlyCoapResource("{\"Identifier\":{\"ImplementerID\":1,\"Specification\":{\"Name\":\"CAE\",\"AIW\":\"REV\",\"AIM\":\"AudioPeaksAnalysis\",\"Version\":\"1\"}},\"Description\":\"This AIM implements analysis transform function for CAE-REV that recognizes volume peaks from microphone array audio.\",\"Ports\":[],\"Topology\":[],\"SubAIMs\":[],\"Implementations\":[],\"Documentation\":[{\"Type\":\"Tutorial\",\"URI\":\"https://mpai.community/standards/mpai-cae/\"}]}"));
        coapServer.addRequestHandler(CONFIG_AIM_CONTROL_UNIT_SENSORS_READING_URL, new ReadOnlyCoapResource("{\"Identifier\":{\"ImplementerID\":1,\"Specification\":{\"Name\":\"CAE\",\"AIW\":\"REV\",\"AIM\":\"ControlUnitSensorsReading\",\"Version\":\"1\"}},\"Description\":\"This AIM implements sensor readings from control unit.\",\"Ports\":[],\"Topology\":[],\"SubAIMs\":[],\"Implementations\":[],\"Documentation\":[{\"Type\":\"Tutorial\",\"URI\":\"https://mpai.community/standards/mpai-cae/\"}]}"));
        coapServer.addRequestHandler(CONFIG_AIM_MOTION_RECOGNITION_ANALYSIS_URL, new ReadOnlyCoapResource("{\"Identifier\":{\"ImplementerID\":1,\"Specification\":{\"Name\":\"CAE\",\"AIW\":\"REV\",\"AIM\":\"MotionRecognitionAnalysis\",\"Version\":\"1\"}},\"Description\":\"This AIM implements motion recognition analysing data from inertial unit.\",\"Ports\":[],\"Topology\":[],\"SubAIMs\":[],\"Implementations\":[],\"Documentation\":[{\"Type\":\"Tutorial\",\"URI\":\"https://mpai.community/standards/mpai-cae/\"}]}"));
        coapServer.addRequestHandler(CONFIG_AIM_MOVEMENTS_WITH_AUDIO_VALIDATION_URL, new ReadOnlyCoapResource("{\"Identifier\":{\"ImplementerID\":1,\"Specification\":{\"Name\":\"CAE\",\"AIW\":\"REV\",\"AIM\":\"MovementsWithAudioValidation\",\"Version\":\"1\"}},\"Description\":\"This AIM implements a validation of limbs movements during rehabilitation exercises, according to music rhythm\",\"Ports\":[],\"Topology\":[],\"SubAIMs\":[],\"Implementations\":[],\"Documentation\":[{\"Type\":\"Tutorial\",\"URI\":\"https://mpai.community/standards/mpai-cae/\"}]}"));

        coapServer.start();
        return coapServer;
    }
}
