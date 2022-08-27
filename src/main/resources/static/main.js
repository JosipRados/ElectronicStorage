

$('document').ready(function(){
    $('.table .btn').on('click', function(event){
        event.preventDefault();
        $('#elementDetails').modal();
    });
});