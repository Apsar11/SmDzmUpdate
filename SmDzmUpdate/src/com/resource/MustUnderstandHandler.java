package com.resource;

import org.apache.ws.security.WSConstants;
import org.apache.ws.security.WSSecurityEngine;
import org.apache.ws.security.WSSecurityEngineResult;
import org.apache.ws.security.WSSecurityException;
import org.apache.ws.security.message.token.UsernameToken;
import org.apache.ws.security.handler.WSHandlerConstants;
import org.apache.ws.security.handler.WSHandler;
import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;
import org.apache.axis.message.SOAPEnvelope;
import org.apache.axis.message.SOAPHeaderElement;
import org.apache.ws.security.components.crypto.Merlin;
import org.apache.ws.security.components.crypto.Crypto;
import org.apache.ws.security.components.crypto.CryptoFactory;

import javax.xml.namespace.QName;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.List;

public class MustUnderstandHandler extends BasicHandler {

    private static final String CRYPTO_PROPERTIES = "crypto.properties"; // Crypto config properties for verifying signature

    @Override
    public void invoke(MessageContext context) throws AxisFault {
        SOAPEnvelope envelope = context.getRequestMessage().getSOAPEnvelope();

        // Loop through all headers to find the Security header
        Iterator headers = (Iterator) envelope.getHeaders();

        while (headers.hasNext()) {
            SOAPHeaderElement header = (SOAPHeaderElement) headers.next();

            if (header.getMustUnderstand()) {
                boolean processed = processHeader(header);

                if (!processed) {
                    throw new AxisFault("MustUnderstand header not processed: " + header.getQName());
                }
            }
        }
    }

    private boolean processHeader(SOAPHeaderElement header) {
        QName headerName = header.getQName();

        if ("Security".equals(headerName.getLocalPart()) 
                && "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd".equals(headerName.getNamespaceURI())) {
            try {
                processSecurityHeader(header);
                return true;
            } catch (WSSecurityException e) {
                e.printStackTrace();
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        return false;
    }

    private void processSecurityHeader(SOAPHeaderElement header) throws Exception {
        WSSecurityEngine securityEngine = new WSSecurityEngine();
        List<WSSecurityEngineResult> results = securityEngine.processSecurityHeader(header.getAsDOM(), null);

        for (WSSecurityEngineResult result : results) {
            Integer action = (Integer) result.get(WSSecurityEngineResult.TAG_ACTION);

            if ((action & WSConstants.UT) != 0) {
                UsernameToken ut = (UsernameToken) result.get(WSSecurityEngineResult.TAG_USERNAME_TOKEN);
                String username = ut.getName();
                String password = ut.getPassword();
                validateUsernameToken(username, password);
            } else if ((action & WSConstants.SIGN) != 0) {
                verifySignature(result);
            }
        }
    }

    private void validateUsernameToken(String username, String password) throws WSSecurityException {
        if ("Apsar".equals(username) && "Apsar@11".equals(password)) {
            System.out.println("UsernameToken is valid");
        } else {
            throw new WSSecurityException(WSSecurityException.FAILED_AUTHENTICATION, "Invalid UsernameToken");
        }
    }

    private void verifySignature(WSSecurityEngineResult result) throws WSSecurityException {
        // Extract X509 certificate from the result
        X509Certificate[] cert = (X509Certificate[]) result.get(WSSecurityEngineResult.TAG_X509_CERTIFICATE);
        
        if (cert == null) {
            throw new WSSecurityException(WSSecurityException.FAILED_CHECK, "No X509 certificate found in signature");
        }

        try {
            // Load the trusted certificates
            Crypto crypto = CryptoFactory.getInstance(CRYPTO_PROPERTIES);
            if (!crypto.verifyTrust(cert, true)){
                throw new WSSecurityException(WSSecurityException.FAILED_CHECK, "Signature verification failed");
            }

            // Optionally, you can implement more checks on the certificate
            System.out.println("Signature verification succeeded, certificate: " + cert.length);
        } catch (Exception e) {
            throw new WSSecurityException("Signature verification failed: " + e.getMessage(), e);
        }
    }
}
