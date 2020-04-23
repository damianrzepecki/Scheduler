$(document).ready(function() {
    $("#locales").change(function () {
        var selectedOption = $('#locales').val();
        if (selectedOption != ''){
            window.location.replace('?lang=' + selectedOption);
        };
    });
});
$(document).ready(function() {
    $('#langChangePL').on('click', function{
        calendar.setOption('locale', 'pl');
    });
)};
$(document).ready(function() {
    $('#langChangeEN').on('click', function{
        calendar.setOption('locale', 'en');
    });
)};
//$(document).ready(function() {
//    $('#langChangePL').on('click', function{
//    $('#table').bootstrapTable('destroy').bootstrapTable({
//          locale: 'pl-PL'
//          });
//    alert('PLPLPLP');
//   });
//});

//$(document).ready(function() {
//    $('#langChangeEN').on('click', function{
//    $('#table').bootstrapTable('destroy').bootstrapTable({
//          locale: 'en-US'
//          });
//    alert('USUS');
//   });
//});