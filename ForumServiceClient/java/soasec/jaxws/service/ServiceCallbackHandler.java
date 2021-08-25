
/**
 * ServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package soasec.jaxws.service;

    /**
     *  ServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class ServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public ServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public ServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for deleteMessage method
            * override this method for handling normal response from deleteMessage operation
            */
           public void receiveResultdeleteMessage(
                    soasec.jaxws.service.ServiceStub.DeleteMessageResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from deleteMessage operation
           */
            public void receiveErrordeleteMessage(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addMessage method
            * override this method for handling normal response from addMessage operation
            */
           public void receiveResultaddMessage(
                    soasec.jaxws.service.ServiceStub.AddMessageResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addMessage operation
           */
            public void receiveErroraddMessage(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getAllMessages method
            * override this method for handling normal response from getAllMessages operation
            */
           public void receiveResultgetAllMessages(
                    soasec.jaxws.service.ServiceStub.GetAllMessagesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getAllMessages operation
           */
            public void receiveErrorgetAllMessages(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for editMessage method
            * override this method for handling normal response from editMessage operation
            */
           public void receiveResulteditMessage(
                    soasec.jaxws.service.ServiceStub.EditMessageResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from editMessage operation
           */
            public void receiveErroreditMessage(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addUser method
            * override this method for handling normal response from addUser operation
            */
           public void receiveResultaddUser(
                    soasec.jaxws.service.ServiceStub.AddUserResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addUser operation
           */
            public void receiveErroraddUser(java.lang.Exception e) {
            }
                


    }
    