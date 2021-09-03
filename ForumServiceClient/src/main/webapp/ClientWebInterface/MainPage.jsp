<%@page contentType="text/html;charset=UTF-8"%><HTML>
<HEAD>
	<TITLE>Web Services Test Client</TITLE>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css" integrity="sha256-46r060N2LrChLLb5zowXQ72/iKKNiw/lAmygmHExk/o=" crossorigin="anonymous" />
	<link rel="stylesheet" href="../css/style.css" />
	
</HEAD>
<BODY>
	<div class="container">
		<div class="main-body p-0">
		    <div class="inner-wrapper">	
		    	<div class="simplebar-height-auto-observer-wrapper"><div class="simplebar-height-auto-observer"></div></div>
		    	<div class="simplebar-mask">
		    			<div class="simplebar-content-wrapper" style="height: 100%; overflow: auto; padding-left: 0.5rem;" >
		    			
					    		<p><b>Secure Forum Web Service</b>, identify yourself with username and password, and post your messages here.</p>
					    		<b>Roles:</b>
					    		<p>-- <b>user</b>: can only view and post messages.</p>
					    		<p>-- <b>moderator</b>: can edit all users messages.</p>
					    		<p>-- <b>admin</b>: can edit, delete messages and open close a topic.</p>
		    			</div>
		    	</div>
		        <!-- Inner main -->
		        <div class="inner-main">

		
		
		            <!-- Forum Detail -->
		            
		            	<iframe  src="ForumBoard.jsp" name="forumboard" style="height: 100%; margin-top:5px; margin-right:5px;">
		                </iframe>    
		    
		            <!-- /Forum Detail -->
		            
		
					<!-- Enter New Message Form -->
			        <form id="msgForm" method="post" target="forumboard" action="ForumBoard.jsp">
			        	<div class="modal-header d-flex align-items-center bg-primary text-white">
			        		<h6 class="modal-title mb-0" id="threadModalLabel">Enter New Message</h6> 
			        	</div>
			        	
			        	<div class="modal-body">
			        		<div class="form-group"> 
			        			<textarea style = "float: left;" rows = "5" cols = "60" name = "message" placeholder="Insert Your Message Here"></textarea>
				        		<div class="form-group-2"> 
				        			<input id="soap_op" type="hidden" name="op" value="add">
				        			<input id=msg_id type="hidden" name="msg_id" value="0">
				        			<input type="text" id="email" placeholder="Username" name="user">
	  								<input type="password" id="pwd" placeholder="Password" name="pswd">
	  								<button type = "submit" value = "submit" class="btn btn-primary">Post</button>
	  							</div>
  							</div>
			        	</div>
			        	<div class="modal-footer">
			        		
			        	</div>
			        </form>
			        <!-- Enter New Message Form -->
		
		            <!-- /Inner main body -->
		        </div>
		        <!-- /Inner main -->
		        
		    </div>
		
		    
		</div>
	</div>
</BODY>