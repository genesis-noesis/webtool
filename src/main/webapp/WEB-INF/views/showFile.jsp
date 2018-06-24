<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

      <div class="container-fluid text-center">
         <div class="row content">
            <div class="col-sm-2 sidenav">
               <!--<p><a href="#">Link</a></p>
                  <p><a href="#">Link</a></p>
                  <p><a href="#">Link</a></p>-->
            </div>
            <div class="col-sm-8 text-left">
				<div class="container">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Mobile Number</th>
								<th>Message</th>
								<th>Created Date</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${file.smsList}" var="sms">
								<tr>
									<td>${sms.mobileNumber}</td>
									<td>${sms.message}</td>
									<td>${sms.createdDate}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>               
            </div>
            
            <div class="col-sm-2 sidenav">
               <!--<div class="well">
                  <p>Image</p>
                  </div>
                  <div class="well">
                  <p>Image</p>
                  </div>-->
            </div>
         </div>
      </div>
<%@ include file="common/footer.jspf"%>    