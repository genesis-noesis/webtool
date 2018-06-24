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
               <h3>Upload File</h3>

               <form method="POST" action="uploadFile" enctype="multipart/form-data">
               		<fieldset class="form-group">File name :<input type="text" name="fileName" class="form-control"><br/></fieldset>
                  	<fieldset class="form-group">File to upload :<input type="file" name="file" ><br /> </fieldset>
                  	<fieldset class="form-group"><input type="submit" value="Upload" class="btn btn-success"> Press here to upload the file!</fieldset>
               </form>
               <div class="progress" id="progress">
				  <div id="progressBar" class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;">
				    0%
				  </div>
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