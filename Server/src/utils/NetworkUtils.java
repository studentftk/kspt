package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 *
 * @author llama
 */
public final class NetworkUtils {
    
    public static Map<String, String> parseURIQuery(String query){
        Map<String, String> result = new HashMap();
        if (query==null) return result;
        String[] parameters = query.split("&");
        for (String parameter : parameters) {
            String[] pair = parameter.split("=");
            String name = pair[0];
            String value =  (pair.length>0) ? pair[1] : "";
            result.put(name, value);
        }
        return result;
    }
    
    /**
     * Creates ssl conext based on keystore file
     * @param keyStoreName
     * @param openPassword
     * @param usePassword
     * @return 
     */
    public static SSLContext createSSLContext(String keyStoreName, String openPassword, String usePassword){
        try {
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(new FileInputStream(keyStoreName), openPassword.toCharArray());
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
            keyManagerFactory.init(keyStore, usePassword.toCharArray());
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(keyManagerFactory.getKeyManagers(), null, null);
            return sslContext;
        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException | UnrecoverableKeyException | KeyManagementException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static void setAllTrusted(URLConnection connection){
            try {
                HttpsURLConnection sslConnection = (HttpsURLConnection)connection;
                SSLContext sslContext = SSLContext.getInstance("TLS");
                
                TrustManager[] trustManagers = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                        @Override
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {  }
                        @Override
                        public void checkServerTrusted(X509Certificate[] certs, String authType) {  }
                    }
                };
                
                HostnameVerifier hostnameVerifier = new HostnameVerifier() {
                    @Override
                    public boolean verify(String s, SSLSession sslSession) {
                        return s.equals(sslSession.getPeerHost());
                    }
                };
                sslConnection.setHostnameVerifier(hostnameVerifier);
                
                sslContext.init(null, trustManagers, null);
                sslConnection.setSSLSocketFactory(sslContext.getSocketFactory());
            } catch (NoSuchAlgorithmException | KeyManagementException ex) {
                throw new RuntimeException(ex);
            }

    }
    
    public static String getServerURL(){
        return "https://studentftk.tk";
    }  
    
    
}
