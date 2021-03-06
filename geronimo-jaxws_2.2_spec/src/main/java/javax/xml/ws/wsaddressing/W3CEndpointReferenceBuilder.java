/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package javax.xml.ws.wsaddressing;

import org.w3c.dom.Element;

import javax.xml.namespace.QName;
import javax.xml.ws.spi.Provider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Utility class to build W3CEndpointReference instances.  
 */
public final class W3CEndpointReferenceBuilder {
    private String address;
    private QName serviceName;
    private QName endpointName;
    private QName interfaceName;
    private String wsdlDocumentLocation;
    private List<Element> referenceParameters;
    private List<Element> metadataElements;
    private List<Element> elements;
    private Map<QName, String> attributes;
    
    public W3CEndpointReferenceBuilder() {
    }
    
    public W3CEndpointReferenceBuilder address(String address) {
        this.address = address;
        return this;
    }
    
    public W3CEndpointReferenceBuilder serviceName(QName serviceName) {
        this.serviceName = serviceName;
        return this;
    }
    
    public W3CEndpointReferenceBuilder endpointName(QName endpointName) {
        //TODO NLS enable
        if (this.serviceName == null) {
            throw new IllegalStateException("The endpoint qname cannot be set before the service qname.");
        }
        
        this.endpointName = endpointName;
        return this;
    }
    
    public W3CEndpointReferenceBuilder wsdlDocumentLocation(String wsdlDocumentLocation) {
        this.wsdlDocumentLocation = wsdlDocumentLocation;
        return this;
    }
    
    public W3CEndpointReferenceBuilder referenceParameter(Element referenceParameter) {
        //TODO NLS enable
        if (referenceParameter == null) {
            throw new IllegalArgumentException("A reference parameter cannot be null.");
        }
        
        if (this.referenceParameters == null) {
            this.referenceParameters = new ArrayList<Element>();
        }
        
        this.referenceParameters.add(referenceParameter);
        return this;
    }
    
    public W3CEndpointReferenceBuilder metadata(Element metadataElement) {
        //TODO NLS enable
        if (metadataElement == null) {
            throw new IllegalArgumentException("A metadata element cannot be null.");
        }
        
        if (this.metadataElements == null) {
            this.metadataElements = new ArrayList<Element>();
        }
        
        this.metadataElements.add(metadataElement);
        return this;
    }
    
    /**
     * @since 2.2
     */
    public W3CEndpointReferenceBuilder attribute(QName name, String value) {
        if (name == null || value == null) {
            throw new IllegalArgumentException("An attribute name or value cannot be null.");
        }
        
        if (this.attributes == null) {
            this.attributes = new HashMap<QName, String>();
        }
        attributes.put(name, value);
        return this;
    }
    /**
     * @since 2.2
     */
    public W3CEndpointReferenceBuilder element(Element el) {
        if (el == null) {
            throw new IllegalArgumentException("An element cannot be null.");
        }
        
        if (this.elements == null) {
            this.elements = new ArrayList<Element>();
        }
        elements.add(el);
        return this;
    }
    /**
     * @since 2.2
     */
    public W3CEndpointReferenceBuilder interfaceName(QName iname) {
        interfaceName = iname;
        return this;
    }

    
    public W3CEndpointReference build() {
        try {
            //JAX-WS 2.2 runtime
            return Provider.provider().createW3CEndpointReference(address, 
                                                                  interfaceName, 
                                                                  serviceName,
                                                                  endpointName,
                                                                  metadataElements,
                                                                  wsdlDocumentLocation,
                                                                  referenceParameters,
                                                                  elements,
                                                                  attributes);            
        } catch (UnsupportedOperationException ex) {
            //Probably a JAX-WS 2.1 runtime
            return Provider.provider().createW3CEndpointReference(address,
                                                                  serviceName,
                                                                  endpointName,
                                                                  metadataElements,
                                                                  wsdlDocumentLocation,
                                                                  referenceParameters);
        }
    }

}
