package com.carolsboutique.clientpos.restclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class URLReader {
    private ObjectMapper om;
    private File configFile;

    public URLReader() {
        om = new ObjectMapper();
        configFile = new File("C:\\Users\\Liam\\Desktop\\Java\\Carol-s-Clientique\\src\\main\\java\\com\\"
                + "carolsboutique\\clientpos\\restclient\\RestURLConfig.json");
    }
    
    public String readRestURL(){
        URLReader.URL url = null;
        try {
            om.readValue(configFile, URLReader.URL.class);
        } catch (IOException ex) {
            Logger.getLogger(URLReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return url.toString();
    }
    
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class URL{
        private String protocol;
        private String ip;
        private String port;
        private String mainResource;
        private String rootRoute;

        @Override
        public String toString() {
            return protocol + "://" + ip + ":" + port + "/" + mainResource + "/" + rootRoute;
        }
    }
}