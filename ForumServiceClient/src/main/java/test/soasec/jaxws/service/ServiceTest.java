
/**
 * ServiceTest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
    package test.soasec.jaxws.service;

    /*
     *  ServiceTest Junit test case
    */

    public class ServiceTest extends junit.framework.TestCase{

     
        /**
         * Auto generated test method
         */
        public  void testdeleteMessage() throws java.lang.Exception{

        soasec.jaxws.service.ServiceStub stub =
                    new soasec.jaxws.service.ServiceStub();//the default implementation should point to the right endpoint

           soasec.jaxws.service.ServiceStub.DeleteMessage deleteMessage16=
                                                        (soasec.jaxws.service.ServiceStub.DeleteMessage)getTestObject(soasec.jaxws.service.ServiceStub.DeleteMessage.class);
                    // TODO : Fill in the deleteMessage16 here
                
                        assertNotNull(stub.deleteMessage(
                        deleteMessage16));
                  



        }
        
         /**
         * Auto generated test method
         */
        public  void testStartdeleteMessage() throws java.lang.Exception{
            soasec.jaxws.service.ServiceStub stub = new soasec.jaxws.service.ServiceStub();
             soasec.jaxws.service.ServiceStub.DeleteMessage deleteMessage16=
                                                        (soasec.jaxws.service.ServiceStub.DeleteMessage)getTestObject(soasec.jaxws.service.ServiceStub.DeleteMessage.class);
                    // TODO : Fill in the deleteMessage16 here
                

                stub.startdeleteMessage(
                         deleteMessage16,
                    new tempCallbackN65548()
                );
              


        }

        private class tempCallbackN65548  extends soasec.jaxws.service.ServiceCallbackHandler{
            public tempCallbackN65548(){ super(null);}

            public void receiveResultdeleteMessage(
                         soasec.jaxws.service.ServiceStub.DeleteMessageResponse result
                            ) {
                
            }

            public void receiveErrordeleteMessage(java.lang.Exception e) {
                fail();
            }

        }
      
        /**
         * Auto generated test method
         */
        public  void testaddMessage() throws java.lang.Exception{

        soasec.jaxws.service.ServiceStub stub =
                    new soasec.jaxws.service.ServiceStub();//the default implementation should point to the right endpoint

           soasec.jaxws.service.ServiceStub.AddMessage addMessage18=
                                                        (soasec.jaxws.service.ServiceStub.AddMessage)getTestObject(soasec.jaxws.service.ServiceStub.AddMessage.class);
                    // DONE : Fill in the addMessage18 here
           			addMessage18.setMessageID(0);
       				addMessage18.setUserID(0);
       				addMessage18.setMessaggio("Hello world");
           
                        assertNotNull(stub.addMessage(
                        addMessage18));
                  



        }
        
         /**
         * Auto generated test method
         */
        public  void testStartaddMessage() throws java.lang.Exception{
            soasec.jaxws.service.ServiceStub stub = new soasec.jaxws.service.ServiceStub();
             soasec.jaxws.service.ServiceStub.AddMessage addMessage18=
                                                        (soasec.jaxws.service.ServiceStub.AddMessage)getTestObject(soasec.jaxws.service.ServiceStub.AddMessage.class);
                    // TODO : Fill in the addMessage18 here
                

                stub.startaddMessage(
                         addMessage18,
                    new tempCallbackN65589()
                );
              


        }

        private class tempCallbackN65589  extends soasec.jaxws.service.ServiceCallbackHandler{
            public tempCallbackN65589(){ super(null);}

            public void receiveResultaddMessage(
                         soasec.jaxws.service.ServiceStub.AddMessageResponse result
                            ) {
                
            }

            public void receiveErroraddMessage(java.lang.Exception e) {
                fail();
            }

        }
      
        /**
         * Auto generated test method
         */
        public  void testgetAllMessages() throws java.lang.Exception{

        soasec.jaxws.service.ServiceStub stub =
                    new soasec.jaxws.service.ServiceStub();//the default implementation should point to the right endpoint

           soasec.jaxws.service.ServiceStub.GetAllMessages getAllMessages20=
                                                        (soasec.jaxws.service.ServiceStub.GetAllMessages)getTestObject(soasec.jaxws.service.ServiceStub.GetAllMessages.class);
                    // TODO : Fill in the getAllMessages20 here
                
                        assertNotNull(stub.getAllMessages(
                        getAllMessages20));
                  



        }
        
         /**
         * Auto generated test method
         */
        public  void testStartgetAllMessages() throws java.lang.Exception{
            soasec.jaxws.service.ServiceStub stub = new soasec.jaxws.service.ServiceStub();
             soasec.jaxws.service.ServiceStub.GetAllMessages getAllMessages20=
                                                        (soasec.jaxws.service.ServiceStub.GetAllMessages)getTestObject(soasec.jaxws.service.ServiceStub.GetAllMessages.class);
                    // TODO : Fill in the getAllMessages20 here
                

                stub.startgetAllMessages(
                         getAllMessages20,
                    new tempCallbackN65630()
                );
              


        }

        private class tempCallbackN65630  extends soasec.jaxws.service.ServiceCallbackHandler{
            public tempCallbackN65630(){ super(null);}

            public void receiveResultgetAllMessages(
                         soasec.jaxws.service.ServiceStub.GetAllMessagesResponse result
                            ) {
                
            }

            public void receiveErrorgetAllMessages(java.lang.Exception e) {
                fail();
            }

        }
      
        /**
         * Auto generated test method
         */
        public  void testeditMessage() throws java.lang.Exception{

        soasec.jaxws.service.ServiceStub stub =
                    new soasec.jaxws.service.ServiceStub();//the default implementation should point to the right endpoint

           soasec.jaxws.service.ServiceStub.EditMessage editMessage22=
                                                        (soasec.jaxws.service.ServiceStub.EditMessage)getTestObject(soasec.jaxws.service.ServiceStub.EditMessage.class);
                    // TODO : Fill in the editMessage22 here
                
                        assertNotNull(stub.editMessage(
                        editMessage22));
                  



        }
        
         /**
         * Auto generated test method
         */
        public  void testStarteditMessage() throws java.lang.Exception{
            soasec.jaxws.service.ServiceStub stub = new soasec.jaxws.service.ServiceStub();
             soasec.jaxws.service.ServiceStub.EditMessage editMessage22=
                                                        (soasec.jaxws.service.ServiceStub.EditMessage)getTestObject(soasec.jaxws.service.ServiceStub.EditMessage.class);
                    // TODO : Fill in the editMessage22 here
                

                stub.starteditMessage(
                         editMessage22,
                    new tempCallbackN65671()
                );
              


        }

        private class tempCallbackN65671  extends soasec.jaxws.service.ServiceCallbackHandler{
            public tempCallbackN65671(){ super(null);}

            public void receiveResulteditMessage(
                         soasec.jaxws.service.ServiceStub.EditMessageResponse result
                            ) {
                
            }

            public void receiveErroreditMessage(java.lang.Exception e) {
                fail();
            }

        }
      
        //Create an ADBBean and provide it as the test object
        public org.apache.axis2.databinding.ADBBean getTestObject(java.lang.Class type) throws java.lang.Exception{
           return (org.apache.axis2.databinding.ADBBean) type.newInstance();
        }

        
        

    }
    