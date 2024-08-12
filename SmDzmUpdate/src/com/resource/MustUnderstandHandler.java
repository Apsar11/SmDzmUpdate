package com.resource;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.xml.namespace.QName;

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;
import org.apache.axis.message.SOAPEnvelope;
import org.apache.axis.message.SOAPHeaderElement;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.WSSecurityEngine;
import org.apache.ws.security.WSSecurityEngineResult;
import org.apache.ws.security.WSSecurityException;
import org.apache.ws.security.message.token.UsernameToken;

public class MustUnderstandHandler extends BasicHandler {

    private static final String VALID_USERNAME = "validUsername";
    private static final String VALID_PASSWORD = "validPassword";

    @Override
    public void invoke(MessageContext context) throws AxisFault {
        SOAPEnvelope envelope = context.getRequestMessage().getSOAPEnvelope();
        Iterator headers = (Iterator) envelope.getHeaders();

        while (headers.hasNext()) {
            SOAPHeaderElement header = (SOAPHeaderElement) headers.next();
            if (header.getMustUnderstand()) {
                QName headerName = header.getQName();
                boolean processed = processHeader(header);

                if (!processed) {
                    // Log the error instead of throwing an AxisFault directly
                    System.err.println("Cannot process mustUnderstand header: " + headerName);
                }
            }
        }
    }

    private boolean processHeader(SOAPHeaderElement header) {
        QName headerName = header.getQName();

        if ("Security".equals(headerName.getLocalPart()) && "http://schemas.xmlsoap.org/ws/2002/12/secext".equals(headerName.getNamespaceURI())) {
            try {
                processSecurityHeader(header);
                return true;
            } catch (WSSecurityException e) {
                // Log the exception for debugging purposes
                e.printStackTrace();
                return false;
            } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
        if (VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password)) {
            System.out.println("UsernameToken is valid");
        } else {
            throw new WSSecurityException(WSSecurityException.FAILED_AUTHENTICATION, "Invalid UsernameToken");
        }
    }

    private void verifySignature(WSSecurityEngineResult result) throws WSSecurityException {
        // Implement your signature verification logic
        boolean signatureValid = true; // Placeholder for actual logic

        if (!signatureValid) {
            throw new WSSecurityException(WSSecurityException.FAILED_CHECK, "Invalid Signature");
        } else {
            System.out.println("Signature is valid");
        }
    }

    @Override
    public void onFault(MessageContext context) {
        // Add custom fault handling logic here if needed
    }

    @Override
    public void init() {
        // Optional: Add initialization logic here if needed
    }

    @Override
    public void cleanup() {
        // Optional: Add cleanup logic here if needed
    }
}
