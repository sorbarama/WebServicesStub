package it.lutech.c2sense.pee.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.1 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "PeeWS", targetNamespace = "http://ws.pee.c2sense.lutech.it/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface PeeWSProxy {

    /**
     *
     * @param arg0
     * @param arg1
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    public String importProfile(
            @WebParam(name = "arg0", partName = "arg0") byte[] arg0,
            @WebParam(name = "arg1", partName = "arg1") String arg1);

    /**
     *
     * @param arg0
     * @param arg1
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    public String deleteProfile(
            @WebParam(name = "arg0", partName = "arg0") String arg0,
            @WebParam(name = "arg1", partName = "arg1") Boolean arg1);

    /**
     *
     * @param arg0
     * @param arg1
     * @param arg2
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    public String startProfile(
            @WebParam(name = "arg0", partName = "arg0") String arg0,
            @WebParam(name = "arg1", partName = "arg1") String arg1,
            @WebParam(name = "arg2", partName = "arg2") String arg2);

    /**
     *
     * @param arg0
     * @param arg1
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    public String completeUserTask(
            @WebParam(name = "arg0", partName = "arg0") String arg0,
            @WebParam(name = "arg1", partName = "arg1") String arg1);

}
