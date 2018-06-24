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
								<th>File</th>
								<th>SMS Count</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${files}" var="file">
								<tr>
									<td><a href="/show?id=${file.id}"
										class="btn btn-success">${file.fileName}</a> 
									</td>
									<td>${file.count}</td>
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