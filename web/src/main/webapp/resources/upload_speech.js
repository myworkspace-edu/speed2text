/**
 * Process event: upload button is clicked & uploaded file is available
 */
$(function () {
    $('#attachment').on('change', function() {
        // Auto submit form
        $('#frmUploadSpeech').submit();
    });
    

});


/**
 * Process event: form is submitted
 */

$(function () {

  $('#frmUploadSpeech').submit(function(e) {
      e.preventDefault();
      
      var frm = $('#frmUploadSpeech');
      var frmData = new FormData(this);

          
      $.ajax({
          url : _ctx + 'speech2text/uploadfile',
          type : frm.attr('method'),
          enctype : frm.attr('enctype'),
          data : frmData,
          processData : false,
          contentType : false,
          success : function(result) {
            if (result) {
                // console.log(result);
                updateOutput(result);
            } 
              // alert("Save OK.");
          },
          error : function() {
              alert("Process uploaded file failed.");
          }
      });
  });

});

function updateOutput(content) {
    $('#resultText').text(content);
}

