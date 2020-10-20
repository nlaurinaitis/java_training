package ru.stqa.pft.soap;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.lavasoft.GeoIPService;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

    @Test
    public void testMyIp() {
        String geoIP = new GeoIpService().getGeoIPServiceSoap12().getIpLocation("94.19.201.232");
        assertEquals(geoIP.getCountryCode(), "RUS");
    }
}
